package com.adcc.cdm.fc.bean;

import com.adcc.cdm.slot.bean.FlowControlInfo;
import com.adcc.cdm.slot.service.SlotUtil;
import com.adcc.common.util.InfoUtil;
import com.adcc.efps.bean.EfpsFlight;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FlightCoordination entity.
 *
 * @author Fangj
 */
public class FlightCoordination extends AbstractFlightCoordination implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(FlightCoordination.class);

    /**
     * default constructor
     */
    public FlightCoordination() {
    }

    /**
     * minimal constructor
     */
    public FlightCoordination(Long id) {
        super(id);
    }

    /**
     * 航班是否为CRS航班（航班收CRS流控影响）
     */
    private boolean isCRSFlight = false;

    /**
     * 航班是否为CDM流控
     */
    private boolean isCDMFlight = false;

    public boolean isCRSFlight() {
        return isCRSFlight;
    }

    public void setCRSFlight(boolean isCRSFlight) {
        this.isCRSFlight = isCRSFlight;
    }

    public boolean isCDMFlight() {
        return isCDMFlight;
    }

    public void setCDMFlight(boolean isCDMFlight) {
        this.isCDMFlight = isCDMFlight;
    }

    /**
     * 航班CTOT失效时间
     */
    private String CTOTUnEffectiveTime;

    public String getCTOTUnEffectiveTime() {
        return CTOTUnEffectiveTime;
    }

    public void setCTOTUnEffectiveTime(String cTOTUnEffectiveTime) {
        CTOTUnEffectiveTime = cTOTUnEffectiveTime;
    }

    /**
     * RCF原因类型 -
     */
    public static final String reActivedByMitDepError = "reActivedByMitDepError";

    /**
     * RCF原因类型 -
     */
    public static final String reActivedByFlowCHG = "reActivedByFlowCHG";

    /**
     * RCF原因类型 - 因shortGS短期GS而平移时隙
     */
    public static final String shiftSlotByShortGS = "shiftSlotByShortGS";

    /**
     * RCF 细节
     */
    private Map<String, Object> reActivedDetails = new HashMap<String, Object>();

    public Map<String, Object> getReActivedDetails() {
        return reActivedDetails;
    }

    /**
     * RCF 航班原因
     */
    private String reActivedReason;

    public String getReActivedReason() {
        return reActivedReason;
    }

    public void setReActivedReason(String reActivedReason) {
        this.reActivedReason = reActivedReason;
    }

    /**
     * 获取解析后的 过监控点信息
     *
     * @return 解析后MPI List信息
     */
    public List<Map<String, String>> getMonitorPointInfoDetail() {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        if (!InfoUtil.IsEmptyString(this.getMonitorPointInfo())) {
            // ?转义String
            String[] pointInfo = this.getMonitorPointInfo().split("\\u003F");
            for (int i = 0; i < pointInfo.length; i++) {
                // 拆到每个点
                String[] pointInfoItem = pointInfo[i].split("/");
                Map<String, String> pointInfoItemMap = new HashMap<String, String>();
                resultList.add(pointInfoItemMap);
                for (int j = 0; j < pointInfoItem.length; j++) {
                    // 每个点内的信息
                    String[] pointInfoElement = pointInfoItem[j].split(":");
                    if (pointInfoElement.length == 2) {
                        pointInfoItemMap.put(pointInfoElement[0], pointInfoElement[1]);
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 获取解析后的 过监控点信息
     *
     * @return 解析后MPI Map信息
     */
    public Map<String, Map<String, String>> getMonitorPointInfoMap() {
        Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
        List<Map<String, String>> t = this.getMonitorPointInfoDetail();
        for (Map<String, String> mapItem : t) {
            resultMap.put(mapItem.get("ID"), mapItem);
        }
        return resultMap;
    }

    /**
     * 将MPI由Map形式，转成字符串形式
     *
     * @param pointInfoMap Map格式MPI
     * @return String格式MPI
     */
    public String getMonitorPointInStrFromMap(Map<String, Map<String, String>> pointInfoMap) {
        StringBuffer newMPInfo = new StringBuffer();
        Set<String> keySet = pointInfoMap.keySet();
        for (String key : keySet) {
            Map<String, String> pointInfo = pointInfoMap.get(key);
            // 添加ID
            newMPInfo.append("ID:").append(key).append("/");
            // 添加E
            if (!SlotUtil.isEmptyStr(pointInfo.get("E"))) {
                newMPInfo.append("E:").append(pointInfo.get("E")).append("/");
            }
            // 添加P,如果存在
            if (pointInfo.containsKey("P")) {
                newMPInfo.append("P:").append(pointInfo.get("P")).append("/");
            }
            // 添加C,如果存在
            if (pointInfo.containsKey("C")) {
                newMPInfo.append("C:").append(pointInfo.get("C")).append("/");
            }
            // 添加A，如果存在
            if (pointInfo.containsKey("A")) {
                newMPInfo.append("A:").append(pointInfo.get("A")).append("/");
            }
            // 添加T，如果存在
            if (pointInfo.containsKey("T")) {
                newMPInfo.append("T:").append(pointInfo.get("T")).append("/");
            }
            // 添加S
            if (pointInfo.containsKey("S")) {
                newMPInfo.append("S:").append(pointInfo.get("S")).append("/");
            }
            // 添加H
            if (pointInfo.containsKey("H")) {
                newMPInfo.append("/").append("H:").append(pointInfo.get("H"));
            }
            newMPInfo.append("?");
        }
        return newMPInfo.toString();
    }

    /**
     * 将MPI由List形式，转成字符串形式
     *
     * @param pointInfoList List格式MPI
     * @return String格式MPI
     */
    @Deprecated
    public String getMonitorPointStrFromList(List<Map<String, String>> pointInfoList) {
        StringBuffer newMPInfo = new StringBuffer();
        for (Map<String, String> map : pointInfoList) {
            // 添加ID
            newMPInfo.append("ID:").append(map.get("ID")).append("/");
            // 添加E
            newMPInfo.append("E:").append(map.get("E")).append("/");
            // 添加C,如果存在
            if (map.containsKey("C")) {
                newMPInfo.append("C:").append(map.get("C")).append("/");
            }
            // 添加P,如果存在
            if (map.containsKey("P")) {
                newMPInfo.append("P:").append(map.get("P")).append("/");
            }
            // 添加A，如果存在
            if (map.containsKey("A")) {
                newMPInfo.append("A:").append(map.get("A")).append("/");
            }
            // 添加T，如果存在
            if (map.containsKey("T")) {
                newMPInfo.append("T:").append(map.get("T")).append("/");
            }
            // 添加S
            newMPInfo.append("S:").append(map.get("S"));
            // 添加H
            if (map.containsKey("H")) {
                newMPInfo.append("/").append("H:").append(map.get("H"));
            }
            newMPInfo.append("?");
        }
        return newMPInfo.toString();
    }

    /**
     * 测试
     *
     * @param args args
     */
    public static void main(String[] args) {
        FlightCoordination fc = new FlightCoordination();
        fc.setMonitorPointInfo("ID:ISKEM/E:20120522202050?ID:NODAL/E:20120522202848?");
        Map<String, Map<String, String>> m = fc.getMonitorPointInfoMap();
        System.out.println(m.get("ISKEM").get("E"));
        fc.setFlowControlInfo("[1][123]");
        System.out.println(fc.haveThisFlowControlInfo(12L));
    }

    /**
     * 航班命中流控信息
     * <p/>
     * 存储格式[FLOW_1.ID][FLOW_2.ID]...FLOW_n.ID
     */
    private String flowControlInfo;

    public String getFlowControlInfo() {
        return flowControlInfo;
    }

    public void setFlowControlInfo(String flowControlInfo) {
        this.flowControlInfo = flowControlInfo;
    }

    /**
     * 检测航班是否受该条流控影响
     *
     * @param flowControlInfoID 流控ID
     * @return true：是，false：否
     */
    public boolean haveThisFlowControlInfo(Long flowControlInfoID) {
        if ((this.getFlowControlInfo() != null) && (flowControlInfoID != null)) {
            if (this.getFlowControlInfo().contains(new StringBuffer().append("[").append(flowControlInfoID).append("]"))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 系统推算TOBT
     */
    private String tobtP;

    public String getTobtP() {
        return tobtP;
    }

    public void setTobtP(String tobtP) {
        this.tobtP = tobtP;
    }

    /**
     * 获取航班可用TOBT
     * <p/>
     * 若有TOBT则返回，否则返回TOBTP
     *
     * @return TOBT || TOBTP
     */
    public String getRealTobt() {
        if (this.getTobtByConfig() != null) {
            return this.getTobtByConfig();
        } else {
            return this.getTobtP();
        }
    }

    /**
     *
     * @return real TOBT || EOBT
     */
    public String getMostPossibleObt() {
        String obt = this.getRealTobt();
        if (obt == null) {
            obt = this.getEobt();
        }
        return obt;
    }

    /**
     * 航班是否已经仍未申请放行
     *
     * @return
     */
    public boolean isUnApplyFC() {
        if ((this.getClearanceStatus() == FlightCoordination.CLEARANCE_UNAPPLY)
                || (this.getClearanceStatus() == FlightCoordination.CLEARANCE_CREW_APPLY)
                || (this.getClearanceStatus() == FlightCoordination.CLEARANCE_ENTRUST_APPLY)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断航班是否属于 APPLY 优先级
     *
     * @return
     */
    public boolean isInApplyPriority() {
        if ((this.getClearanceStatus() == FlightCoordination.CLEARANCE_APPLYED) && (!SlotUtil.isEmptyStr(this.getHobt()))) {
            return true;
        }
        return false;
    }

    /**
     * 判断航班是否已经被批复
     *
     * @return true：是，false：否
     */
    public boolean isInArrrovedStatus() {
        if (((this.getClearanceStatus() == FlightCoordination.CLEARANCE_APPROVED))
                || (this.getClearanceStatus() == FlightCoordination.CLEARANCE_HANDOVER)) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    private String beClearedHobt;
    private String beClearedHCtot;
    private String beClearedCobt;
    private String beClearedCtd;

    public String getBeClearedHobt() {
        return beClearedHobt;
    }

    public void setBeClearedHobt(String beClearedHobt) {
        this.beClearedHobt = beClearedHobt;
    }

    public String getBeClearedHCtot() {
        return beClearedHCtot;
    }

    public void setBeClearedHCtot(String beClearedHCtot) {
        this.beClearedHCtot = beClearedHCtot;
    }

    public String getBeClearedCobt() {
        return beClearedCobt;
    }

    public void setBeClearedCobt(String beClearedCobt) {
        this.beClearedCobt = beClearedCobt;
    }

    public String getBeClearedCtd() {
        return beClearedCtd;
    }

    public void setBeClearedCtd(String beClearedCtd) {
        this.beClearedCtd = beClearedCtd;
    }

    /**
     * 获取航班ATD or CTD
     *
     * @return ATD || CTD
     */
    public String getAtdOrCtd() {
        return (SlotUtil.isEmptyStr(this.getAtd()) ? this.getCtd() : this.getAtd());
    }

    /**
     * 平移C时间
     *
     * @param timeSpan 平移量（分钟）
     */
    public void shiftCTD(int timeSpan) {
        // COBT
        if (SlotUtil.isNotEmptyStr(this.getCobt())) {
            this.setBeClearedCobt(this.getCobt());
            this.setCobt(SlotUtil.getTimeAddMin(this.getCobt(), timeSpan));
        }
        // CTD
        if (SlotUtil.isNotEmptyStr(this.getCtd())) {
            this.setBeClearedCtd(this.getCtd());
            this.setCtd(SlotUtil.getTimeAddMin(this.getCtd(), timeSpan));
        }
        // CTA
        if (SlotUtil.isNotEmptyStr(this.getCta())) {
            this.setCta(SlotUtil.getTimeAddMin(this.getCta(), timeSpan));
        }
        // CTO
        if (SlotUtil.isNotEmptyStr(this.getCto())) {
            this.setCto(SlotUtil.getTimeAddMin(this.getCto(), timeSpan));
        }
        // CTO2
        if (SlotUtil.isNotEmptyStr(this.getCto2())) {
            this.setCto2(SlotUtil.getTimeAddMin(this.getCto2(), timeSpan));
        }
        // CTO3
        if (SlotUtil.isNotEmptyStr(this.getCto3())) {
            this.setCto3(SlotUtil.getTimeAddMin(this.getCto3(), timeSpan));
        }
        // CTO4
        if (SlotUtil.isNotEmptyStr(this.getCto4())) {
            this.setCto4(SlotUtil.getTimeAddMin(this.getCto4(), timeSpan));
        }
        // HOBT
        if (SlotUtil.isNotEmptyStr(this.getHobt())) {
            this.setBeClearedHobt(this.getHobt());
            this.setHobt(SlotUtil.getTimeAddMin(this.getHobt(), timeSpan));
        }
        // MPI，把C字段加固定值
        Map<String, Map<String, String>> map = this.getMonitorPointInfoMap();
        Set<String> keySet = map.keySet();
        SimpleDateFormat format14 = new SimpleDateFormat("yyyyMMddHHmmss");
        for (String key : keySet) {
            Map<String, String> map2 = map.get(key);
            String targetTime = map2.get("C");
            if (SlotUtil.isNotEmptyStr(targetTime)) {
                map2.put("C", SlotUtil.getTimeAddMin(targetTime, format14, timeSpan));
            }
        }
        this.setMonitorPointInfo(this.getMonitorPointInStrFromMap(map));
    }

    /**
     * 清空C时间
     */
    public void clearCTDStatus() {
        this.setLocked(0);
        this.setCto2(null);
        this.setCto(null);
        this.setCto3(null);
        this.setCto4(null);
        this.setCta(null);
        if (!SlotUtil.isEmptyStr(this.getHobt())) {
            this.setBeClearedHobt(this.getHobt());
        }
        if (!SlotUtil.isEmptyStr(this.getCobt())) {
            this.setBeClearedCobt(this.getCobt());
        }
        if (!SlotUtil.isEmptyStr(this.getHctot())) {
            this.setBeClearedHCtot(this.getHctot());
        }
        if (!SlotUtil.isEmptyStr(this.getCtd())) {
            this.setBeClearedCtd(this.getCtd());
        }
        this.setHobt(null);
        this.setHctot(null);
        this.setCobt(null);
        this.setCtd(null);
        this.setLctot(null);

        // 把MPI.C字段去掉
        Map<String, Map<String, String>> map = this.getMonitorPointInfoMap();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Map<String, String> map2 = map.get(key);
            map2.remove("C");
        }
        this.setMonitorPointInfo(this.getMonitorPointInStrFromMap(map));
        if (this.getClearanceStatus() != null && this.getClearanceStatus().equals(FlightCoordination.CLEARANCE_APPROVED)) {
            this.setClearanceStatus(FlightCoordination.CLEARANCE_APPLYED);
        }
    }

    /**
     * 计算航班的GSobt时间
     * <p/>
     * 用于同优先级的排序和时隙交换
     *
     * @return GSOBT时间
     */
    public String caculateGSobt2() {
        // 有GSOBT 返回 GSOBT
        // 无GSOBT 返回 IOBT
        // 都没有则返回 EOBT
        if (!SlotUtil.isEmptyStr(this.getGsobt())) {
            return this.getGsobt();
        }

        if (SlotUtil.isNotEmptyStr(this.getIobt())) {
            return this.getIobt();
        }

        return this.getEobt();
    }


    /**
     * 计算航班的GSobt时间
     * <p/>
     * 用于同优先级的排序和时隙交换
     *
     * @return SGOBT时间
     */
    public String caculateGSobt() {
        // 有GSOBT 返回 GSOBT
        // 无GSOBT 返回TOBTP 和SOBT中的大值
        // 都没有则返回 EOBT

        if (!SlotUtil.isEmptyStr(this.getGsobt())) {
            return this.getGsobt();
        }

        List<String> slotList = new ArrayList<String>();
        slotList.add(this.getSobt());

        // slotList.add(this.getTobtP());
        slotList.add(this.getEarlstARDT());
        if (SlotUtil.isEmptyStr(this.getSobt())) {
            slotList.add(this.getIobt());
        }

        String slot = SlotUtil.sortMaxDateString(slotList);

        if (!SlotUtil.isEmptyStr(slot)) {
            return slot;
        } else {
            return this.getEobt();
        }
    }

    /**
     * 该航班具有CRS优先流控
     */
    private boolean crsFlowPriority;

    /**
     * 该航班具有CDM优先流控
     */
    private boolean cdmFlowPriority;

    /**
     * 该航班具有CRS优先流控
     */
    public boolean isCrsFlowPriority() {
        return crsFlowPriority;
    }

    public void setCrsFlowPriority(boolean crsFlowPriority) {
        this.crsFlowPriority = crsFlowPriority;
    }

    public boolean isCdmFlowPriority() {
        return cdmFlowPriority;
    }

    public void setCdmFlowPriority(boolean cdmFlowPriority) {
        this.cdmFlowPriority = cdmFlowPriority;
    }

    /**
     * 是否命中指定时隙流控
     */
    private boolean assginFlow;

    public boolean isAssginFlow() {
        return assginFlow;
    }

    public void setAssginFlow(boolean assginFlow) {
        this.assginFlow = assginFlow;
    }

    /**
     * 电子进程单对象
     */
    private EfpsFlight efpsFlight;

    public EfpsFlight getEfpsFlight() {
        return efpsFlight;
    }

    public void setEfpsFlight(EfpsFlight efpsFlight) {
        this.efpsFlight = efpsFlight;
    }

    /**
     * 各机场CTOT时间失效窗口
     * <p/>
     * key：机场ICAO，value：机场对应CTOT失效窗口参数（分钟）
     */
    private static final Map<String, Integer> DepSlotErrorWin = new HashMap<String, Integer>();

    static {
        // 初始化机场CTOT时间失效参数  TODO 代码写死
        DepSlotErrorWin.put("ZUUU", 5);
        DepSlotErrorWin.put("ZUCK", 5);
        DepSlotErrorWin.put("ZPPP", 5);
        DepSlotErrorWin.put("ZUGY", 5);
        DepSlotErrorWin.put("ZZZZ", 10);
    }

    /**
     *
     * @param ICAOAirport 机场ICAO
     * @return depSlotErrWin
     */
    public static Integer getDepSlotErrorWin(String ICAOAirport) {
        Integer depSlotErrWin = FlightCoordination.DepSlotErrorWin.get(ICAOAirport);
        if (depSlotErrWin != null) {
            return depSlotErrWin;
        } else {
            return FlightCoordination.DepSlotErrorWin.get("ZZZZ");
        }
    }

    /**
     * 计算CTOT的失效时间，若不存在，返回Null
     * <p/>
     * CTOTUnEffectTime = CTD + DepSlotErrorWin[Airport]
     * <p/>
     * DepSlotErrorWin 默认ZZZZ：10min，ZUUU/ZUCK/ZUGY/ZPPP：5min
     *
     * @param nowTime 当前时间
     * @param fc      FC
     * @return CTOTUnEffectTime
     */
    public static String caculateCTOTUnEffectTime(String nowTime, FlightCoordination fc) {
        if (SlotUtil.isNotEmptyStr(fc.getCtd())) {
            String caculateCTOTUnEffectTime = SlotUtil.getTimeAddMin(fc.getCtd(), FlightCoordination.getDepSlotErrorWin(fc.getDepap()));
            return caculateCTOTUnEffectTime;
        }
        return null;
    }

    /**
     * 根据EOBT-X推算的TOBT
     */
    private String eobtWithDiff;

    /**
     * 根据前序落地时间推算的最早准备完毕时间
     */
    private String earlstARDT;

    public String getEarlstARDT() {
        return earlstARDT;
    }

    public void setEarlstARDT(String earlstARDT) {
        this.earlstARDT = earlstARDT;
    }

    public String getEobtWithDiff() {
        return eobtWithDiff;
    }

    public void setEobtWithDiff(String eobtWithDiff) {
        this.eobtWithDiff = eobtWithDiff;
    }

    /**
     * 超过该时间时，不能改变FC.COBT
     */
    private String noCOBTChangeTime;

    public String getNoCOBTChangeTime() {
        return noCOBTChangeTime;
    }

    public void setNoCOBTChangeTime(String noCOBTChangeTime) {
        this.noCOBTChangeTime = noCOBTChangeTime;
    }

    /**
     * LDR 流控
     */
    private FlowControlInfo ldrFlowInfo;

    public FlowControlInfo getLdrFlowInfo() {
        return ldrFlowInfo;
    }

    public void setLdrFlowInfo(FlowControlInfo ldrFlowInfo) {
        this.ldrFlowInfo = ldrFlowInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
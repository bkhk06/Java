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
     * �����Ƿ�ΪCRS���ࣨ������CRS����Ӱ�죩
     */
    private boolean isCRSFlight = false;

    /**
     * �����Ƿ�ΪCDM����
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
     * ����CTOTʧЧʱ��
     */
    private String CTOTUnEffectiveTime;

    public String getCTOTUnEffectiveTime() {
        return CTOTUnEffectiveTime;
    }

    public void setCTOTUnEffectiveTime(String cTOTUnEffectiveTime) {
        CTOTUnEffectiveTime = cTOTUnEffectiveTime;
    }

    /**
     * RCFԭ������ -
     */
    public static final String reActivedByMitDepError = "reActivedByMitDepError";

    /**
     * RCFԭ������ -
     */
    public static final String reActivedByFlowCHG = "reActivedByFlowCHG";

    /**
     * RCFԭ������ - ��shortGS����GS��ƽ��ʱ϶
     */
    public static final String shiftSlotByShortGS = "shiftSlotByShortGS";

    /**
     * RCF ϸ��
     */
    private Map<String, Object> reActivedDetails = new HashMap<String, Object>();

    public Map<String, Object> getReActivedDetails() {
        return reActivedDetails;
    }

    /**
     * RCF ����ԭ��
     */
    private String reActivedReason;

    public String getReActivedReason() {
        return reActivedReason;
    }

    public void setReActivedReason(String reActivedReason) {
        this.reActivedReason = reActivedReason;
    }

    /**
     * ��ȡ������� ����ص���Ϣ
     *
     * @return ������MPI List��Ϣ
     */
    public List<Map<String, String>> getMonitorPointInfoDetail() {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        if (!InfoUtil.IsEmptyString(this.getMonitorPointInfo())) {
            // ?ת��String
            String[] pointInfo = this.getMonitorPointInfo().split("\\u003F");
            for (int i = 0; i < pointInfo.length; i++) {
                // ��ÿ����
                String[] pointInfoItem = pointInfo[i].split("/");
                Map<String, String> pointInfoItemMap = new HashMap<String, String>();
                resultList.add(pointInfoItemMap);
                for (int j = 0; j < pointInfoItem.length; j++) {
                    // ÿ�����ڵ���Ϣ
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
     * ��ȡ������� ����ص���Ϣ
     *
     * @return ������MPI Map��Ϣ
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
     * ��MPI��Map��ʽ��ת���ַ�����ʽ
     *
     * @param pointInfoMap Map��ʽMPI
     * @return String��ʽMPI
     */
    public String getMonitorPointInStrFromMap(Map<String, Map<String, String>> pointInfoMap) {
        StringBuffer newMPInfo = new StringBuffer();
        Set<String> keySet = pointInfoMap.keySet();
        for (String key : keySet) {
            Map<String, String> pointInfo = pointInfoMap.get(key);
            // ���ID
            newMPInfo.append("ID:").append(key).append("/");
            // ���E
            if (!SlotUtil.isEmptyStr(pointInfo.get("E"))) {
                newMPInfo.append("E:").append(pointInfo.get("E")).append("/");
            }
            // ���P,�������
            if (pointInfo.containsKey("P")) {
                newMPInfo.append("P:").append(pointInfo.get("P")).append("/");
            }
            // ���C,�������
            if (pointInfo.containsKey("C")) {
                newMPInfo.append("C:").append(pointInfo.get("C")).append("/");
            }
            // ���A���������
            if (pointInfo.containsKey("A")) {
                newMPInfo.append("A:").append(pointInfo.get("A")).append("/");
            }
            // ���T���������
            if (pointInfo.containsKey("T")) {
                newMPInfo.append("T:").append(pointInfo.get("T")).append("/");
            }
            // ���S
            if (pointInfo.containsKey("S")) {
                newMPInfo.append("S:").append(pointInfo.get("S")).append("/");
            }
            // ���H
            if (pointInfo.containsKey("H")) {
                newMPInfo.append("/").append("H:").append(pointInfo.get("H"));
            }
            newMPInfo.append("?");
        }
        return newMPInfo.toString();
    }

    /**
     * ��MPI��List��ʽ��ת���ַ�����ʽ
     *
     * @param pointInfoList List��ʽMPI
     * @return String��ʽMPI
     */
    @Deprecated
    public String getMonitorPointStrFromList(List<Map<String, String>> pointInfoList) {
        StringBuffer newMPInfo = new StringBuffer();
        for (Map<String, String> map : pointInfoList) {
            // ���ID
            newMPInfo.append("ID:").append(map.get("ID")).append("/");
            // ���E
            newMPInfo.append("E:").append(map.get("E")).append("/");
            // ���C,�������
            if (map.containsKey("C")) {
                newMPInfo.append("C:").append(map.get("C")).append("/");
            }
            // ���P,�������
            if (map.containsKey("P")) {
                newMPInfo.append("P:").append(map.get("P")).append("/");
            }
            // ���A���������
            if (map.containsKey("A")) {
                newMPInfo.append("A:").append(map.get("A")).append("/");
            }
            // ���T���������
            if (map.containsKey("T")) {
                newMPInfo.append("T:").append(map.get("T")).append("/");
            }
            // ���S
            newMPInfo.append("S:").append(map.get("S"));
            // ���H
            if (map.containsKey("H")) {
                newMPInfo.append("/").append("H:").append(map.get("H"));
            }
            newMPInfo.append("?");
        }
        return newMPInfo.toString();
    }

    /**
     * ����
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
     * ��������������Ϣ
     * <p/>
     * �洢��ʽ[FLOW_1.ID][FLOW_2.ID]...FLOW_n.ID
     */
    private String flowControlInfo;

    public String getFlowControlInfo() {
        return flowControlInfo;
    }

    public void setFlowControlInfo(String flowControlInfo) {
        this.flowControlInfo = flowControlInfo;
    }

    /**
     * ��⺽���Ƿ��ܸ�������Ӱ��
     *
     * @param flowControlInfoID ����ID
     * @return true���ǣ�false����
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
     * ϵͳ����TOBT
     */
    private String tobtP;

    public String getTobtP() {
        return tobtP;
    }

    public void setTobtP(String tobtP) {
        this.tobtP = tobtP;
    }

    /**
     * ��ȡ�������TOBT
     * <p/>
     * ����TOBT�򷵻أ����򷵻�TOBTP
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
     * �����Ƿ��Ѿ���δ�������
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
     * �жϺ����Ƿ����� APPLY ���ȼ�
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
     * �жϺ����Ƿ��Ѿ�������
     *
     * @return true���ǣ�false����
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
     * ��ȡ����ATD or CTD
     *
     * @return ATD || CTD
     */
    public String getAtdOrCtd() {
        return (SlotUtil.isEmptyStr(this.getAtd()) ? this.getCtd() : this.getAtd());
    }

    /**
     * ƽ��Cʱ��
     *
     * @param timeSpan ƽ���������ӣ�
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
        // MPI����C�ֶμӹ̶�ֵ
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
     * ���Cʱ��
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

        // ��MPI.C�ֶ�ȥ��
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
     * ���㺽���GSobtʱ��
     * <p/>
     * ����ͬ���ȼ��������ʱ϶����
     *
     * @return GSOBTʱ��
     */
    public String caculateGSobt2() {
        // ��GSOBT ���� GSOBT
        // ��GSOBT ���� IOBT
        // ��û���򷵻� EOBT
        if (!SlotUtil.isEmptyStr(this.getGsobt())) {
            return this.getGsobt();
        }

        if (SlotUtil.isNotEmptyStr(this.getIobt())) {
            return this.getIobt();
        }

        return this.getEobt();
    }


    /**
     * ���㺽���GSobtʱ��
     * <p/>
     * ����ͬ���ȼ��������ʱ϶����
     *
     * @return SGOBTʱ��
     */
    public String caculateGSobt() {
        // ��GSOBT ���� GSOBT
        // ��GSOBT ����TOBTP ��SOBT�еĴ�ֵ
        // ��û���򷵻� EOBT

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
     * �ú������CRS��������
     */
    private boolean crsFlowPriority;

    /**
     * �ú������CDM��������
     */
    private boolean cdmFlowPriority;

    /**
     * �ú������CRS��������
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
     * �Ƿ�����ָ��ʱ϶����
     */
    private boolean assginFlow;

    public boolean isAssginFlow() {
        return assginFlow;
    }

    public void setAssginFlow(boolean assginFlow) {
        this.assginFlow = assginFlow;
    }

    /**
     * ���ӽ��̵�����
     */
    private EfpsFlight efpsFlight;

    public EfpsFlight getEfpsFlight() {
        return efpsFlight;
    }

    public void setEfpsFlight(EfpsFlight efpsFlight) {
        this.efpsFlight = efpsFlight;
    }

    /**
     * ������CTOTʱ��ʧЧ����
     * <p/>
     * key������ICAO��value��������ӦCTOTʧЧ���ڲ��������ӣ�
     */
    private static final Map<String, Integer> DepSlotErrorWin = new HashMap<String, Integer>();

    static {
        // ��ʼ������CTOTʱ��ʧЧ����  TODO ����д��
        DepSlotErrorWin.put("ZUUU", 5);
        DepSlotErrorWin.put("ZUCK", 5);
        DepSlotErrorWin.put("ZPPP", 5);
        DepSlotErrorWin.put("ZUGY", 5);
        DepSlotErrorWin.put("ZZZZ", 10);
    }

    /**
     *
     * @param ICAOAirport ����ICAO
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
     * ����CTOT��ʧЧʱ�䣬�������ڣ�����Null
     * <p/>
     * CTOTUnEffectTime = CTD + DepSlotErrorWin[Airport]
     * <p/>
     * DepSlotErrorWin Ĭ��ZZZZ��10min��ZUUU/ZUCK/ZUGY/ZPPP��5min
     *
     * @param nowTime ��ǰʱ��
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
     * ����EOBT-X�����TOBT
     */
    private String eobtWithDiff;

    /**
     * ����ǰ�����ʱ�����������׼�����ʱ��
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
     * ������ʱ��ʱ�����ܸı�FC.COBT
     */
    private String noCOBTChangeTime;

    public String getNoCOBTChangeTime() {
        return noCOBTChangeTime;
    }

    public void setNoCOBTChangeTime(String noCOBTChangeTime) {
        this.noCOBTChangeTime = noCOBTChangeTime;
    }

    /**
     * LDR ����
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
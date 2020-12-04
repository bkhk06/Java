package com.adcc.cdm.slot.bean;

import com.adcc.cdm.slot.service.SlotUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ����������Ϣ
 *
 * @author Zhaochen
 */
public class FlowControlInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private static Logger logger = Logger.getLogger(FlowControlInfo.class);

    /**
     * ��������-����
     */
    public static final String TYPE_TIME = "TIME";

    /**
     * ��������-������MIT
     * add by lihongwei 20191224 ���Ϸ���
     */
    public static final String TYPE_JOINT = "TOT_MIT";

    /**
     * ��������-ʱ��
     */
    public static final String TYPE_MIT = "MIT";

    /**
     * ��������-����ֹͣ
     */
    public static final String TYPE_GS = "GS";

    /**
     * ��������-ָ��ʱ϶
     */
    public static final String TYPE_ASSIGN = "ASSIGN";

    /**
     * ��������-��������
     */
    public static final String TYPE_REQ = "REQ";

    /**
     * ��������-���ģ����ָ�
     */
    public static final String TYPE_LDR = "LDR";

    /**
     * ��������-����ָ�
     */
    public static final String TYPE_TRANSLATION = "TRANSLATION";

    /**
     * ��������-����+MIT
     */
    public static final String TYPE_TOT_MIT = "TOT+MIT";

    /**
     * ���صص� -����
     */
    public static final String PLACE_TYPE_AP = "AP";

    /**
     * ���صص� -��·��
     */

    public static final String PLACE_TYPE_POINT = "POINT";

    /**
     * ���صص� -���ڵ���
     */
    public static final String PLACE_TYPE_ARR = "ARR";
    /**
     * ���صص� -���Ϸ���
     * add by lihongwei 20191224 �������Ϸ���
     */
    public static final String PLACE_TYPE_JOINT = "JOINT";


    /**
     * ��������-����
     */
    public static final String CONTROL_TYPE_WEATHER = "WEATHER";

    /**
     * ��������-�����
     */
    public static final String CONTROL_TYPE_MILITARY = "MILITARY";

    /**
     * ��������-����
     */
    public static final String CONTROL_TYPE_FLOW = "FLOW";

    /**
     * ��������-�豸����
     */
    public static final String CONTROL_TYPE_EQUIPMENT = "EQUIPMENT";

    /**
     * ��������-����
     */
    public static final String CONTROL_TYPE_OTHERS = "OTHERS";

    /**
     * ���ز�������-δ��Ч
     */
    public static final String CONTROL_OPERATION_UNEFFECT = "UNEFFECT";
    /**
     * ���ز�������-����Ч
     */
    public static final String CONTROL_OPERATION_PUBLISH = "PUBLISH";
    /**
     * ���ز�������-��ֹ
     */
    public static final String CONTROL_OPERATION_TERMINATED = "TERMINATED";
    /**
     * ���ز�������-ȡ��
     */
    public static final String CONTROL_OPERATION_CANCEL = "CANCEL";
    /**
     * ����ִ��
     */
    public static final String FLOWCONTROL_STATUS_RUNNING = "RUNNING";
    /**
     * ��Ҫִ��
     */
    public static final String FLOWCONTROL_STATUS_FUTURE = "FUTURE";
    /**
     * ��������
     */
    public static final String FLOWCONTROL_STATUS_FINISHED = "FINISHED";

    /**
     * ID
     */
    private Long id;

    private Long pid;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * ��������
     */
    private String name;

    /**
     * ���ؿ�ʼʱ��
     * <p/>
     * ʱ���ʽ��yyyyMMddHHmm
     */
    private String startTime;

    /**
     * ���ؽ���ʱ��
     * <p/>
     * ʱ���ʽ��yyyyMMddHHmm
     */
    private String endTime;

    /**
     * ����ʱ�� ʱ���ʽ:yyyyMMddHHmm
     */
    private String generateTime;

    /**
     * ����޸�ʱ��
     */
    private String lastModifyTime;

    /**
     * ������������(���Ӽ�� �� ������)
     */
    private String type;

    /**
     * �������ƺ�������ʱ��ֵ
     */
    private Long time;

    /**
     * �������ƺ������Ƽ��ֵ
     */
    private Long distance;

    /**
     * �������Ƿ�ӱ�����
     */
    private String doubleHEB;
    /**
     * �ܿص㼯�ϣ�ʹ�ö���(,)�ָ�
     */
    private String controlPoints;

    /**
     * �ܿؽ���������ϣ�ʹ�ö���(,)�ָ�
     */
    private String controlDirection;

    /**
     * ���⽵��������ϣ�ʹ�ö���(,)�ָ�
     */
    private String exemptDirection;

    /**
     * �ܿ���ɻ������ϣ�ʹ�ö���(,)�ָ�
     */
    private String controlDepDirection;

    /**
     * ������ɻ������ϣ�ʹ�ö���(,)�ָ�
     */
    private String exemptDepDirection;

    /**
     * �����������Ƹ߶�
     */
    private String controlLevel;

    /**
     * ����ԭ���������������豸��������
     */
    private String reason;

    /**
     * ���ر�ע����
     */
    private String comments;

    /**
     * ������Ϣ�����û���
     */
    private String publishUser;

    /**
     * ��ǰ������Ϣ״̬
     */
    private String status;

    /**
     * ��ǰ������Ϣ�������
     */
    private String operation;

    /**
     * �������ͣ���� or ���أ�
     */
    private Long flowcontrolType;
    /**
     * �������������ʼʱ��
     */
    private String startFlowCasaTime;

    /**
     * ���ط���
     */
    private String flowcontrolDirection;

    /**
     * ����ʱ϶
     */
    private String reserveSlots;

    /**
     * ���ػ������
     */
    private String exemptActype;

    /**
     * �����ܿػ���
     */
    private String controlActype;

    /**
     * �����������
     */
    private String groupQuota;

    /**
     * ���䷽ʽ
     */
    private String allocateType;

    public String getGroupQuota() {
        return groupQuota;
    }

    public void setGroupQuota(String groupQuota) {
        this.groupQuota = groupQuota;
    }

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

    public String getExemptActype() {
        return exemptActype;
    }

    public void setExemptActype(String exemptActype) {
        this.exemptActype = exemptActype;
    }

    public String getControlActype() {
        return controlActype;
    }

    public void setControlActype(String controlActype) {
        this.controlActype = controlActype;
    }

    public List<String> getReserveSlotsList() {

        List<String> resultList = new ArrayList<String>();
        if (!SlotUtil.isEmptyStr(this.getReserveSlots())) {
            String[] strs = this.getReserveSlots().split(",");
            Arrays.sort(strs);

            for (int i = 0; i < strs.length; i++) {
                resultList.add(strs[i]);
            }
        }

        return resultList;

    }

    public String getReserveSlots() {
        return reserveSlots;
    }

    public void setReserveSlots(String reserveSlots) {
        this.reserveSlots = reserveSlots;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getExemptPriority() {
        return exemptPriority;
    }

    public void setExemptPriority(String exemptPriority) {
        this.exemptPriority = exemptPriority;
    }

    private String priority;
    private String exemptPriority;

    public String getFlowcontrolDirection() {
        return flowcontrolDirection;
    }

    public void setFlowcontrolDirection(String flowcontrolDirection) {
        this.flowcontrolDirection = flowcontrolDirection;
    }

    public String getStartFlowCasaTime() {
        return startFlowCasaTime;
    }

    public void setStartFlowCasaTime(String startFlowCasaTime) {
        this.startFlowCasaTime = startFlowCasaTime;
    }

    public String getEndFlowCasaTime() {
        return endFlowCasaTime;
    }

    public void setEndFlowCasaTime(String endFlowCasaTime) {
        this.endFlowCasaTime = endFlowCasaTime;
    }

    /**
     * ������ֹ����ʱ��
     */
    private String endFlowCasaTime;

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    private String placeType;

    public Long getFlowcontrolType() {
        return flowcontrolType;
    }

    public void setFlowcontrolType(Long flowcontrolType) {
        this.flowcontrolType = flowcontrolType;
    }

    private String assignSlot;

    public String getAssignSlot() {
        return assignSlot;
    }

    public void setAssignSlot(String assignSlot) {
        this.assignSlot = assignSlot;
    }

    public Long getFormerid() {
        return formerid;
    }

    public void setFormerid(Long formerId) {
        this.formerid = formerId;
    }

    private Long formerid;

    /**
     *
     */
    public FlowControlInfo() {
        super();
    }

    /**
     * @param id
     * @param name
     * @param startTime
     * @param endTime
     * @param generateTime
     * @param lastModifyTime
     * @param type
     * @param time
     * @param distance
     * @param doubleHEB
     * @param controlPoints
     * @param controlDirection
     * @param exemptDirection
     * @param controlDepDirection
     * @param exemptDepDirection
     * @param controlLevel
     * @param reason
     * @param comments
     * @param status
     * @param operation
     * @param user
     */
    public FlowControlInfo(Long id, String name, String startTime, String endTime, String generateTime,
                           String lastModifyTime, String type, Long time, Long distance, String doubleHEB, String controlPoints,
                           String controlDirection, String exemptDirection, String controlDepDirection, String exemptDepDirection,
                           String controlLevel, String reason, String comments, String status, String operation, String user) {
        super();
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.generateTime = generateTime;
        this.lastModifyTime = lastModifyTime;
        this.type = type;
        this.time = time;
        this.distance = distance;
        this.doubleHEB = doubleHEB;
        this.controlPoints = controlPoints;
        this.controlDirection = controlDirection;
        this.exemptDepDirection = exemptDepDirection;
        this.controlDepDirection = controlDepDirection;
        this.exemptDirection = exemptDirection;
        this.controlLevel = controlLevel;
        this.reason = reason;
        this.comments = comments;
        this.status = status;
        this.operation = operation;
        this.publishUser = user;
    }

    public FlowControlInfo(String name, String startTime, String endTime, String generateTime, String lastModifyTime,
                           String type, Long time, Long distance, String doubleHEB, String controlPoints, String controlDirection,
                           String exemptDirection, String controlDepDirection, String exemptDepDirection, String controlLevel,
                           String reason, String comments, String status, String operation, String user) {
        super();
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.generateTime = generateTime;
        this.lastModifyTime = lastModifyTime;
        this.type = type;
        this.time = time;
        this.distance = distance;
        this.doubleHEB = doubleHEB;
        this.controlPoints = controlPoints;
        this.controlDirection = controlDirection;
        this.exemptDirection = exemptDirection;
        this.controlDepDirection = controlDepDirection;
        this.exemptDepDirection = exemptDepDirection;
        this.controlLevel = controlLevel;
        this.reason = reason;
        this.comments = comments;
        this.status = status;
        this.operation = operation;
        this.publishUser = user;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the generateTime
     */
    public String getGenerateTime() {
        return generateTime;
    }

    /**
     * @param generateTime the generateTime to set
     */
    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    /**
     * @return the lastModifyTime
     */
    public String getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * @param lastModifyTime the lastModifyTime to set
     */
    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the time
     */
    public Long getTime() {
        if ((this.time != null) && this.time > 1) {
            return time - 1;
        }

        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * @return the distance
     */
    public Long getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    /**
     * @return the doubleHEB
     */
    public String getDoubleHEB() {
        return doubleHEB;
    }

    /**
     * @param doubleHEB the doubleHEB to set
     */
    public void setDoubleHEB(String doubleHEB) {
        this.doubleHEB = doubleHEB;
    }

    /**
     * @return the controlPoints
     */
    public String getControlPoints() {
        if (controlPoints == null || controlPoints.trim().equals("")) {
            return "NONE";
        }
        return controlPoints;
    }

    /**
     * @param controlPoints the controlPoints to set
     */
    public void setControlPoints(String controlPoints) {
        this.controlPoints = controlPoints;
    }

    /**
     * @return the controlDirection
     */
    public String getControlDirection() {
        return controlDirection;
    }

    /**
     * @param controlDirection the controlDirection to set
     */
    public void setControlDirection(String controlDirection) {
        this.controlDirection = controlDirection;
    }

    /**
     * @return the exemptDirection
     */
    public String getExemptDirection() {
        return exemptDirection;
    }

    /**
     * @param exemptDirection the exemptDirection to set
     */
    public void setExemptDirection(String exemptDirection) {
        this.exemptDirection = exemptDirection;
    }

    /**
     * @return the controlDepDirection
     */
    public String getControlDepDirection() {
        return controlDepDirection;
    }

    /**
     * @param controlDepDirection the controlDepDirection to set
     */
    public void setControlDepDirection(String controlDepDirection) {
        this.controlDepDirection = controlDepDirection;
    }

    /**
     * @return the exemptDepDirection
     */
    public String getExemptDepDirection() {
        return exemptDepDirection;
    }

    /**
     * @param exemptDepDirection the exemptDepDirection to set
     */
    public void setExemptDepDirection(String exemptDepDirection) {
        this.exemptDepDirection = exemptDepDirection;
    }

    /**
     * @return the controlLevel
     */
    public String getControlLevel() {
        return controlLevel;
    }

    /**
     * @param controlLevel the controlLevel to set
     */
    public void setControlLevel(String controlLevel) {
        this.controlLevel = controlLevel;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the publishUser
     */
    public String getPublishUser() {
        return publishUser;
    }

    /**
     * @param publishUser the publishUser to set
     */
    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * ������Ϣ�е�ʱ��Ƭ��
     * <p/>
     * Ŀǰ���ڱ���GS�¶������ʱ��
     */
    private String timeSegment;

    public String getTimeSegment() {
        return timeSegment;
    }

    public void setTimeSegment(String timeSegment) {
        this.timeSegment = timeSegment;
    }

    /**
     * ������Դ
     */
    public static final String SOURCE_CDM = "CDM";

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("generateTime", generateTime)
                .append("lastModifyTime", lastModifyTime)
                .append("type", type)
                .append("time", time)
//                .append("distance", distance)
                .append("controlPoints", controlPoints)
                .append("controlDirection", controlDirection)
                .append("exemptDirection", exemptDirection)
                .append("controlDepDirection", controlDepDirection)
                .append("exemptDepDirection", exemptDepDirection)
                .append("flowcontrolDirection", flowcontrolDirection)
                .append("assignSlot", assignSlot)
                .append("reserveSlots", reserveSlots)
                .append("placeType", placeType)
                .append("flowcontrolType", flowcontrolType)
                .append("startFlowCasaTime", startFlowCasaTime)
                .append("endFlowCasaTime", endFlowCasaTime)
                .append("doubleHEB", doubleHEB)
                .append("priority", priority)
                .append("exemptPriority", exemptPriority)
                .append("rcfFlowType", rcfFlowType)
                .append("controlLevel", controlLevel)
                .append("formerid", formerid)
                .append("publishUser", publishUser)
                .append("reason", reason)
                .append("comments", comments)
                .toString();

        /*
        return "FlowControlInfo [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
                + ", generateTime=" + generateTime + ", lastModifyTime=" + lastModifyTime + ", type=" + type + ", time="
                + time + ", distance=" + distance + ", doubleHEB=" + doubleHEB + ", controlPoints=" + controlPoints
                + ", controlDirection=" + controlDirection + ", exemptDirection=" + exemptDirection
                + ", controlDepDirection=" + controlDepDirection + ", exemptDepDirection=" + exemptDepDirection
                + ", controlLevel=" + controlLevel + ", reason=" + reason + ", comments=" + comments + ", publishUser="
                + publishUser + ", status=" + status + ", operation=" + operation + ", flowcontrolType="
                + flowcontrolType + ", startFlowCasaTime=" + startFlowCasaTime + ", flowcontrolDirection="
                + flowcontrolDirection + ", reserveSlots=" + reserveSlots + ", priority=" + priority
                + ", exemptPriority=" + exemptPriority + ", endFlowCasaTime=" + endFlowCasaTime + ", placeType="
                + placeType + ", assignSlot=" + assignSlot + ", formerid=" + formerid + ", rcfFlowType=" + rcfFlowType
                + ", source=" + source + "]";
        */
    }

    public static final String SOURCE_CRS = "CRS";

    /**
     * RCF����Ӱ��ԭ��-���ر仯
     */
    public static final String RCF_CHG_FLOW_TYPE = "CHG";

    /**
     * RCF����Ӱ��ԭ��-
     */
    public static final String RCF_MER_FLOW_TYPE = "MER";

    /**
     * RCF����Ӱ��ԭ��
     */
    private String rcfFlowType = null;

    public String getRcfFlowType() {
        return rcfFlowType;
    }

    public void setRcfFlowType(String rcfFlowType) {
        this.rcfFlowType = rcfFlowType;
    }

    /**
     * ������Դ
     */
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * �ж��Ƿ��Ƕ���GS
     * �ж���������������ΪGS && ����ȷ����ʱ�� && ��Чʱ�䷶Χ������30����
     *
     * @return true���ǣ�false����
     */
    public boolean isShortGS() {
        // �ж�����
        if (this.getType().equals(FlowControlInfo.TYPE_GS)) {
            // �ж��Ƿ�����ֹʱ��
            if (SlotUtil.isNotEmptyStr(this.getEndTime())) {
                int GSDurationTime = SlotUtil.calculateStrTimeDiff(this.getEndTime(), this.getStartTime(), new SimpleDateFormat("yyyyMMddHHmm"), Calendar.MINUTE).intValue();
                // �ж������Ƿ�С��30����
                if (GSDurationTime <= 30) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ��Ӧ�ĸ�����
     */
    private Long sceneCaseId;

    public Long getSceneCaseId() {
        return sceneCaseId;
    }

    public void setSceneCaseId(Long sceneCaseId) {
        this.sceneCaseId = sceneCaseId;
    }

    /**
     * ��ʼǰѹ������
     */
    private String compressAtStartStrategy;

    /**
     * ��ʼǰѹ�������ڿ�ʼʱ��
     */
    private String compressAtStartWinStart;

    /**
     * ��ʼǰѹ�������ڽ���ʱ��
     */
    private String compressAtStartWinEnd;

    /**
     * ����ǰѹ������
     */
    private String compressAtEndStrategy;

    /**
     * ����ǰѹ�������ڿ�ʼʱ��
     */
    private String compressAtEndWinStart;

    /**
     * ����ǰѹ�������ڽ���ʱ��
     */
    private String compressAtEndWinEnd;

    /**
     * ������ѹ������
     */
    private String compressAtMidStrategy;

    /**
     * ������ѹ�������ڿ�ʼʱ��
     */
    private String compressAtMidWinStart;

    /**
     * ������ѹ�������ڽ���ʱ��
     */
    private String compressAtMidWinEnd;

    /**
     * �Ƿ�ѹ��
     */
    private String compressType;

    public String getCompressAtStartStrategy() {
        return compressAtStartStrategy;
    }

    public void setCompressAtStartStrategy(String compressAtStartStrategy) {
        this.compressAtStartStrategy = compressAtStartStrategy;
    }

    public String getCompressAtStartWinStart() {
        return compressAtStartWinStart;
    }

    public void setCompressAtStartWinStart(String compressAtStartWinStart) {
        this.compressAtStartWinStart = compressAtStartWinStart;
    }

    public String getCompressAtStartWinEnd() {
        return compressAtStartWinEnd;
    }

    public void setCompressAtStartWinEnd(String compressAtStartWinEnd) {
        this.compressAtStartWinEnd = compressAtStartWinEnd;
    }

    public String getCompressAtEndStrategy() {
        return compressAtEndStrategy;
    }

    public void setCompressAtEndStrategy(String compressAtEndStrategy) {
        this.compressAtEndStrategy = compressAtEndStrategy;
    }

    public String getCompressAtEndWinStart() {
        return compressAtEndWinStart;
    }

    public void setCompressAtEndWinStart(String compressAtEndWinStart) {
        this.compressAtEndWinStart = compressAtEndWinStart;
    }

    public String getCompressAtEndWinEnd() {
        return compressAtEndWinEnd;
    }

    public void setCompressAtEndWinEnd(String compressAtEndWinEnd) {
        this.compressAtEndWinEnd = compressAtEndWinEnd;
    }

    public String getCompressAtMidStrategy() {
        return compressAtMidStrategy;
    }

    public void setCompressAtMidStrategy(String compressAtMidStrategy) {
        this.compressAtMidStrategy = compressAtMidStrategy;
    }

    public String getCompressAtMidWinStart() {
        return compressAtMidWinStart;
    }

    public void setCompressAtMidWinStart(String compressAtMidWinStart) {
        this.compressAtMidWinStart = compressAtMidWinStart;
    }

    public String getCompressAtMidWinEnd() {
        return compressAtMidWinEnd;
    }

    public void setCompressAtMidWinEnd(String compressAtMidWinEnd) {
        this.compressAtMidWinEnd = compressAtMidWinEnd;
    }

    public String getCompressType() {
        return compressType;
    }

    public void setCompressType(String compressType) {
        this.compressType = compressType;
    }

    private String compositeCondition;

    public String getCompositeCondition() {
        return compositeCondition;
    }

    public void setCompositeCondition(String compositeCondition) {
        this.compositeCondition = compositeCondition;
    }

    public String getCompositeRelation() {
        return compositeRelation;
    }

    public void setCompositeRelation(String compositeRelation) {
        this.compositeRelation = compositeRelation;
    }

    public String getComposite() {
        return composite;
    }

    public void setComposite(String composite) {
        this.composite = composite;
    }

    private String compositeRelation;
    private String composite;
    private List<FlowControlInfo> subFlows;

    /**
     * ���������ظ��������е������ؼ���
     *
     * @return ������
     */
    public List<FlowControlInfo> getSubFlows() {
        if ("100".equals(this.getComposite())) {
            // ��������
            if (subFlows != null) {
                // �ѳ�ʼ��
                return subFlows;
            } else {
                subFlows = CompositeFlow.parseCompositeFlowcontrol(this); // ��ʼ��
                return subFlows;
            }
        }
        return subFlows;
    }

    /*
    public void setSubFlows(List<FlowControlInfo> subFlows) {
        this.subFlows = subFlows;
    }
    */

    /**
     * ѹ�����Գ���
     */
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_ALL = "ALL"; // ȫ������
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_PART = "PART"; // ��������
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_NONE = "NONE"; // ȫ������

    private List<Map<String, String>> timeSegments = null;

    public List<Map<String, String>> getTimeSegments() {

        if (timeSegments == null) {
            timeSegments = this.deserializeTimeSegment();
        }

        return timeSegments;
    }

    /**
     * ��JSON��ʽ��timeSegment���ԣ�ת��Ϊjava.util.List����
     *
     * @return �����л����timeSegment����
     */
    private List<Map<String, String>> deserializeTimeSegment() {
        try {
            return new Gson().fromJson(this.getTimeSegment(), new TypeToken<List<Map<String, String>>>() {
            }.getType());
        } catch (Exception e) {

            logger.error("FlowID " + this.getId() + " deserializeTimeSegment Exception", e);

        }
        return new ArrayList<Map<String, String>>();

    }

    /**
     * �жϼ��ʱ��
     *
     * @param checkTime        �����ʱ��
     * @param timeSegmentsList ʱ��Ƭ��
     * @param startTime        ��Ч��ʼʱ��
     * @param endTime          ��Ч����ʱ��
     * @return
     */
    public static String getHitTimeSegmentSlot(String checkTime, List<Map<String, String>> timeSegmentsList,
                                               String startTime, String endTime) {

        String result = null;
        // ��Сʱ��Ƭ�εĿ�ʼʱ��
        String minStartTime = "-1";
        // �ж���Ч��ʼʱ���Ƿ�Ϊ��
        if (SlotUtil.isEmptyStr(startTime)) {
            return result;
        }
        // �жϴ��ж�ʱ���Ƿ�Ϊ��
        if (SlotUtil.isEmptyStr(checkTime)) {
            return result;
        }
        // �ж�ʱ��Ƭ�������Ƿ�Ϊ��
        if (timeSegmentsList == null) {
            return result;
        } else if (timeSegmentsList.size() < 1) {
            return result;
        }
        // �Ƿ���������Чʱ���ڱ�ʾ
        boolean flag = false;
        // �ж���Ч����ʱ���Ƿ�Ϊ��
        if (SlotUtil.isEmptyStr(endTime)) {
            // �ж�ʱ���Ƿ�������ʱ�����
            if (checkTime.compareTo(startTime) >= 0) {
                flag = true;
            }
        } else {
            // �ж�ʱ���Ƿ�������ʱ�����
            if (checkTime.compareTo(startTime) >= 0 && checkTime.compareTo(endTime) <= 0) {
                flag = true;
            }
        }
        if (flag) {
            // ѭ��ȡ��ʱ��Ƭ��
            for (Map<String, String> map : timeSegmentsList) {
                // �жϵ���ʱ��Ƭ���Ƿ�Ϊ��
                if (map == null) {
                    continue;
                }
                // ʱ��Ƭ�εĿ�ʼʱ��
                String sTimeString = map.get("S");
                // ʱ��Ƭ�εĽ���ʱ��
                String eTimeString = map.get("E");
                // �жϵ���ʱ��Ƭ�ο�ʼʱ��ͽ���ʱ���Ƿ�Ϊ��
                if (SlotUtil.isEmptyStr(sTimeString)) {
                    continue;
                } else {
                    // ʱ��Ƭ�ν���ʱ��Ϊ��
                    if (SlotUtil.isEmptyStr(eTimeString)) {
                        eTimeString = endTime;
                    }
                    // �ж�������Ч����ʱ���Ƿ�Ϊ��
                    if (SlotUtil.isEmptyStr(endTime)) {
                        // �ж�ʱ��Ƭ���Ƿ���Ч
                        if ((!SlotUtil.isEmptyStr(eTimeString)) && ((sTimeString.compareTo(eTimeString) > 0)
                                || (eTimeString.compareTo(startTime) <= 0))) {
                            continue;
                        }
                    } else {
                        // �ж�ʱ��Ƭ���Ƿ���Ч
                        if ((sTimeString.compareTo(eTimeString) > 0) || (eTimeString.compareTo(startTime) <= 0)
                                || (sTimeString.compareTo(endTime) >= 0)) {
                            continue;
                        }
                        // �ж�ʱ��Ƭ�ν���ʱ�����Ч��
                        if (eTimeString.compareTo(endTime) >= 0) {
                            eTimeString = endTime;
                        }
                    }
                    // �ж�ʱ��Ƭ�ο�ʼʱ�����Ч��
                    if (sTimeString.compareTo(startTime) <= 0) {
                        sTimeString = startTime;
                    }
                    // �ж�ʱ���Ƿ���ʱ��Ƭ����
                    if (checkTime.compareTo(sTimeString) >= 0) {
                        // �ж�ʱ��Ƭ�ν���ʱ���Ƿ�Ϊ��
                        if (SlotUtil.isEmptyStr(eTimeString)) {
                            return checkTime;
                        } else {
                            if (checkTime.compareTo(eTimeString) <= 0) {
                                return checkTime;
                            }
                        }
                    } else {
                        // �ж�ʱ��Ƭ�ο�ʼʱ���Ƿ���ڴ�����ʱ��
                        if (checkTime.compareTo(sTimeString) <= 0) {
                            if (minStartTime.equals("-1")) {
                                minStartTime = sTimeString;
                                result = sTimeString;
                            } else {
                                // �ж���С��ʼʱ��
                                if (sTimeString.compareTo(minStartTime) <= 0) {
                                    minStartTime = sTimeString;
                                    result = sTimeString;
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}

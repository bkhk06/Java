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
 * 流量控制信息
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
     * 限制类型-距离
     */
    public static final String TYPE_TIME = "TIME";

    /**
     * 限制类型-总量加MIT
     * add by lihongwei 20191224 联合放行
     */
    public static final String TYPE_JOINT = "TOT_MIT";

    /**
     * 限制类型-时间
     */
    public static final String TYPE_MIT = "MIT";

    /**
     * 限制类型-地面停止
     */
    public static final String TYPE_GS = "GS";

    /**
     * 限制类型-指定时隙
     */
    public static final String TYPE_ASSIGN = "ASSIGN";

    /**
     * 限制类型-开车申请
     */
    public static final String TYPE_REQ = "REQ";

    /**
     * 限制类型-大规模延误恢复
     */
    public static final String TYPE_LDR = "LDR";

    /**
     * 限制类型-评议恢复
     */
    public static final String TYPE_TRANSLATION = "TRANSLATION";

    /**
     * 限制类型-总量+MIT
     */
    public static final String TYPE_TOT_MIT = "TOT+MIT";

    /**
     * 流控地点 -机场
     */
    public static final String PLACE_TYPE_AP = "AP";

    /**
     * 流控地点 -航路点
     */

    public static final String PLACE_TYPE_POINT = "POINT";

    /**
     * 流控地点 -区内到港
     */
    public static final String PLACE_TYPE_ARR = "ARR";
    /**
     * 流控地点 -联合放行
     * add by lihongwei 20191224 西部联合放行
     */
    public static final String PLACE_TYPE_JOINT = "JOINT";


    /**
     * 流控类型-天气
     */
    public static final String CONTROL_TYPE_WEATHER = "WEATHER";

    /**
     * 流控类型-军方活动
     */
    public static final String CONTROL_TYPE_MILITARY = "MILITARY";

    /**
     * 流控类型-流量
     */
    public static final String CONTROL_TYPE_FLOW = "FLOW";

    /**
     * 流控类型-设备故障
     */
    public static final String CONTROL_TYPE_EQUIPMENT = "EQUIPMENT";

    /**
     * 流控类型-其他
     */
    public static final String CONTROL_TYPE_OTHERS = "OTHERS";

    /**
     * 流控操作类型-未生效
     */
    public static final String CONTROL_OPERATION_UNEFFECT = "UNEFFECT";
    /**
     * 流控操作类型-已生效
     */
    public static final String CONTROL_OPERATION_PUBLISH = "PUBLISH";
    /**
     * 流控操作类型-终止
     */
    public static final String CONTROL_OPERATION_TERMINATED = "TERMINATED";
    /**
     * 流控操作类型-取消
     */
    public static final String CONTROL_OPERATION_CANCEL = "CANCEL";
    /**
     * 正在执行
     */
    public static final String FLOWCONTROL_STATUS_RUNNING = "RUNNING";
    /**
     * 将要执行
     */
    public static final String FLOWCONTROL_STATUS_FUTURE = "FUTURE";
    /**
     * 正常结束
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
     * 流控名称
     */
    private String name;

    /**
     * 流控开始时间
     * <p/>
     * 时间格式：yyyyMMddHHmm
     */
    private String startTime;

    /**
     * 流控结束时间
     * <p/>
     * 时间格式：yyyyMMddHHmm
     */
    private String endTime;

    /**
     * 发布时间 时间格式:yyyyMMddHHmm
     */
    private String generateTime;

    /**
     * 最后修改时间
     */
    private String lastModifyTime;

    /**
     * 流量控制类型(分钟间隔 或 距离间隔)
     */
    private String type;

    /**
     * 流量控制航班限制时间值
     */
    private Long time;

    /**
     * 流量控制航班限制间隔值
     */
    private Long distance;

    /**
     * 哈尔滨是否加倍流控
     */
    private String doubleHEB;
    /**
     * 受控点集合，使用逗号(,)分隔
     */
    private String controlPoints;

    /**
     * 受控降落机场集合，使用逗号(,)分隔
     */
    private String controlDirection;

    /**
     * 豁免降落机场集合，使用逗号(,)分隔
     */
    private String exemptDirection;

    /**
     * 受控起飞机场集合，使用逗号(,)分隔
     */
    private String controlDepDirection;

    /**
     * 豁免起飞机场集合，使用逗号(,)分隔
     */
    private String exemptDepDirection;

    /**
     * 流量控制限制高度
     */
    private String controlLevel;

    /**
     * 流控原因（天气、军方、设备、其他）
     */
    private String reason;

    /**
     * 流控备注内容
     */
    private String comments;

    /**
     * 流控信息发布用户名
     */
    private String publishUser;

    /**
     * 当前流控信息状态
     */
    private String status;

    /**
     * 当前流控信息操作情况
     */
    private String operation;

    /**
     * 流控类型（间隔 or 流控）
     */
    private Long flowcontrolType;
    /**
     * 流控纳入计算起始时间
     */
    private String startFlowCasaTime;

    /**
     * 流控方向
     */
    private String flowcontrolDirection;

    /**
     * 保留时隙
     */
    private String reserveSlots;

    /**
     * 流控豁免机型
     */
    private String exemptActype;

    /**
     * 流控受控机型
     */
    private String controlActype;

    /**
     * 地区分组配额
     */
    private String groupQuota;

    /**
     * 分配方式
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
     * 流控终止处理时间
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
     * 流控信息中的时间片段
     * <p/>
     * 目前用于保存GS下二类放行时间
     */
    private String timeSegment;

    public String getTimeSegment() {
        return timeSegment;
    }

    public void setTimeSegment(String timeSegment) {
        this.timeSegment = timeSegment;
    }

    /**
     * 流控来源
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
     * RCF流控影响原因-流控变化
     */
    public static final String RCF_CHG_FLOW_TYPE = "CHG";

    /**
     * RCF流控影响原因-
     */
    public static final String RCF_MER_FLOW_TYPE = "MER";

    /**
     * RCF流控影响原因
     */
    private String rcfFlowType = null;

    public String getRcfFlowType() {
        return rcfFlowType;
    }

    public void setRcfFlowType(String rcfFlowType) {
        this.rcfFlowType = rcfFlowType;
    }

    /**
     * 流控来源
     */
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 判断是否是短期GS
     * 判断条件：流控类型为GS && 有明确结束时间 && 生效时间范围不超过30分钟
     *
     * @return true：是，false：否
     */
    public boolean isShortGS() {
        // 判断类型
        if (this.getType().equals(FlowControlInfo.TYPE_GS)) {
            // 判断是否有终止时间
            if (SlotUtil.isNotEmptyStr(this.getEndTime())) {
                int GSDurationTime = SlotUtil.calculateStrTimeDiff(this.getEndTime(), this.getStartTime(), new SimpleDateFormat("yyyyMMddHHmm"), Calendar.MINUTE).intValue();
                // 判断期限是否小于30分钟
                if (GSDurationTime <= 30) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 对应哪个场景
     */
    private Long sceneCaseId;

    public Long getSceneCaseId() {
        return sceneCaseId;
    }

    public void setSceneCaseId(Long sceneCaseId) {
        this.sceneCaseId = sceneCaseId;
    }

    /**
     * 开始前压缩策略
     */
    private String compressAtStartStrategy;

    /**
     * 开始前压缩窗口期开始时间
     */
    private String compressAtStartWinStart;

    /**
     * 开始前压缩窗口期结束时间
     */
    private String compressAtStartWinEnd;

    /**
     * 结束前压缩策略
     */
    private String compressAtEndStrategy;

    /**
     * 结束前压缩窗口期开始时间
     */
    private String compressAtEndWinStart;

    /**
     * 结束前压缩窗口期结束时间
     */
    private String compressAtEndWinEnd;

    /**
     * 进行中压缩策略
     */
    private String compressAtMidStrategy;

    /**
     * 进行中压缩窗口期开始时间
     */
    private String compressAtMidWinStart;

    /**
     * 进行中压缩窗口期结束时间
     */
    private String compressAtMidWinEnd;

    /**
     * 是否压缩
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
     * 解析并返回复合流控中的子流控集合
     *
     * @return 子流控
     */
    public List<FlowControlInfo> getSubFlows() {
        if ("100".equals(this.getComposite())) {
            // 复合流控
            if (subFlows != null) {
                // 已初始化
                return subFlows;
            } else {
                subFlows = CompositeFlow.parseCompositeFlowcontrol(this); // 初始化
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
     * 压缩策略常量
     */
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_ALL = "ALL"; // 全部重排
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_PART = "PART"; // 部分重排
    public static final String FLOWCONTROL_COMPRESS_STRATEGY_NONE = "NONE"; // 全不重排

    private List<Map<String, String>> timeSegments = null;

    public List<Map<String, String>> getTimeSegments() {

        if (timeSegments == null) {
            timeSegments = this.deserializeTimeSegment();
        }

        return timeSegments;
    }

    /**
     * 将JSON格式的timeSegment属性，转换为java.util.List类型
     *
     * @return 反序列化后的timeSegment数据
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
     * 判断检查时间
     *
     * @param checkTime        待检查时间
     * @param timeSegmentsList 时间片段
     * @param startTime        生效开始时间
     * @param endTime          生效结束时间
     * @return
     */
    public static String getHitTimeSegmentSlot(String checkTime, List<Map<String, String>> timeSegmentsList,
                                               String startTime, String endTime) {

        String result = null;
        // 最小时间片段的开始时间
        String minStartTime = "-1";
        // 判断生效开始时间是否为空
        if (SlotUtil.isEmptyStr(startTime)) {
            return result;
        }
        // 判断待判断时间是否为空
        if (SlotUtil.isEmptyStr(checkTime)) {
            return result;
        }
        // 判断时间片段数据是否为空
        if (timeSegmentsList == null) {
            return result;
        } else if (timeSegmentsList.size() < 1) {
            return result;
        }
        // 是否在流控生效时间内表示
        boolean flag = false;
        // 判断生效结束时间是否为空
        if (SlotUtil.isEmptyStr(endTime)) {
            // 判断时间是否在流控时间段内
            if (checkTime.compareTo(startTime) >= 0) {
                flag = true;
            }
        } else {
            // 判断时间是否在流控时间段内
            if (checkTime.compareTo(startTime) >= 0 && checkTime.compareTo(endTime) <= 0) {
                flag = true;
            }
        }
        if (flag) {
            // 循环取出时间片段
            for (Map<String, String> map : timeSegmentsList) {
                // 判断单个时间片段是否为空
                if (map == null) {
                    continue;
                }
                // 时间片段的开始时间
                String sTimeString = map.get("S");
                // 时间片段的结束时间
                String eTimeString = map.get("E");
                // 判断单个时间片段开始时间和结束时间是否为空
                if (SlotUtil.isEmptyStr(sTimeString)) {
                    continue;
                } else {
                    // 时间片段结束时间为空
                    if (SlotUtil.isEmptyStr(eTimeString)) {
                        eTimeString = endTime;
                    }
                    // 判断流控生效结束时间是否为空
                    if (SlotUtil.isEmptyStr(endTime)) {
                        // 判断时间片段是否有效
                        if ((!SlotUtil.isEmptyStr(eTimeString)) && ((sTimeString.compareTo(eTimeString) > 0)
                                || (eTimeString.compareTo(startTime) <= 0))) {
                            continue;
                        }
                    } else {
                        // 判断时间片段是否有效
                        if ((sTimeString.compareTo(eTimeString) > 0) || (eTimeString.compareTo(startTime) <= 0)
                                || (sTimeString.compareTo(endTime) >= 0)) {
                            continue;
                        }
                        // 判断时间片段结束时间的有效性
                        if (eTimeString.compareTo(endTime) >= 0) {
                            eTimeString = endTime;
                        }
                    }
                    // 判断时间片段开始时间的有效性
                    if (sTimeString.compareTo(startTime) <= 0) {
                        sTimeString = startTime;
                    }
                    // 判断时间是否在时间片段内
                    if (checkTime.compareTo(sTimeString) >= 0) {
                        // 判断时间片段结束时间是否为空
                        if (SlotUtil.isEmptyStr(eTimeString)) {
                            return checkTime;
                        } else {
                            if (checkTime.compareTo(eTimeString) <= 0) {
                                return checkTime;
                            }
                        }
                    } else {
                        // 判断时间片段开始时间是否大于待检验时间
                        if (checkTime.compareTo(sTimeString) <= 0) {
                            if (minStartTime.equals("-1")) {
                                minStartTime = sTimeString;
                                result = sTimeString;
                            } else {
                                // 判断最小开始时间
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

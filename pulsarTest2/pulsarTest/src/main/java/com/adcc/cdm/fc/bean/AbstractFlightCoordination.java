/**
 *
 */
package com.adcc.cdm.fc.bean;

import com.adcc.cdm.slot.service.SlotUtil;
import com.adcc.ground.bean.GroundFlight;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;


/**
 * AbstractFlightCoordination entity provides the base persistence definition of
 * the FlightCoordination entity.
 *
 * @author MyEclipse Persistence Tools
 */
public abstract class AbstractFlightCoordination implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 优先级
     */
    public static final int PRIORITY_LDR = 80; // LDR
    public static final int PRIORITY_PRIVATE = 60; // 专机
    public static final int PRIORITY_VIP = 50; // 要客
    public static final int PRIORITY_EXEMPT = 48; // 豁免
    public static final int PRIORITY_ICE = 45; // 除冰
    public static final int PRIORITY_INTERNATIONAL = 40; // 国际
    public static final int PRIORITY_TO_INTERNATIONAL = 30; // 国内转国际
    public static final int PRIORITY_DELAY = 20; // 延误
    public static final int PRIORITY_AOC = 10; // 航空公司协调
    public static final int PRIORITY_NORMAL = 0; // 普通

    /**
     * 放行状态
     */
    public static final int CLEARANCE_UNAPPLY = 0; // 待申请
    public static final int CLEARANCE_CREW_APPLY = 200; // 机组已请求
    public static final int CLEARANCE_ENTRUST_APPLY = 300; // 已委托
    public static final int CLEARANCE_APPLYED = 400; // 已申请
    public static final int CLEARANCE_APPROVED = 500; // 已批复
    public static final int CLEARANCE_HANDOVER = 600; // 已移交

    /**
     * 放行数据类型
     */
    public static final int CLEARANCE_DEFAULT = 0;   // 默认
    public static final int CLEARANCE_FLIGHTS = 100; // 参与放行
    public static final int CLEARANCE_OVERFLY = 200; // 飞越
    public static final int CLEARANCE_EXCLUDE = 300; // Exlude

    /**
     * 航班类型
     */
    public static final int FLIGHT_DEFAULT = 0;   // 默认
    public static final int FLIGHT_DEPAP = 1; // 离港
    public static final int FLIGHT_ARRAP = 2; // 进港

    /**
     * 等待池状态
     */
    public static final int OUT_POOL = 0; // 未进等待池
    public static final int IN_POOL = 1; // 系统进等待池
    public static final int IN_POOL_M = 2; // 手动进等待池

    /**
     * 除冰状态
     */
    public static final int DEICE_OFF = 0; // 未除冰
    public static final int DEICE_ON = 1; // 除冰
    public static final int DEICE_OFF_MANUAL = 200; // 人工指定不除冰

    /**
     * COBT、CTD时间手动修改状态
     */
    public static final int UNLOCK = 0; // 未被手动修改
    public static final int LOCKED = 1; // 已被手动修改
    public static final int LOCKED_IMPACT = 2; // 已被手动修改，并影响其他航班
    public static final int LOCKED_NOSLOT = 3; // 标记为不分配时隙

    /**
     * 字段取值开关及顺序KEY值
     */
    public static final String FLIGHT_ASBT_KEY = "asbtSortSwitch";//上客时间
    public static final String FLIGHT_POSITION_KEY = "positionSortSwitch";//停机位
    public static final String FLIGHT_TOBT_KEY = "tobtSortSwitch";//TOBT

    /**
     * 数据ID主键
     */
    private Long id;

    /**
     * 数据库版本
     */
    private Integer version;

    /**
     * 目标撤轮档时间
     */
    private String tobt;

    /**
     * 预计撤轮档时间
     */
    private String eobt;

    /**
     * 计算撤轮档时间
     */
    private String cobt;

    /**
     * 协调关舱门/撤轮档时间
     */
    private String hobt;

    /**
     *
     */
    private String fobt;

    /**
     *
     */
    private String lcobt;

    /**
     * 实际撤轮档时间
     */
    private String aobt;

    /**
     * 预计起飞时间
     */
    private String etd;

    /**
     * 计算起飞时间
     */
    private String ctd;

    /**
     * 协调起飞时间
     */
    private String hctot;

    /**
     *
     */
    private String ftot;

    /**
     *
     */
    private String lctot;

    /**
     * 起飞航班经过APP FIX航路点的ID
     */
    private String controlInnerWaypointId;

    /**
     * 起飞航班经过APP FIX航路点的名称
     */
    private String controlInnerWaypointName;

    /**
     * 起飞航班经过APP FIX航路点的速度
     */
    private Long controlInnerWaypointSpeed;

    /**
     * 起飞航班预计过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String eto2;

    /**
     * 起飞航班计算过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String cto2;

    /**
     * 起飞航班实际过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String ato2;

    /**
     * 起飞航班经过ACC FIX航路点的ID
     */
    private Long controlWaypointId;

    /**
     * 起飞航班经过ACC FIX航路点的名称
     */
    private String controlWaypointName;

    /**
     * 起飞航班经过ACC FIX航路点的速度
     */
    private Long controlWaypointSpeed;

    /**
     * 起飞航班预计过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String eto;

    /**
     * 起飞航班计算过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String cto;

    /**
     * 起飞航班实际过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String ato;

    /**
     * 降落航班经过ACC FIX航路点的ID
     */
    private String desaccfixWaypointId;

    /**
     * 降落航班经过ACC FIX航路点的名称
     */
    private String desaccfixWaypointName;

    /**
     * 降落航班预计过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String cto3;

    /**
     * 降落航班计算过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String eto3;

    /**
     * 降落航班实际过ACC FIX时间，格式：yyyyMMddHHmm
     */
    private String ato3;

    /**
     * 降落航班经过APP FIX航路点的ID
     */
    private String desappfixWaypointId;

    /**
     * 降落航班经过APP FIX航路点的名称
     */
    private String desappfixWaypointName;

    /**
     * 降落航班预计过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String cto4;

    /**
     * 降落航班计算过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String eto4;

    /**
     * 降落航班实际过APP FIX时间，格式：yyyyMMddHHmm
     */
    private String ato4;

    /**
     * 起降航班航迹信息，格式：ID:POINT/E:yyyyMMddHHmmss/C:yyyyMMddHHmmss/A:yyyyMMddHHmmss?
     */
    private String monitorPointInfo;

    /**
     * 预计飞越EST报信息
     */
    private String estInfo;

    /**
     * 雷达修正标记位，1已修正
     */
    private Long isModify;

    /**
     * 雷达最新修正的时间，格式：yyyyMMddHHmm
     */
    private String updateTime;

    /**
     * 任务/优先级
     */
    private Integer priority;

    /**
     * 使用跑道
     */
    private String runway;

    /**
     * 上客时间
     */
    private String boardingTime;

    /**
     * 关舱门时间
     */
    private String closeTime;

    /**
     * 停机位
     */
    private String position;

    /**
     * 目标机场停机位
     */
    private String destinationPosition;


    /**
     * 除冰状态
     */
    private String deICE;

    /**
     * 除冰状态，是否需要除冰
     */
    private Integer deiceStatus;

    /**
     * 除冰位
     */
    private String deicePosition;

    /**
     * 除冰归属组
     */
    private String deiceGroup;

    /**
     * 入池状态
     */
    private Integer poolStatus;

    /**
     * 放行状态
     */
    private Integer clearanceStatus;

    /**
     * @deprecated 记录机组请求放行的时间
     */
    private String clearanceCrewApplyTime;

    /**
     * @deprecated 记录申请放行的时间
     */
    private String clearanceApplyTime;

    /**
     * @deprecated 记录批复放行的时间
     */
    private String clearanceApproveTime;

    /**
     * @deprecated 放行协调备注内容
     */
    private String clearanceComment;

    /**
     * 延误原因
     */
    private String delayReason;

    /**
     * 执行日
     */
    private String executedate;

    /**
     * RPS顺序起飞机场四码
     */
    private String depap;

    /**
     * RPS顺序降落机场四码
     */
    private String arrap;

    /**
     * 计划撤轮档时间
     */
    private String sobt;

    /**
     * 实际起飞时间
     */
    private String atd;

    /**
     *
     */
    private String fplUpdateTime;

    /**
     * 降落航班预计降落时间
     */
    private String eta;

    /**
     * 降落航班计算降落时间
     */
    private String cta;

    /**
     * GS-NOEND下OBT时间
     */
    private String gsobt;

    /**
     * 标记COBT、CTD是否被手动修改过
     * <p/>
     * 0:未被手动修改，1:已被手动修改
     */
    private Integer locked;

    /**
     * Route HashCode
     */
    private Integer routeHashCode;

    /**
     *
     */
    private String aobtAirline;

    /**
     * 内存字段 - 放行方向
     */
    private String clearanceDirection;

    /**
     * 内存字段  - 放行航班类型
     */
    private Integer clearanceType;

    /**
     * 内存字段 - 航班类型（离港，进港）
     */
    private Integer flightType;

    /**
     * 方向实体类
     */
    private FlightDirection direction;

    /**
     * 内存字段 - TOBTP
     */
    private String tobtp;

    /**
     * 半数间隔流控
     */
    private String intervalFlowcontrols;

    /**
     * 豁免流控
     */
    private String exemptFlowcontrols;

    public Integer getRouteHashCode() {
        return routeHashCode;
    }

    public void setRouteHashCode(Integer routeHashCode) {
        this.routeHashCode = routeHashCode;
    }

    /**
     * FME Call Sign
     */
    private String flightId;
    /**
     *
     */
    private Long formerId;

    /**
     * First EOBT
     */
    private String iobt;

    /**
     * 对应哪个场景
     */
    private Long sceneCaseId;

    /**
     * 飞行员资质
     * 2016/12/14 yangminxing
     */
    private String qualifications;

    /**
     * ACDM航班数据
     * 20190508 李宏伟
     */
    private GroundFlight ACDMFlight;

    /**
     * 字段取值顺序
     * 20190514 lihongwei
     */
    private Map<String, String> switchAndSortMap;

    /**
     * 起降航班航迹信息，格式：ID:POINT/S:yyyyMMddHHmmss?
     * 20191111
     */
    private String sMPI;

    public String getsMPI() {
        return sMPI;
    }

    public void setsMPI(String sMPI) {
        this.sMPI = sMPI;
    }

    public Map<String, String> getSwitchAndSortMap() {
        return switchAndSortMap;
    }

    public void setSwitchAndSortMap(Map<String, String> switchAndSortMap) {
        this.switchAndSortMap = switchAndSortMap;
    }

    public GroundFlight getACDMFlight() {
        return ACDMFlight;
    }

    public void setACDMFlight(GroundFlight ACDMFlight) {
        this.ACDMFlight = ACDMFlight;
    }

    public Long getSceneCaseId() {
        return sceneCaseId;
    }

    public void setSceneCaseId(Long sceneCaseId) {
        this.sceneCaseId = sceneCaseId;
    }

    public String getIobt() {
        return iobt;
    }

    public void setIobt(String iobt) {
        this.iobt = iobt;
    }

    public String getIfplUpdateTime() {
        return ifplUpdateTime;
    }

    public void setIfplUpdateTime(String ifplUpdateTime) {
        this.ifplUpdateTime = ifplUpdateTime;
    }

    private String ifplUpdateTime;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    /**
     *
     */
    public AbstractFlightCoordination() {
        super();
    }

    /**
     * @param id
     */
    public AbstractFlightCoordination(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param id
     * @param version
     * @param tobt
     * @param eobt
     * @param cobt
     * @param hobt
     * @param fobt
     * @param lcobt
     * @param etd
     * @param ctd
     * @param hctot
     * @param ftot
     * @param lctot
     * @param controlInnerWaypointId
     * @param controlInnerWaypointName
     * @param controlInnerWaypointSpeed
     * @param eto2
     * @param cto2
     * @param ato2
     * @param controlWaypointId
     * @param controlWaypointName
     * @param controlWaypointSpeed
     * @param eto
     * @param cto
     * @param ato
     * @param desaccfixWaypointId
     * @param desaccfixWaypointName
     * @param cto3
     * @param eto3
     * @param ato3
     * @param desappfixWaypointId
     * @param desappfixWaypointName
     * @param cto4
     * @param eto4
     * @param ato4
     * @param monitorPointInfo
     * @param estInfo
     * @param isModify
     * @param updateTime
     * @param priority
     * @param runway
     * @param boardingTime
     * @param closeTime
     * @param position
     * @param deICE
     * @param deicePosition
     * @param poolStatus
     * @param clearanceStatus
     * @param clearanceCrewApplyTime
     * @param clearanceApplyTime
     * @param clearanceApproveTime
     * @param clearanceComment
     * @param delayReason
     * @param executedate
     * @param depap
     * @param arrap
     * @param sobt
     * @param atd
     * @param fplUpdateTime
     * @param eta
     * @param cta
     */
    public AbstractFlightCoordination(Long id, Integer version, String tobt,
                                      String eobt, String cobt, String hobt, String fobt, String lcobt,
                                      String etd, String ctd, String hctot, String ftot, String lctot,
                                      String controlInnerWaypointId, String controlInnerWaypointName,
                                      Long controlInnerWaypointSpeed, String eto2, String cto2,
                                      String ato2, Long controlWaypointId, String controlWaypointName,
                                      Long controlWaypointSpeed, String eto, String cto, String ato,
                                      String desaccfixWaypointId, String desaccfixWaypointName,
                                      String cto3, String eto3, String ato3, String desappfixWaypointId,
                                      String desappfixWaypointName, String cto4, String eto4,
                                      String ato4, String monitorPointInfo, String estInfo,
                                      Long isModify, String updateTime, Integer priority, String runway,
                                      String boardingTime, String closeTime, String position,
                                      String deICE, String deicePosition, Integer poolStatus,
                                      Integer clearanceStatus, String clearanceCrewApplyTime,
                                      String clearanceApplyTime, String clearanceApproveTime,
                                      String clearanceComment, String delayReason, String executedate,
                                      String depap, String arrap, String sobt, String atd,
                                      String fplUpdateTime, String eta, String cta) {
        super();
        this.id = id;
        this.version = version;
        this.tobt = tobt;
        this.eobt = eobt;
        this.cobt = cobt;
        this.hobt = hobt;
        this.fobt = fobt;
        this.lcobt = lcobt;
        this.etd = etd;
        this.ctd = ctd;
        this.hctot = hctot;
        this.ftot = ftot;
        this.lctot = lctot;
        this.controlInnerWaypointId = controlInnerWaypointId;
        this.controlInnerWaypointName = controlInnerWaypointName;
        this.controlInnerWaypointSpeed = controlInnerWaypointSpeed;
        this.eto2 = eto2;
        this.cto2 = cto2;
        this.ato2 = ato2;
        this.controlWaypointId = controlWaypointId;
        this.controlWaypointName = controlWaypointName;
        this.controlWaypointSpeed = controlWaypointSpeed;
        this.eto = eto;
        this.cto = cto;
        this.ato = ato;
        this.desaccfixWaypointId = desaccfixWaypointId;
        this.desaccfixWaypointName = desaccfixWaypointName;
        this.cto3 = cto3;
        this.eto3 = eto3;
        this.ato3 = ato3;
        this.desappfixWaypointId = desappfixWaypointId;
        this.desappfixWaypointName = desappfixWaypointName;
        this.cto4 = cto4;
        this.eto4 = eto4;
        this.ato4 = ato4;
        this.monitorPointInfo = monitorPointInfo;
        this.estInfo = estInfo;
        this.isModify = isModify;
        this.updateTime = updateTime;
        this.priority = priority;
        this.runway = runway;
        this.boardingTime = boardingTime;
        this.closeTime = closeTime;
        this.position = position;
        this.deICE = deICE;
        this.deicePosition = deicePosition;
        this.poolStatus = poolStatus;
        this.clearanceStatus = clearanceStatus;
        this.clearanceCrewApplyTime = clearanceCrewApplyTime;
        this.clearanceApplyTime = clearanceApplyTime;
        this.clearanceApproveTime = clearanceApproveTime;
        this.clearanceComment = clearanceComment;
        this.delayReason = delayReason;
        this.executedate = executedate;
        this.depap = depap;
        this.arrap = arrap;
        this.sobt = sobt;
        this.atd = atd;
        this.fplUpdateTime = fplUpdateTime;
        this.eta = eta;
        this.cta = cta;
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
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @param tobt the tobt to set
     */
    public void setTobt(String tobt) {
        this.tobt = tobt;
    }

    /**
     * @return the eobt
     */
    public String getEobt() {
        return eobt;
    }

    /**
     * @param eobt the eobt to set
     */
    public void setEobt(String eobt) {
        this.eobt = eobt;
    }

    /**
     * @return the cobt
     */
    public String getCobt() {
        return cobt;
    }

    /**
     * @param cobt the cobt to set
     */
    public void setCobt(String cobt) {
        this.cobt = cobt;
    }

    /**
     * @return the hobt
     */
    public String getHobt() {
        return hobt;
    }

    /**
     * @param hobt the hobt to set
     */
    public void setHobt(String hobt) {
        this.hobt = hobt;
    }

    /**
     * @return the fobt
     */
    public String getFobt() {
        return fobt;
    }

    /**
     * @param fobt the fobt to set
     */
    public void setFobt(String fobt) {
        this.fobt = fobt;
    }

    /**
     * @return the lcobt
     */
    public String getLcobt() {
        return lcobt;
    }

    /**
     * @param lcobt the lcobt to set
     */
    public void setLcobt(String lcobt) {
        this.lcobt = lcobt;
    }

    /**
     * @return the etd
     */
    public String getEtd() {
        return etd;
    }

    /**
     * @param etd the etd to set
     */
    public void setEtd(String etd) {
        this.etd = etd;
    }

    /**
     * @return the ctd
     */
    public String getCtd() {
        return ctd;
    }

    /**
     * @param ctd the ctd to set
     */
    public void setCtd(String ctd) {
        this.ctd = ctd;
    }

    /**
     * @return the hctot
     */
    public String getHctot() {
        return hctot;
    }

    /**
     * @param hctot the hctot to set
     */
    public void setHctot(String hctot) {
        this.hctot = hctot;
    }

    /**
     * @return the ftot
     */
    public String getFtot() {
        return ftot;
    }

    /**
     * @param ftot the ftot to set
     */
    public void setFtot(String ftot) {
        this.ftot = ftot;
    }

    /**
     * @return the lctot
     */
    public String getLctot() {
        return lctot;
    }

    /**
     * @param lctot the lctot to set
     */
    public void setLctot(String lctot) {
        this.lctot = lctot;
    }

    /**
     * @return the aobt
     */
    public String getAobt() {
        return aobt;
    }

    /**
     * @param aobt the aobt to set
     */
    public void setAobt(String aobt) {
        this.aobt = aobt;
    }

    /**
     * @return the controlInnerWaypointId
     */
    public String getControlInnerWaypointId() {
        return controlInnerWaypointId;
    }

    /**
     * @param controlInnerWaypointId the controlInnerWaypointId to set
     */
    public void setControlInnerWaypointId(String controlInnerWaypointId) {
        this.controlInnerWaypointId = controlInnerWaypointId;
    }

    /**
     * @return the controlInnerWaypointName
     */
    public String getControlInnerWaypointName() {
        return controlInnerWaypointName;
    }

    /**
     * @param controlInnerWaypointName the controlInnerWaypointName to set
     */
    public void setControlInnerWaypointName(String controlInnerWaypointName) {
        this.controlInnerWaypointName = controlInnerWaypointName;
    }

    /**
     * @return the controlInnerWaypointSpeed
     */
    public Long getControlInnerWaypointSpeed() {
        return controlInnerWaypointSpeed;
    }

    /**
     * @param controlInnerWaypointSpeed the controlInnerWaypointSpeed to set
     */
    public void setControlInnerWaypointSpeed(Long controlInnerWaypointSpeed) {
        this.controlInnerWaypointSpeed = controlInnerWaypointSpeed;
    }

    /**
     * @return the eto2
     */
    public String getEto2() {
        return eto2;
    }

    /**
     * @param eto2 the eto2 to set
     */
    public void setEto2(String eto2) {
        this.eto2 = eto2;
    }

    /**
     * @return the cto2
     */
    public String getCto2() {
        return cto2;
    }

    /**
     * @param cto2 the cto2 to set
     */
    public void setCto2(String cto2) {
        this.cto2 = cto2;
    }

    /**
     * @return the ato2
     */
    public String getAto2() {
        return ato2;
    }

    /**
     * @param ato2 the ato2 to set
     */
    public void setAto2(String ato2) {
        this.ato2 = ato2;
    }

    /**
     * @return the controlWaypointId
     */
    public Long getControlWaypointId() {
        return controlWaypointId;
    }

    /**
     * @param controlWaypointId the controlWaypointId to set
     */
    public void setControlWaypointId(Long controlWaypointId) {
        this.controlWaypointId = controlWaypointId;
    }

    /**
     * @return the controlWaypointName
     */
    public String getControlWaypointName() {
        return controlWaypointName;
    }

    /**
     * @param controlWaypointName the controlWaypointName to set
     */
    public void setControlWaypointName(String controlWaypointName) {
        this.controlWaypointName = controlWaypointName;
    }

    /**
     * @return the controlWaypointSpeed
     */
    public Long getControlWaypointSpeed() {
        return controlWaypointSpeed;
    }

    /**
     * @param controlWaypointSpeed the controlWaypointSpeed to set
     */
    public void setControlWaypointSpeed(Long controlWaypointSpeed) {
        this.controlWaypointSpeed = controlWaypointSpeed;
    }

    /**
     * @return the eto
     */
    public String getEto() {
        return eto;
    }

    /**
     * @param eto the eto to set
     */
    public void setEto(String eto) {
        this.eto = eto;
    }

    /**
     * @return the cto
     */
    public String getCto() {
        return cto;
    }

    /**
     * @param cto the cto to set
     */
    public void setCto(String cto) {
        this.cto = cto;
    }

    /**
     * @return the ato
     */
    public String getAto() {
        return ato;
    }

    /**
     * @param ato the ato to set
     */
    public void setAto(String ato) {
        this.ato = ato;
    }

    /**
     * @return the desaccfixWaypointId
     */
    public String getDesaccfixWaypointId() {
        return desaccfixWaypointId;
    }

    /**
     * @param desaccfixWaypointId the desaccfixWaypointId to set
     */
    public void setDesaccfixWaypointId(String desaccfixWaypointId) {
        this.desaccfixWaypointId = desaccfixWaypointId;
    }

    /**
     * @return the desaccfixWaypointName
     */
    public String getDesaccfixWaypointName() {
        return desaccfixWaypointName;
    }

    /**
     * @param desaccfixWaypointName the desaccfixWaypointName to set
     */
    public void setDesaccfixWaypointName(String desaccfixWaypointName) {
        this.desaccfixWaypointName = desaccfixWaypointName;
    }

    /**
     * @return the cto3
     */
    public String getCto3() {
        return cto3;
    }

    /**
     * @param cto3 the cto3 to set
     */
    public void setCto3(String cto3) {
        this.cto3 = cto3;
    }

    /**
     * @return the eto3
     */
    public String getEto3() {
        return eto3;
    }

    /**
     * @param eto3 the eto3 to set
     */
    public void setEto3(String eto3) {
        this.eto3 = eto3;
    }

    /**
     * @return the ato3
     */
    public String getAto3() {
        return ato3;
    }

    /**
     * @param ato3 the ato3 to set
     */
    public void setAto3(String ato3) {
        this.ato3 = ato3;
    }

    /**
     * @return the desappfixWaypointId
     */
    public String getDesappfixWaypointId() {
        return desappfixWaypointId;
    }

    /**
     * @param desappfixWaypointId the desappfixWaypointId to set
     */
    public void setDesappfixWaypointId(String desappfixWaypointId) {
        this.desappfixWaypointId = desappfixWaypointId;
    }

    /**
     * @return the desappfixWaypointName
     */
    public String getDesappfixWaypointName() {
        return desappfixWaypointName;
    }

    /**
     * @param desappfixWaypointName the desappfixWaypointName to set
     */
    public void setDesappfixWaypointName(String desappfixWaypointName) {
        this.desappfixWaypointName = desappfixWaypointName;
    }

    /**
     * @return the cto4
     */
    public String getCto4() {
        return cto4;
    }

    /**
     * @param cto4 the cto4 to set
     */
    public void setCto4(String cto4) {
        this.cto4 = cto4;
    }

    /**
     * @return the eto4
     */
    public String getEto4() {
        return eto4;
    }

    /**
     * @param eto4 the eto4 to set
     */
    public void setEto4(String eto4) {
        this.eto4 = eto4;
    }

    /**
     * @return the ato4
     */
    public String getAto4() {
        return ato4;
    }

    /**
     * @param ato4 the ato4 to set
     */
    public void setAto4(String ato4) {
        this.ato4 = ato4;
    }

    /**
     * @return the monitorPointInfo
     */
    public String getMonitorPointInfo() {
        return monitorPointInfo;
    }

    /**
     * @param monitorPointInfo the monitorPointInfo to set
     */
    public void setMonitorPointInfo(String monitorPointInfo) {
        this.monitorPointInfo = monitorPointInfo;
    }

    /**
     * @return the estInfo
     */
    public String getEstInfo() {
        return estInfo;
    }

    /**
     * @param estInfo the estInfo to set
     */
    public void setEstInfo(String estInfo) {
        this.estInfo = estInfo;
    }

    /**
     * @return the isModify
     */
    public Long getIsModify() {
        return isModify;
    }

    /**
     * @param isModify the isModify to set
     */
    public void setIsModify(Long isModify) {
        this.isModify = isModify;
    }

    /**
     * @return the updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the runway
     */
    public String getRunway() {
        return runway;
    }

    /**
     * @param runway the runway to set
     */
    public void setRunway(String runway) {
        this.runway = runway;
    }

    /**
     * @param boardingTime the boardingTime to set
     */
    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }

    /**
     * @return the closeTime
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the deICE
     */
    public String getDeICE() {
        return deICE;
    }

    /**
     * @param deICE the deICE to set
     */
    public void setDeICE(String deICE) {
        this.deICE = deICE;
    }

    /**
     * @return the deicePosition
     */
    public String getDeicePosition() {
        return deicePosition;
    }

    /**
     * @param deicePosition the deicePosition to set
     */
    public void setDeicePosition(String deicePosition) {
        this.deicePosition = deicePosition;
    }

    /**
     * @return the poolStatus
     */
    public Integer getPoolStatus() {
        return poolStatus;
    }

    /**
     * @param poolStatus the poolStatus to set
     */
    public void setPoolStatus(Integer poolStatus) {
        this.poolStatus = poolStatus;
    }

    /**
     * @return the clearanceStatus
     */
    public Integer getClearanceStatus() {
        return clearanceStatus;
    }

    /**
     * @param clearanceStatus the clearanceStatus to set
     */
    public void setClearanceStatus(Integer clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
    }

    /**
     * @return the clearanceCrewApplyTime
     */
    public String getClearanceCrewApplyTime() {
        return clearanceCrewApplyTime;
    }

    /**
     * @param clearanceCrewApplyTime the clearanceCrewApplyTime to set
     */
    public void setClearanceCrewApplyTime(String clearanceCrewApplyTime) {
        this.clearanceCrewApplyTime = clearanceCrewApplyTime;
    }

    /**
     * @return the clearanceApplyTime
     */
    public String getClearanceApplyTime() {
        return clearanceApplyTime;
    }

    /**
     * @param clearanceApplyTime the clearanceApplyTime to set
     */
    public void setClearanceApplyTime(String clearanceApplyTime) {
        this.clearanceApplyTime = clearanceApplyTime;
    }

    /**
     * @return the clearanceApproveTime
     */
    public String getClearanceApproveTime() {
        return clearanceApproveTime;
    }

    /**
     * @param clearanceApproveTime the clearanceApproveTime to set
     */
    public void setClearanceApproveTime(String clearanceApproveTime) {
        this.clearanceApproveTime = clearanceApproveTime;
    }

    /**
     * @return the clearanceComment
     */
    public String getClearanceComment() {
        return clearanceComment;
    }

    /**
     * @param clearanceComment the clearanceComment to set
     */
    public void setClearanceComment(String clearanceComment) {
        this.clearanceComment = clearanceComment;
    }

    /**
     * @return the delayReason
     */
    public String getDelayReason() {
        return delayReason;
    }

    /**
     * @param delayReason the delayReason to set
     */
    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    /**
     * @return the executedate
     */
    public String getExecutedate() {
        return executedate;
    }

    /**
     * @param executedate the executedate to set
     */
    public void setExecutedate(String executedate) {
        this.executedate = executedate;
    }

    /**
     * @return the depap
     */
    public String getDepap() {
        return depap;
    }

    /**
     * @param depap the depap to set
     */
    public void setDepap(String depap) {
        this.depap = depap;
    }

    /**
     * @return the arrap
     */
    public String getArrap() {
        return arrap;
    }

    /**
     * @param arrap the arrap to set
     */
    public void setArrap(String arrap) {
        this.arrap = arrap;
    }

    /**
     * @return the sobt
     */
    public String getSobt() {
        return sobt;
    }

    /**
     * @param sobt the sobt to set
     */
    public void setSobt(String sobt) {
        this.sobt = sobt;
    }

    /**
     * @return the atd
     */
    public String getAtd() {
        return atd;
    }

    /**
     * @param atd the atd to set
     */
    public void setAtd(String atd) {
        this.atd = atd;
    }

    /**
     * @return the fplUpdateTime
     */
    public String getFplUpdateTime() {
        return fplUpdateTime;
    }

    /**
     * @param fplUpdateTime the fplUpdateTime to set
     */
    public void setFplUpdateTime(String fplUpdateTime) {
        this.fplUpdateTime = fplUpdateTime;
    }

    /**
     * @return the eta
     */
    public String getEta() {
        return eta;
    }

    /**
     * @param eta the eta to set
     */
    public void setEta(String eta) {
        this.eta = eta;
    }

    /**
     * @return the cta
     */
    public String getCta() {
        return cta;
    }

    /**
     * @param cta the cta to set
     */
    public void setCta(String cta) {
        this.cta = cta;
    }

    /**
     * @return the gsobt
     */
    public String getGsobt() {
        return gsobt;
    }

    /**
     * @param gsobt the gsobt to set
     */
    public void setGsobt(String gsobt) {
        this.gsobt = gsobt;
    }

    /**
     * @return the locked
     */
    public Integer getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * @return the deiceGroup
     */
    public String getDeiceGroup() {
        return deiceGroup;
    }

    /**
     * @param deiceGroup the deiceGroup to set
     */
    public void setDeiceGroup(String deiceGroup) {
        this.deiceGroup = deiceGroup;
    }

    /**
     * @return the deiceStatus
     */
    public Integer getDeiceStatus() {
        return deiceStatus;
    }

    /**
     * @param deiceStatus the deiceStatus to set
     */
    public void setDeiceStatus(Integer deiceStatus) {
        this.deiceStatus = deiceStatus;
    }


    /**
     * @return the destinationPosition
     */
    public String getDestinationPosition() {
        return destinationPosition;
    }

    /**
     * @param destinationPosition the destinationPosition to set
     */
    public void setDestinationPosition(String destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    /**
     * @return the formerId
     */
    public Long getFormerId() {
        return formerId;
    }

    /**
     * @param formerId the formerId to set
     */
    public void setFormerId(Long formerId) {
        this.formerId = formerId;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the clearanceDirection
     */
    public String getClearanceDirection() {
        return clearanceDirection;
    }

    /**
     * @param clearanceDirection the clearanceDirection to set
     */
    public void setClearanceDirection(String clearanceDirection) {
        this.clearanceDirection = clearanceDirection;
    }

    /**
     * @return the clearanceType
     */
    public Integer getClearanceType() {
        return clearanceType;
    }

    /**
     * @param clearanceType the clearanceType to set
     */
    public void setClearanceType(Integer clearanceType) {
        this.clearanceType = clearanceType;
    }

    /**
     * @return the tobtp
     */
    public String getTobtp() {
        return tobtp;
    }

    /**
     * @param tobtp the tobtp to set
     */
    public void setTobtp(String tobtp) {
        this.tobtp = tobtp;
    }

    /**
     * @return the flightType
     */
    public Integer getFlightType() {
        return flightType;
    }

    /**
     * @param flightType the flightType to set
     */
    public void setFlightType(Integer flightType) {
        this.flightType = flightType;
    }

    /**
     * @return the direction
     */
    public FlightDirection getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(FlightDirection direction) {
        this.direction = direction;
    }

    public String getAobtAirline() {
        return aobtAirline;
    }

    public void setAobtAirline(String aobtAirline) {
        this.aobtAirline = aobtAirline;
    }

    /**
     * TOBT 更新时间
     */
    private String tobtUpdateTime;

    public String getTobtUpdateTime() {
        return tobtUpdateTime;
    }

    public void setTobtUpdateTime(String tobtUpdateTime) {
        this.tobtUpdateTime = tobtUpdateTime;
    }

    public String getReady() {
        return ardtManual;
    }

    public void setReady(String ready) {
        this.ardtManual = ready;
    }

    /**
     * 准备完毕时间
     */
    private String ardtManual;

    public String getArdtManual() {
        return ardtManual;
    }

    public void setArdtManual(String ardtManual) {
        this.ardtManual = ardtManual;
    }

    /**
     * SlotRevised 次数
     */
    private Integer revisedCount;

    private Integer tobtCompareMinute;

    public Integer getTobtCompareMinute() {
        return tobtCompareMinute;
    }

    public void setTobtCompareMinute(Integer tobtCompareMinute) {
        this.tobtCompareMinute = tobtCompareMinute;
    }

    public Integer getRevisedCount() {
        return revisedCount;
    }

    public void setRevisedCount(Integer revisedCount) {
        this.revisedCount = revisedCount;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getIntervalFlowcontrols() {
        return intervalFlowcontrols;
    }

    public void setIntervalFlowcontrols(String intervalFlowcontrols) {
        this.intervalFlowcontrols = intervalFlowcontrols;
    }

    public String getExemptFlowcontrols() {
        return exemptFlowcontrols;
    }

    public void setExemptFlowcontrols(String exemptFlowcontrols) {
        this.exemptFlowcontrols = exemptFlowcontrols;
    }

    /**
     * @return the boardingTime
     * mod by lihongwei 20190514 西北ACDM数据使用
     * mod by linhao 20191127将原getBoardingTime(）getBoardingTimeByConfig（），解决西北ACDM数据会更新到数据库bug
     */
    public String getBoardingTimeByConfig() {
        try{
            if (switchAndSortMap.size() > 0 && switchAndSortMap.get(FLIGHT_ASBT_KEY) != null&&switchAndSortMap.get(FLIGHT_ASBT_KEY).length()>1) {
                for (String key : switchAndSortMap.get(FLIGHT_ASBT_KEY).split(",")) {
                    if ("M".equals(key) && SlotUtil.isNotEmptyStr(boardingTime)) {
                        return boardingTime;
                    } else if ("A".equals(key) && ACDMFlight != null  && SlotUtil.isNotEmptyStr(ACDMFlight.getAsbtAcdm())) {
                        return ACDMFlight.getAsbtAcdm();
                    }
                }
            } else {
                return boardingTime;
            }
        }catch (Exception ee){
            return boardingTime;
        }
        return boardingTime;
    }

    /**
     * @return the position
     * mod by lihongwei 20190514 西北ACDM数据使用
     * mod by linhao 20191127将原getPosition(）重命名 getPositionByConfig（），解决西北ACDM数据会更新到数据库bug
     */
    public String getPositionByConfig() {
        try{
            if (switchAndSortMap.size() > 0 && switchAndSortMap.get(FLIGHT_POSITION_KEY) != null&&switchAndSortMap.get(FLIGHT_POSITION_KEY).length()>1) {
//            if (MapUtils.isNotEmpty(switchAndSortMap) && SlotUtil.isNotEmptyStr(switchAndSortMap.get(FLIGHT_POSITION_KEY))) {
                for (String key : switchAndSortMap.get(FLIGHT_POSITION_KEY).split(",")) {
                    if ("M".equals(key) && SlotUtil.isNotEmptyStr(position)) {
                        return position;
                    } else if ("A".equals(key) && ACDMFlight != null  && SlotUtil.isNotEmptyStr(ACDMFlight.getStandAcdm())) {
                        return ACDMFlight.getStandAcdm();
                    }
                }
            } else {
                return position;
            }
        }catch (Exception ee){
            return position;
        }
        return position;
    }

    /**
     * @return the tobt
     * mod by lihongwei 20190514 西北ACDM数据使用
     * mod by linhao 20191127将原getTobt()重命名 getTobtByConfig（），解决西北ACDM数据会更新到数据库bug
     */
    public String getTobtByConfig() {
        try {
            if (switchAndSortMap.size() > 0 && switchAndSortMap.get(FLIGHT_TOBT_KEY) != null&&switchAndSortMap.get(FLIGHT_TOBT_KEY).length()>1) {
                for (String key : switchAndSortMap.get(FLIGHT_TOBT_KEY).split(",")) {
                    if ("M".equals(key) && SlotUtil.isNotEmptyStr(tobt)) {
                        return tobt;
                    } else if ("B".equals(key)) {
                        String realTobt = compareData(getEobt(), getACDMFlight().getTobtFusionAcdm());
                        if (SlotUtil.isNotEmptyStr(realTobt)) {
                            return realTobt;
                        }
                    } else if ("A".equals(key) && ACDMFlight != null && SlotUtil.isNotEmptyStr(ACDMFlight.getTobtAcdm())) {
                        return ACDMFlight.getTobtAcdm();
                    }
                }

            } else {
                return tobt;
            }

        } catch (Exception ee) {
            return tobt;
        }
        return tobt;
    }

    public String compareData(String eobt, String aTobt) {
        int compareValue = getTobtCompareMinute();
        if (StringUtils.isNotBlank(eobt) && StringUtils.isNotBlank(aTobt)) {
            if (aTobt.compareTo(SlotUtil.getTimeAddMin(eobt, new SimpleDateFormat("yyyyMMddHHmm"),compareValue)) >= 0) {
                return aTobt;
            }
        }
        return null;
    }

    public String getTobt() {
        return tobt;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public String getPosition() {
        return position;
    }

    /**
     * SlotRevised 次数
     */
    private String cldt;

	public String getCldt() {
		return cldt;
	}

	public void setCldt(String cldt) {
		this.cldt = cldt;
	}
    
	private String ildt;

	public String getIldt() {
		return ildt;
	}

	public void setIldt(String ildt) {
		this.ildt = ildt;
	}

	private String spasttime;

	public String getSpasttime() {
		return spasttime;
	}

	public void setSpasttime(String spasttime) {
		this.spasttime = spasttime;
	}

    /**
     * 航班状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 配额CTO
     */
    private String  jointCto;

    public String getJointCto() {
        return jointCto;
    }

    public void setJointCto(String jointCto) {
        this.jointCto = jointCto;
    }
}
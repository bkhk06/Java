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
     * ���ȼ�
     */
    public static final int PRIORITY_LDR = 80; // LDR
    public static final int PRIORITY_PRIVATE = 60; // ר��
    public static final int PRIORITY_VIP = 50; // Ҫ��
    public static final int PRIORITY_EXEMPT = 48; // ����
    public static final int PRIORITY_ICE = 45; // ����
    public static final int PRIORITY_INTERNATIONAL = 40; // ����
    public static final int PRIORITY_TO_INTERNATIONAL = 30; // ����ת����
    public static final int PRIORITY_DELAY = 20; // ����
    public static final int PRIORITY_AOC = 10; // ���չ�˾Э��
    public static final int PRIORITY_NORMAL = 0; // ��ͨ

    /**
     * ����״̬
     */
    public static final int CLEARANCE_UNAPPLY = 0; // ������
    public static final int CLEARANCE_CREW_APPLY = 200; // ����������
    public static final int CLEARANCE_ENTRUST_APPLY = 300; // ��ί��
    public static final int CLEARANCE_APPLYED = 400; // ������
    public static final int CLEARANCE_APPROVED = 500; // ������
    public static final int CLEARANCE_HANDOVER = 600; // ���ƽ�

    /**
     * ������������
     */
    public static final int CLEARANCE_DEFAULT = 0;   // Ĭ��
    public static final int CLEARANCE_FLIGHTS = 100; // �������
    public static final int CLEARANCE_OVERFLY = 200; // ��Խ
    public static final int CLEARANCE_EXCLUDE = 300; // Exlude

    /**
     * ��������
     */
    public static final int FLIGHT_DEFAULT = 0;   // Ĭ��
    public static final int FLIGHT_DEPAP = 1; // ���
    public static final int FLIGHT_ARRAP = 2; // ����

    /**
     * �ȴ���״̬
     */
    public static final int OUT_POOL = 0; // δ���ȴ���
    public static final int IN_POOL = 1; // ϵͳ���ȴ���
    public static final int IN_POOL_M = 2; // �ֶ����ȴ���

    /**
     * ����״̬
     */
    public static final int DEICE_OFF = 0; // δ����
    public static final int DEICE_ON = 1; // ����
    public static final int DEICE_OFF_MANUAL = 200; // �˹�ָ��������

    /**
     * COBT��CTDʱ���ֶ��޸�״̬
     */
    public static final int UNLOCK = 0; // δ���ֶ��޸�
    public static final int LOCKED = 1; // �ѱ��ֶ��޸�
    public static final int LOCKED_IMPACT = 2; // �ѱ��ֶ��޸ģ���Ӱ����������
    public static final int LOCKED_NOSLOT = 3; // ���Ϊ������ʱ϶

    /**
     * �ֶ�ȡֵ���ؼ�˳��KEYֵ
     */
    public static final String FLIGHT_ASBT_KEY = "asbtSortSwitch";//�Ͽ�ʱ��
    public static final String FLIGHT_POSITION_KEY = "positionSortSwitch";//ͣ��λ
    public static final String FLIGHT_TOBT_KEY = "tobtSortSwitch";//TOBT

    /**
     * ����ID����
     */
    private Long id;

    /**
     * ���ݿ�汾
     */
    private Integer version;

    /**
     * Ŀ�곷�ֵ�ʱ��
     */
    private String tobt;

    /**
     * Ԥ�Ƴ��ֵ�ʱ��
     */
    private String eobt;

    /**
     * ���㳷�ֵ�ʱ��
     */
    private String cobt;

    /**
     * Э���ز���/���ֵ�ʱ��
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
     * ʵ�ʳ��ֵ�ʱ��
     */
    private String aobt;

    /**
     * Ԥ�����ʱ��
     */
    private String etd;

    /**
     * �������ʱ��
     */
    private String ctd;

    /**
     * Э�����ʱ��
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
     * ��ɺ��ྭ��APP FIX��·���ID
     */
    private String controlInnerWaypointId;

    /**
     * ��ɺ��ྭ��APP FIX��·�������
     */
    private String controlInnerWaypointName;

    /**
     * ��ɺ��ྭ��APP FIX��·����ٶ�
     */
    private Long controlInnerWaypointSpeed;

    /**
     * ��ɺ���Ԥ�ƹ�APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String eto2;

    /**
     * ��ɺ�������APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String cto2;

    /**
     * ��ɺ���ʵ�ʹ�APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String ato2;

    /**
     * ��ɺ��ྭ��ACC FIX��·���ID
     */
    private Long controlWaypointId;

    /**
     * ��ɺ��ྭ��ACC FIX��·�������
     */
    private String controlWaypointName;

    /**
     * ��ɺ��ྭ��ACC FIX��·����ٶ�
     */
    private Long controlWaypointSpeed;

    /**
     * ��ɺ���Ԥ�ƹ�ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String eto;

    /**
     * ��ɺ�������ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String cto;

    /**
     * ��ɺ���ʵ�ʹ�ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String ato;

    /**
     * ���亽�ྭ��ACC FIX��·���ID
     */
    private String desaccfixWaypointId;

    /**
     * ���亽�ྭ��ACC FIX��·�������
     */
    private String desaccfixWaypointName;

    /**
     * ���亽��Ԥ�ƹ�ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String cto3;

    /**
     * ���亽������ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String eto3;

    /**
     * ���亽��ʵ�ʹ�ACC FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String ato3;

    /**
     * ���亽�ྭ��APP FIX��·���ID
     */
    private String desappfixWaypointId;

    /**
     * ���亽�ྭ��APP FIX��·�������
     */
    private String desappfixWaypointName;

    /**
     * ���亽��Ԥ�ƹ�APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String cto4;

    /**
     * ���亽������APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String eto4;

    /**
     * ���亽��ʵ�ʹ�APP FIXʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String ato4;

    /**
     * �𽵺��ຽ����Ϣ����ʽ��ID:POINT/E:yyyyMMddHHmmss/C:yyyyMMddHHmmss/A:yyyyMMddHHmmss?
     */
    private String monitorPointInfo;

    /**
     * Ԥ�Ʒ�ԽEST����Ϣ
     */
    private String estInfo;

    /**
     * �״��������λ��1������
     */
    private Long isModify;

    /**
     * �״�����������ʱ�䣬��ʽ��yyyyMMddHHmm
     */
    private String updateTime;

    /**
     * ����/���ȼ�
     */
    private Integer priority;

    /**
     * ʹ���ܵ�
     */
    private String runway;

    /**
     * �Ͽ�ʱ��
     */
    private String boardingTime;

    /**
     * �ز���ʱ��
     */
    private String closeTime;

    /**
     * ͣ��λ
     */
    private String position;

    /**
     * Ŀ�����ͣ��λ
     */
    private String destinationPosition;


    /**
     * ����״̬
     */
    private String deICE;

    /**
     * ����״̬���Ƿ���Ҫ����
     */
    private Integer deiceStatus;

    /**
     * ����λ
     */
    private String deicePosition;

    /**
     * ����������
     */
    private String deiceGroup;

    /**
     * ���״̬
     */
    private Integer poolStatus;

    /**
     * ����״̬
     */
    private Integer clearanceStatus;

    /**
     * @deprecated ��¼����������е�ʱ��
     */
    private String clearanceCrewApplyTime;

    /**
     * @deprecated ��¼������е�ʱ��
     */
    private String clearanceApplyTime;

    /**
     * @deprecated ��¼�������е�ʱ��
     */
    private String clearanceApproveTime;

    /**
     * @deprecated ����Э����ע����
     */
    private String clearanceComment;

    /**
     * ����ԭ��
     */
    private String delayReason;

    /**
     * ִ����
     */
    private String executedate;

    /**
     * RPS˳����ɻ�������
     */
    private String depap;

    /**
     * RPS˳�����������
     */
    private String arrap;

    /**
     * �ƻ����ֵ�ʱ��
     */
    private String sobt;

    /**
     * ʵ�����ʱ��
     */
    private String atd;

    /**
     *
     */
    private String fplUpdateTime;

    /**
     * ���亽��Ԥ�ƽ���ʱ��
     */
    private String eta;

    /**
     * ���亽����㽵��ʱ��
     */
    private String cta;

    /**
     * GS-NOEND��OBTʱ��
     */
    private String gsobt;

    /**
     * ���COBT��CTD�Ƿ��ֶ��޸Ĺ�
     * <p/>
     * 0:δ���ֶ��޸ģ�1:�ѱ��ֶ��޸�
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
     * �ڴ��ֶ� - ���з���
     */
    private String clearanceDirection;

    /**
     * �ڴ��ֶ�  - ���к�������
     */
    private Integer clearanceType;

    /**
     * �ڴ��ֶ� - �������ͣ���ۣ����ۣ�
     */
    private Integer flightType;

    /**
     * ����ʵ����
     */
    private FlightDirection direction;

    /**
     * �ڴ��ֶ� - TOBTP
     */
    private String tobtp;

    /**
     * �����������
     */
    private String intervalFlowcontrols;

    /**
     * ��������
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
     * ��Ӧ�ĸ�����
     */
    private Long sceneCaseId;

    /**
     * ����Ա����
     * 2016/12/14 yangminxing
     */
    private String qualifications;

    /**
     * ACDM��������
     * 20190508 ���ΰ
     */
    private GroundFlight ACDMFlight;

    /**
     * �ֶ�ȡֵ˳��
     * 20190514 lihongwei
     */
    private Map<String, String> switchAndSortMap;

    /**
     * �𽵺��ຽ����Ϣ����ʽ��ID:POINT/S:yyyyMMddHHmmss?
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
     * TOBT ����ʱ��
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
     * ׼�����ʱ��
     */
    private String ardtManual;

    public String getArdtManual() {
        return ardtManual;
    }

    public void setArdtManual(String ardtManual) {
        this.ardtManual = ardtManual;
    }

    /**
     * SlotRevised ����
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
     * mod by lihongwei 20190514 ����ACDM����ʹ��
     * mod by linhao 20191127��ԭgetBoardingTime(��getBoardingTimeByConfig�������������ACDM���ݻ���µ����ݿ�bug
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
     * mod by lihongwei 20190514 ����ACDM����ʹ��
     * mod by linhao 20191127��ԭgetPosition(�������� getPositionByConfig�������������ACDM���ݻ���µ����ݿ�bug
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
     * mod by lihongwei 20190514 ����ACDM����ʹ��
     * mod by linhao 20191127��ԭgetTobt()������ getTobtByConfig�������������ACDM���ݻ���µ����ݿ�bug
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
     * SlotRevised ����
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
     * ����״̬
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * ���CTO
     */
    private String  jointCto;

    public String getJointCto() {
        return jointCto;
    }

    public void setJointCto(String jointCto) {
        this.jointCto = jointCto;
    }
}
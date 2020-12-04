package com.adcc.cdm.slot.bean;

/**
 * AbstractFlightAutoslot entity provides the base persistence definition of the
 * FlightAutoslot entity.
 *
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractFlightAutoslot implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Fields
    /**
     * 放行状态-待申请
     */
    public static final int CDM = 1;

    public static final int CRS = 2;
    // Fields

    private Long id;
    private String ctd;
    private String cto2;
    private String cto;
    private String cto3;
    private String cto4;
    private String cobt;
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

    public String getCto3() {
        return cto3;
    }

    public void setCto3(String cto3) {
        this.cto3 = cto3;
    }

    public String getCto4() {
        return cto4;
    }

    public void setCto4(String cto4) {
        this.cto4 = cto4;
    }

    private Integer source;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCobt2() {
        return cobt2;
    }

    public void setCobt2(String cobt2) {
        this.cobt2 = cobt2;
    }

    public String getCtot2() {
        return ctot2;
    }

    public void setCtot2(String ctot2) {
        this.ctot2 = ctot2;
    }

    private String cobt2;
    private String ctot2;

    public String getCobt() {
        return cobt;
    }

    public void setCobt(String cobt) {
        this.cobt = cobt;
    }

    public String getMpCto() {
        return mpCto;
    }

    public void setMpCto(String mpCto) {
        this.mpCto = mpCto;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }

    public String getGenTime2() {
        return genTime2;
    }

    public void setGenTime2(String genTime2) {
        this.genTime2 = genTime2;
    }

    private String mpCto;
    private String genTime;
    private String genTime2;

    // Constructors

    /**
     * default constructor
     */
    public AbstractFlightAutoslot() {
    }

    /**
     * minimal constructor
     */
    public AbstractFlightAutoslot(Long id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public AbstractFlightAutoslot(Long id, String ctd, String cto2, String cto) {
        this.id = id;
        this.ctd = ctd;
        this.cto2 = cto2;
        this.cto = cto;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCtd() {
        return this.ctd;
    }

    public void setCtd(String ctd) {
        this.ctd = ctd;
    }

    public String getCto2() {
        return this.cto2;
    }

    public void setCto2(String cto2) {
        this.cto2 = cto2;
    }

    public String getCto() {
        return this.cto;
    }

    public void setCto(String cto) {
        this.cto = cto;
    }

    private String reason;


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
}
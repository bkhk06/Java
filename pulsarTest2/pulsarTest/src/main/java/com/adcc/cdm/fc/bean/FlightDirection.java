package com.adcc.cdm.fc.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * FC对象中规则方向
 *
 * @author zhaohaojie
 */
public class FlightDirection implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 内存字段 - 放行方向
     */
    private String clearanceDirection;

    /**
     * 内存字段 - 放行方向类型
     */
    private String clearanceDirectionType;

    /**
     * 内存字段 - 放行航班类型
     */
    private Integer clearanceType;

    /**
     * 内存字段 - 所有方向
     */
    private Set<String> clearanceDirections;

    /**
     * 内存字段 - 航班类型（离港，进港）
     */
    private Integer flightType;

    /**
     * 内存字段 - 放行方向中文描述
     */
    private String clearanceDirectionZh;

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
     * @return the clearanceDirectionType
     */
    public String getClearanceDirectionType() {
        return clearanceDirectionType;
    }

    /**
     * @param clearanceDirectionType the clearanceDirectionType to set
     */
    public void setClearanceDirectionType(String clearanceDirectionType) {
        this.clearanceDirectionType = clearanceDirectionType;
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
     * @return the clearanceDirections
     */
    public Set<String> getClearanceDirections() {
        return clearanceDirections;
    }

    /**
     * @param clearanceDirections the clearanceDirections to set
     */
    public void setClearanceDirections(Set<String> clearanceDirections) {
        this.clearanceDirections = clearanceDirections;
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
     * @return the clearanceDirectionZh
     */
    public String getClearanceDirectionZh() {
        return clearanceDirectionZh;
    }

    /**
     * @param clearanceDirectionZh the clearanceDirectionZh to set
     */
    public void setClearanceDirectionZh(String clearanceDirectionZh) {
        this.clearanceDirectionZh = clearanceDirectionZh;
    }
}

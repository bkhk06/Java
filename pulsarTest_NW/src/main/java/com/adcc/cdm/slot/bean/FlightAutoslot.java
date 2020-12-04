package com.adcc.cdm.slot.bean;

/**
 * FlightAutoslot entity.
 *
 * @author MyEclipse Persistence Tools
 */
public class FlightAutoslot extends AbstractFlightAutoslot implements
        java.io.Serializable {

    // Constructors

    @Override
    public String toString() {
        return "FlightAutoslot [getSceneCaseId()=" + getSceneCaseId()
                + ", getCobt()=" + getCobt() + ", getMpCto()=" + getMpCto()
                + ", getGenTime()=" + getGenTime() + ", getId()=" + getId()
                + ", getCtd()=" + getCtd() + "]";
    }

    /**
     * default constructor
     */
    public FlightAutoslot() {
    }

    /**
     * minimal constructor
     */
    public FlightAutoslot(Long id) {
        super(id);
    }

    /**
     * full constructor
     */
    public FlightAutoslot(Long id, String ctd, String cto2, String cto) {
        super(id, ctd, cto2, cto);
    }


}

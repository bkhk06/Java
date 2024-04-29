package com.adcc.restresponse;

//import org.springframework.data.annotation.Id;
//import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;

@Entity
@Table(name = "response_times")
public class ResponseTimeEntity /*implements Serializable*/ {

    @Id
    //@Column(name="id",unique=true,nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="api_endpoint")
    private String apiEndpoint;

    @Column(name="response_time")
    private Double responseTime;

    @Column(name="response_code")
    private int responseCode;

    @Column(name="timestamp")
    private Timestamp timestamp;

    public String getId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public Double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = (double) responseTime;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResponseTime [id=" + id + ", apiEndpoint=" + apiEndpoint + ", responseTime=" + responseTime
                + ", responseCode=" + responseCode + ", timestamp=" + timestamp + ", toString()=" + super.toString()
                + "]";
    }


    

    // Getters and setters
}

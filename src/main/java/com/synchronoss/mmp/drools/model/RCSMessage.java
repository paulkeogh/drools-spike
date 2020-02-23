package com.synchronoss.mmp.drools.model;

public class RCSMessage {
    private Boolean isSenderBias;
    private Long riskScore;

    public Boolean getIsSenderBias() {
        return isSenderBias;
    }

    public void setIsSenderBias(Boolean isSenderBias) {
        this.isSenderBias = isSenderBias;
    }

    public Long getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Long riskScore) {
        this.riskScore = riskScore;
    }

}
package com.synchronoss.mmp.drools.model;

public class Risk {
    private Long senderBias;
    private Long score;

    public Risk() {
        senderBias = 0L;
        score = 0L;
    }

    public Long getSenderBias() {
        return senderBias;
    }

    public void setSenderBias(Long senderBias) {
        this.senderBias = senderBias;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getTotalRiskScore() {
        return senderBias + score;
    }

}

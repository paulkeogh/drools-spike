package com.synchronoss.mmp.drools.service;

import com.synchronoss.mmp.drools.model.Risk;
import com.synchronoss.mmp.drools.model.RCSMessage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageScoreCalculatorService {

    @Autowired
    private KieContainer kContainer;

    public Long calculateRisk(RCSMessage rcsMessage, Risk risk) {
        KieSession kieSession = kContainer.newKieSession();
        kieSession.setGlobal("riskScore", risk);
        kieSession.insert(rcsMessage);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("!! Risk !! " + risk.getTotalRiskScore());
        return risk.getTotalRiskScore();
    }
}

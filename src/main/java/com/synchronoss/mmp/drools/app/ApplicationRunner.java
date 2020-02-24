package com.synchronoss.mmp.drools.app;

import com.synchronoss.mmp.drools.model.Risk;
import com.synchronoss.mmp.drools.model.RCSMessage;
import com.synchronoss.mmp.drools.service.MessageScoreCalculatorService;
import com.synchronoss.mmp.drools.service.MessageScoreConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MessageScoreConfiguration.class);
        MessageScoreCalculatorService messageScoreCalculatorService = (MessageScoreCalculatorService) context.getBean(MessageScoreCalculatorService.class);

        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(true);
        rcsMessage.setRiskScore(190L);
        Risk riskScore = new Risk();

        messageScoreCalculatorService.monitorRulesFiles();

        messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);
    }

}
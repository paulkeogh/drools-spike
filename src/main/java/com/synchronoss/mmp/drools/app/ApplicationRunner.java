package com.synchronoss.mmp.drools.app;

import com.synchronoss.mmp.drools.model.Risk;
import com.synchronoss.mmp.drools.model.RCSMessage;
import com.synchronoss.mmp.drools.service.MessageScoreCalculatorService;
import com.synchronoss.mmp.drools.service.MessageScoreConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MessageScoreConfiguration.class);
        MessageScoreCalculatorService messageScoreCalculatorService = (MessageScoreCalculatorService) context.getBean(MessageScoreCalculatorService.class);

        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(true);
        rcsMessage.setRiskScore(190L);
        Risk rideFare = new Risk();

        messageScoreCalculatorService.calculateRisk(rcsMessage, rideFare);
    }

}
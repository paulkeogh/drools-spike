package com.synchronoss.mmp.drools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.synchronoss.mmp.drools.model.Risk;
import com.synchronoss.mmp.drools.model.RCSMessage;
import com.synchronoss.mmp.drools.service.MessageScoreCalculatorService;
import com.synchronoss.mmp.drools.service.MessageScoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MessageScoreConfiguration.class)
@TestPropertySource("/application-test.properties")
public class MessageScoreCalculationServiceTest {
    @Autowired
    private MessageScoreCalculatorService messageScoreCalculatorService;

    @Test
    public void test1() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(false);
        rcsMessage.setRiskScore(9L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(70), totalScore);
    }

    @Test
    public void test2() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(true);
        rcsMessage.setRiskScore(5L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(100), totalScore);
    }

    @Test
    public void test3() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(false);
        rcsMessage.setRiskScore(50L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(170), totalScore);
    }

    @Test
    public void test4() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(true);
        rcsMessage.setRiskScore(50L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(250), totalScore);
    }

    @Test
    public void test5() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(false);
        rcsMessage.setRiskScore(100L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(220), totalScore);
    }

    @Test
    public void test6() {
        RCSMessage rcsMessage = new RCSMessage();
        rcsMessage.setIsSenderBias(true);
        rcsMessage.setRiskScore(100L);
        Risk riskScore = new Risk();
        Long totalScore = messageScoreCalculatorService.calculateRisk(rcsMessage, riskScore);

        assertNotNull(totalScore);
        assertEquals(Long.valueOf(350), totalScore);
    }
}
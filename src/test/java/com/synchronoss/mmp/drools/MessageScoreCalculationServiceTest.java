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
    public void whenNightSurchargeFalseAndDistanceLessThan10_thenFixFareWithoutNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(false);
        taxiRide.setRiskScore(9L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(70), totalCharge);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan10_thenFixFareWithNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(true);
        taxiRide.setRiskScore(5L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(100), totalCharge);
    }

    @Test
    public void whenNightSurchargeFalseAndDistanceLessThan100_thenDoubleFareWithoutNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(false);
        taxiRide.setRiskScore(50L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(170), totalCharge);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceLessThan100_thenDoubleFareWithNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(true);
        taxiRide.setRiskScore(50L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(250), totalCharge);
    }

    @Test
    public void whenNightSurchargeFalseAndDistanceGreaterThan100_thenExtraPercentFareWithoutNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(false);
        taxiRide.setRiskScore(100L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(220), totalCharge);
    }

    @Test
    public void whenNightSurchargeTrueAndDistanceGreaterThan100_thenExtraPercentFareWithNightSurcharge() {
        RCSMessage taxiRide = new RCSMessage();
        taxiRide.setIsSenderBias(true);
        taxiRide.setRiskScore(100L);
        Risk rideFare = new Risk();
        Long totalCharge = messageScoreCalculatorService.calculateRisk(taxiRide, rideFare);

        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(350), totalCharge);
    }

}
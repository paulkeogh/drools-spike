package com.synchronoss.mmp.drools.app;

import com.synchronoss.mmp.drools.model.Fare;
import com.synchronoss.mmp.drools.model.TaxiRide;
import com.synchronoss.mmp.drools.service.TaxiFareCalculatorService;
import com.synchronoss.mmp.drools.service.TaxiFareConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TaxiFareConfiguration.class);
        TaxiFareCalculatorService taxiFareCalculatorService = (TaxiFareCalculatorService) context.getBean(TaxiFareCalculatorService.class);

        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(true);
        taxiRide.setDistanceInMile(190L);
        Fare rideFare = new Fare();

        taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
    }

}
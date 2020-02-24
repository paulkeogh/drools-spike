package com.synchronoss.mmp.drools.service;

import com.synchronoss.mmp.drools.model.Risk;
import com.synchronoss.mmp.drools.model.RCSMessage;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

@Service
public class MessageScoreCalculatorService {
    private WatchService watchService = FileSystems.getDefault().newWatchService();

    @Autowired
    private KieContainer kContainer;

    public MessageScoreCalculatorService() throws IOException {
    }

    public Long calculateRisk(RCSMessage rcsMessage, Risk risk) {
        KieSession kieSession = kContainer.newKieSession();
        kieSession.setGlobal("riskScore", risk);
        kieSession.insert(rcsMessage);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("!! Risk !! " + risk.getTotalRiskScore());
        return risk.getTotalRiskScore();
    }
    
    @Async
    public void monitorRulesFiles() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(System.getProperty("user.home"));

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;

        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                KieServices kieServices = KieServices.Factory.get();
                KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

                kieFileSystem.write(ResourceFactory.newFileResource(path.toFile()));
                KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }

            key.reset();
        }
    }
}

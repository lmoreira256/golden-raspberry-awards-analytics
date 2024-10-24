package br.com.lucas.moreira.goldenraspberryawardsanalytics;

import br.com.lucas.moreira.goldenraspberryawardsanalytics.service.FillDatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.FileNotFoundException;

@SpringBootApplication
public class GoldenRaspberryAwardsAnalyticsApplication {

    private final FillDatabaseService fillDatabaseService;

    GoldenRaspberryAwardsAnalyticsApplication(FillDatabaseService fillDatabaseService) {
        this.fillDatabaseService = fillDatabaseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspberryAwardsAnalyticsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() throws FileNotFoundException {
        fillDatabaseService.execute();
    }

}

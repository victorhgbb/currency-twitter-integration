package br.com.victorhgbb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
@EnableScheduling
public class CurrencyTwitterIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyTwitterIntegrationApplication.class, args);
    }

}
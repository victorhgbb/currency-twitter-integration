package br.com.victorhgbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyTwitterIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyTwitterIntegrationApplication.class, args);
    }

}
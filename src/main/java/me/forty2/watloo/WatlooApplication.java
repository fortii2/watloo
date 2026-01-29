package me.forty2.watloo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class WatlooApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatlooApplication.class, args);
    }

}

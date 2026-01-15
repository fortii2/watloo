package me.forty2.watloo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WatlooApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatlooApplication.class, args);
    }

}

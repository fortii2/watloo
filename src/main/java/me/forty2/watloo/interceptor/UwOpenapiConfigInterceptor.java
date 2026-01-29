package me.forty2.watloo.interceptor;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class UwOpenapiConfigInterceptor {

    @Value("${waterloo.open.key}")
    private String apiKey;

    @Bean
    RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("x-api-key", apiKey);
            requestTemplate.header("accept", "application/json");
        };
    }

}

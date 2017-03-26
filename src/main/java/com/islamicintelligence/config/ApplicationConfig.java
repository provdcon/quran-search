package com.islamicintelligence.config;

import com.islamicintelligence.handler.RequestHeaderInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by mufy on 26/03/2017.
 */

@Configuration
public class ApplicationConfig {

    @Bean
    public RequestHeaderInterceptor requestHeaderInterceptor(){

        return new RequestHeaderInterceptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(requestHeaderInterceptor()));

        return restTemplate;

    }
}

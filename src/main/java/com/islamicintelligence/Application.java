package com.islamicintelligence;

import com.islamicintelligence.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mufy on 26/03/2017.
 */

@SpringBootApplication
//@EnableAutoConfiguration
public class Application {

    public static void main(String args[]) {

        SpringApplication.run(Application.class, args);

    }

}
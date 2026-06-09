package com.feynman.learningstudio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.feynman.learningstudio")
@SpringBootApplication
public class FeynmanLearningStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeynmanLearningStudioApplication.class, args);
    }
}

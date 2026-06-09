package com.feynman.learningstudio.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI feynmanLearningStudioOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Feynman Learning Studio API")
                        .version("v0.1")
                        .description("Backend learning core API for Learning Topic and Learning Experiment."));
    }
}

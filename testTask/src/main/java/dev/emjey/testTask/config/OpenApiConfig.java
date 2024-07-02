package dev.emjey.testTask.config;


// This file is made by EmJey
// Project: testTask
// FileName: OpenApiConfig.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Ordering API for testing By EmJeyDev")
                        .description("Using this API we can use CRUD operations on Customer, Product and Order.")
                        .version("v1.0.1"));
    }

}

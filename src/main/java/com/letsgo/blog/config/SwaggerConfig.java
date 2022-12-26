package com.letsgo.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(getInfo())
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Backend Apis for Blogging Application",
                            "This project is developed by Vishal Kumar",
                            "V1.0",
                            "Terms of Service - Learning purpose",
                            new Contact("Vishal Kumar",
                                    "letsgo.blogging.com",
                                    "vishal80020@gmail.com"),
                            "Free-Api - Source code can be found on Github",
                            "https://github.com/vishal80020/BloggingProject",
                            Collections.emptyList());
    }
}

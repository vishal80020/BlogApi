package com.letsgo.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";


    private ApiKey getApiKey() {
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    private List<SecurityContext> getSecurityContexts() {
        return Arrays.asList(SecurityContext.builder().securityReferences(getSecurityReferences()).build());
    }

    private List<SecurityReference> getSecurityReferences() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {authorizationScope}));
    }


    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(getInfo())
                    .securityContexts(getSecurityContexts())
                    .securitySchemes(Arrays.asList(getApiKey()))
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

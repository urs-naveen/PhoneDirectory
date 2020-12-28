package com.phonedirectory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
    private static final String TITLE = "Telephone Directory";
    private static final String DESCRIPTION = "Building a Telephone Directory";
    private static final String VERSION = "1.2";
    private static final String TERMS_OF_SERVICE = "Terms of Service";
    private static final String NAME = "Naveen Kumar";
    private static final String URL = ""; //swagger-ui test api
    private static final String EMAIL = "naveen.kasa1080@gmail.com";
    private static final String LICENSE = "Own Project";
    private static final String LICENCE_URL = "";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private static ApiInfo apiInfo() {
        return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMS_OF_SERVICE, new Contact(NAME, URL, EMAIL), LICENSE, LICENCE_URL, Collections.emptyList());
    }
}

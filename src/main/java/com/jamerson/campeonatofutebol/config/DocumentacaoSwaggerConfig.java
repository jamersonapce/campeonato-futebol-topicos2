package com.jamerson.campeonatofutebol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class DocumentacaoSwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jamerson.campeonatofutebol.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.description());
    }

    private ApiInfo description() {
        return new ApiInfo(
                "API REST - Sistema controle de campeonatos de futebol",
                "API Spring Boot, Web REST, JWT.",
                "0.1.0",
                "uso permitido para fins acadêmicos.",
                new Contact("Jamerson Silva", "www.example.com", "jamerson@email.com"),
                "License of API", "API license URL", Collections.emptyList());
//        return new ApiInfoBuilder()
//                .title("API REST - Sistema controle de campeonatos de futebol")
//                .description("API Spring Boot, Web REST, JWT")
//                .version("0.1.0")
//                .contact(new Contact("Jamerson Silva", "www.apirest.com", "jamerson@email.com"))
//                .build();
    }
}

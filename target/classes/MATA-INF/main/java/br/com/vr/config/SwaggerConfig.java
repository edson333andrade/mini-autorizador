package br.com.vr.config;

import org.assertj.core.util.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(ResponseEntity.class, Void.class)
                .directModelSubstitute(Object.class, Void.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.vr.controllers"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .consumes(Set.of(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .produces(Set.of(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .protocols(Set.of("HTTP"));

    }
}

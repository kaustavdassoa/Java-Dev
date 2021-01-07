package com.example.HelloWorldDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@@TODO : Upgraded to Swagger 3.0.0 version
//@@TODO : Add security



@Configuration
@EnableSwagger2
public class Swagger2Configuration {


    @Bean
    public Docket apiDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointInfo());


    }

    private ApiInfo apiEndPointInfo()
    {
        return new ApiInfoBuilder()
                .title("HELLO WORLD API")
                .description("Hello world spring-boot service")
                .build();
    }

}

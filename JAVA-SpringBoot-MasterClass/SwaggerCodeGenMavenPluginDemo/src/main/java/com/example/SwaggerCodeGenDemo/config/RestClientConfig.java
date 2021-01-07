package com.example.SwaggerCodeGenDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

//    @Bean
//    public ApiClient getAPIClient()
//    {
//        return new ApiClient().setBasePath("http://localhost:8080");
//    }





}

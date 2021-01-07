package com.example.SwaggerCodeGenDemo.controller;

import com.example.SwaggerCodeGenDemo.api.HelloWorldControllerApi;
import com.example.SwaggerCodeGenDemo.service.ApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V03")
public class HelloWorldClientController {



    @GetMapping(path = "/testHelloWorld")
    public String testHelloWorld()
    {
        HelloWorldControllerApi helloWorldControllerApi=new HelloWorldControllerApi();
        helloWorldControllerApi.setApiClient(new ApiClient().setBasePath("http://localhost:8080"));
        String greetingsString=helloWorldControllerApi.helloWorldUsingGET().toString();

        return "Success :"+greetingsString;
    }

}

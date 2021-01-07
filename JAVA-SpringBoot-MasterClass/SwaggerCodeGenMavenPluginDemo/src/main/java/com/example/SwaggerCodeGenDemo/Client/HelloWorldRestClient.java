package com.example.SwaggerCodeGenDemo.Client;

import com.example.SwaggerCodeGenDemo.api.HelloWorldControllerApi;
import com.example.SwaggerCodeGenDemo.model.Greetings;
import com.example.SwaggerCodeGenDemo.service.ApiClient;
import org.springframework.stereotype.Service;


@Service
public class HelloWorldRestClient {




    public void CallHelloWorld()
    {
        HelloWorldControllerApi helloWorldControllerApi=new HelloWorldControllerApi();
        helloWorldControllerApi.setApiClient(new ApiClient().setBasePath("http://localhost:8080"));

        Greetings greetings=helloWorldControllerApi.helloWorldUsingGET();
        System.out.println("Greeting Object : "+greetings.toString());
    }

}

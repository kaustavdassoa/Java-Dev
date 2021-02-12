package com.example.restendpointinvocation.controller;

import com.example.restendpointinvocation.model.PayloadRequest;
import com.example.restendpointinvocation.model.PayloadResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RestControllerImpl {


    @PostMapping(path = "/post/request")
    public PayloadResponse SampleResposne(@RequestBody PayloadRequest request, @RequestHeader("custome-header1") String customeHeader1,@RequestHeader("custome-header2") String customeHeader2)
    {

        System.out.println("Input Request : "+request.toString());
        System.out.println("custom-header1 :"+customeHeader1);
        System.out.println("custom-header2 :"+customeHeader2);

        PayloadResponse response=new PayloadResponse();
        response.setResponseID(UUID.randomUUID().toString());


        return response;
    }
}

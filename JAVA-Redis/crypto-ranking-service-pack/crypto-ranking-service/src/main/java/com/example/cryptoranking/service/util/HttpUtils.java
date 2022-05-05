package com.example.cryptoranking.service.util;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

@Slf4j
public class HttpUtils {


    private static String apiHost = "coinranking1.p.rapidapi.com";
    private static String apiKey = "847ec2c2b3msh7b72715718ade6ap10113djsn1fcda500c190";

    public static HttpEntity<String> getHttpEntities()
    {

        log.info("HTTP api-Host-name : {}",apiHost);
        log.info("HTTP api-key : {}",apiKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Host", apiHost);
        headers.set("X-RapidAPI-Key", apiKey);
        return new HttpEntity<>(null, headers);
    }
}

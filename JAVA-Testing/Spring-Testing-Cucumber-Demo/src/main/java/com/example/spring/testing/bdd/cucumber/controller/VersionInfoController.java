package com.example.spring.testing.bdd.cucumber.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionInfoController {

    @GetMapping("/version")
    public String getVersion()
    {
        return "v.1.0";
    }
}

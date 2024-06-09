package com.example.helloworld.dto;


import java.time.LocalDateTime;

public class HelloWordResponseV2 extends HelloWordResponse {

    String serviceType;
    String createdBy;
    LocalDateTime createdDate;

    public HelloWordResponseV2(String greetings, String serviceType, String createdBy, LocalDateTime createdDate) {
        super.greetings=greetings;
        this.serviceType = serviceType;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }


    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings ) {
        this.greetings=greetings;
    }


    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}

package com.example.restendpointinvocation.model;

public class PayloadResponse {

    String responseID;
    String errorCode;
    String errorDescription;

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

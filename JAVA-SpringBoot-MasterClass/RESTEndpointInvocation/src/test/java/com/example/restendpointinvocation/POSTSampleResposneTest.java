package com.example.restendpointinvocation;

import com.example.restendpointinvocation.corejavaclient.Simplejavaclient;
import com.example.restendpointinvocation.model.PayloadRequest;
import com.example.restendpointinvocation.model.PayloadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class POSTSampleResposneTest {

    public static final String _POST_URL_STRNG="http://localhost:8080/post/request";

    private PayloadResponse getErrorResponse(String erroCode,String errorDescription)
    {
        PayloadResponse response=new PayloadResponse();
        response.setResponseID("-1");
        response.setErrorCode(erroCode);
        response.setErrorDescription(errorDescription);
        return response;
    }

    public PayloadResponse sendPostRequestWithApacheClient(PayloadRequest request) throws Exception
    {

        HttpPost httpPost = new HttpPost(_POST_URL_STRNG);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response=null;


        ObjectMapper Obj = new ObjectMapper();
        String requestStr = Obj.writeValueAsString(request);

        StringEntity entity = new StringEntity(requestStr);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        httpPost.setHeader("custome-header1", "custome-header1-value");
        httpPost.setHeader("custome-header2", "custome-header2-value");

        try{
            response = client.execute(httpPost);

        }
        catch (Exception exception)
        {
            System.out.println("Exception :"+exception.getMessage());
            return getErrorResponse("unkown",exception.getMessage());
        }

        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("Response status Code :"+statusCode);

        switch (statusCode)
        {

            case 200:
                HttpEntity httpEntity = response.getEntity();
                String apiOutput = EntityUtils.toString(httpEntity);
                PayloadResponse apiResponse = new ObjectMapper().readValue(apiOutput, PayloadResponse.class);
                return apiResponse;

            case 400:
                return getErrorResponse("400","BAD REQUEST");

            case 500:
                return getErrorResponse("500","INTERNAL SERVER ERROR");

            default:
                return getErrorResponse("UNKOWN","UNKOWN ERROR");

        }

    }

    @Test
    void testPOSTResponse() throws Exception{


        Simplejavaclient javaClient=new Simplejavaclient();
        System.out.println("Inside main method");
        PayloadRequest request=new PayloadRequest();
        request.setRequesterName("Kaustav Das");
        request.setRequesterID("1002");
        PayloadResponse apiResponse=new POSTSampleResposneTest().sendPostRequestWithApacheClient(request);
        assertNotNull(apiResponse);


    }




}

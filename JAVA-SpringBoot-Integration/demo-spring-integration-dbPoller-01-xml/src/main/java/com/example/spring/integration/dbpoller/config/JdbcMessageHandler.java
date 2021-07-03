package com.example.spring.integration.dbpoller.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcMessageHandler {

    public void handleJdbcMessage(List<Map<String, Object>> message) {
        for (Map<String, Object> resultMap: message) {
            System.out.println("Row");
            for (String column: resultMap.keySet()) {
                System.out.println("column: " + column + " value: " + resultMap.get(column));
            }
        }
    }
}

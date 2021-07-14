package com.example.spring.integration.dbpoller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.config.JdbcPollingChannelAdapterParser;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class JdbcpollerConfig {

    @Value("${dbpoller.rowPerPoll : 1}")
    public int rowPerPoll;


    @Bean
    @InboundChannelAdapter(value = "dbChannel", poller = @Poller(fixedRate = "${dbpoller.delayBetweenPoll}") ) //TODO: Adding programmable poller refer link : https://www.baeldung.com/spring-integration-transaction
    public MessageSource<?> jdbcAdapter(DataSource dataSource) {
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM Items WHERE INVENTORY_STATUS = 0");
        adapter.setUpdateSql("UPDATE Items SET INVENTORY_STATUS = 1,LAST_UPDATED_DATE=CURRENT_TIMESTAMP WHERE ITEM_ID in (:ITEM_ID)");
        adapter.setMaxRows(rowPerPoll);
        return adapter;
    }


    @ServiceActivator(inputChannel= "dbChannel")
    public void handleJdbcMessage(List<Map<String, Object>> message) {
        for (Map<String, Object> resultMap: message) {
            for (String column: resultMap.keySet()) {
                System.out.println("column: " + column + " value: " + resultMap.get(column));
            }
        }
    }
}

package com.example.spring.integration.dbpoller.config;



import com.example.spring.integration.dbpoller.domain.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.PollerFactory;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DBPollerIntegrationFlow {

    @Bean
    public MessageSource<?> jdbcAdapter(DataSource dataSource) {
        JdbcPollingChannelAdapter adapter =
                new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM Items WHERE INVENTORY_STATUS = 0");
        adapter.setUpdateSql("UPDATE Items SET INVENTORY_STATUS = 1,LAST_UPDATED_DATE=CURRENT_TIMESTAMP WHERE ITEM_ID in (:ITEM_ID)");
        adapter.setMaxRows(1);//Max polling row 1
        adapter.setLoggingEnabled(true);
        return adapter;
    }


    @Bean
    public IntegrationFlow jdbcFlow(MessageSource<?> jdbcAdapter) {
        return IntegrationFlows
                .from(jdbcAdapter, e ->
                e.poller(p -> p.fixedRate(20000))) // in millsecond
                .channel(c -> c.direct("target"))
                .handle(messageHandler())
//                .handle( message  -> {
//                    System.out.println(message.getPayload());
//                })
                .get();
    }
    public MessageHandler messageHandler()
    {
        return new AbstractMessageHandler() {
            @Override
            protected void handleMessageInternal(Message<?> message) {
                //System.out.println(message.getHeaders().get("id")+" ||"+message.getHeaders().get("timestamp")+"||"+parsePayload(message));
                System.out.println(message.getHeaders().get("id")+" ||"+message.getHeaders().get("timestamp")+"||"+parsePayload(message));
            }
        };
    }

    private String parsePayload(Message<?> message)
    {
        StringBuilder returnString = new StringBuilder();
        List<Map<String, Object>> itemList= (ArrayList) message.getPayload();

        System.out.println("Count of items found per message #"+itemList.size());

        for (Map<String, Object> resultMap: itemList) {

            for (String column: resultMap.keySet()) {
                //System.out.println("column: " + column + " value: " + resultMap.get(column));
                //returnString.append("column: " + column + " value: " + resultMap.get(column));
                returnString.append(" "+column + " : " + resultMap.get(column)+" ||");
               }
        }
        return returnString.toString();
    }


}

package com.example.springConfiguration.service.impl;

import com.example.springConfiguration.service.AlaisBean;
import com.example.springConfiguration.service.SimpleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlaisBeanImpl implements AlaisBean {


    @Bean(name = {"littleBean","smallBean","tinyBean"})
    public SimpleBean simpleBeanObject()
    {
        return new SimpleBeanImpl();
    }
}

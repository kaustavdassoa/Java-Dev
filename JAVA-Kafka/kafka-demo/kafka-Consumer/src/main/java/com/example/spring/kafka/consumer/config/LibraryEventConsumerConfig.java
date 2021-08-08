package com.example.spring.kafka.consumer.config;

import com.example.spring.kafka.consumer.service.LibEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffContext;
import org.springframework.retry.backoff.BackOffInterruptedException;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka // this is requried for kafka autoConfig to read configuration provided on application.yml
public class LibraryEventConsumerConfig {


    @Autowired
    LibEventService libEventService;


    @Bean
   ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setConcurrency(3); // this option is not valid for cloud enviroment - where single consumer per application is more desirable - for server this option comes handy
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setRetryTemplate(getRetryTemplate());
        factory.setRecoveryCallback(recoveryContext -> {
            log.info("indide recovery block");
            //check if its a RecoverableDataAccessException
            if(recoveryContext.getLastThrowable().getCause() instanceof RecoverableDataAccessException) {
                log.warn("Recovery trigger for {} Error message  : {}",recoveryContext.getLastThrowable().getClass(),recoveryContext.getLastThrowable().getMessage());
                if(log.isDebugEnabled()) {
                    Arrays.asList(recoveryContext.attributeNames()).forEach(attributeName -> {
                        log.debug("attribute name [{}] value = {} ", attributeName, recoveryContext.getAttribute(attributeName));
                    });
                }
                ConsumerRecord<Integer,String> record=(ConsumerRecord<Integer,String>) recoveryContext.getAttribute("record");
                libEventService.recoverFailedMessages(record);
            }
            else
            {
                log.error("Non-recovery Error {} found , error message  : {}",recoveryContext.getLastThrowable().getClass(),recoveryContext.getLastThrowable().getMessage());
            }

            return null;
        });
        factory.setErrorHandler((exceptionThrown,data) -> {
            log.error("Error logged data {} , exception {} ",data,exceptionThrown.getMessage());
            //TODO: persist error message + exception details
        });


        return factory;
    }

    private RetryTemplate getRetryTemplate() {
        FixedBackOffPolicy fixedBackOffPolicy=new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(1000);

        RetryTemplate retryTemplate=new RetryTemplate();
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy); //fixBackOff set to 1000ms
        retryTemplate.setRetryPolicy(getRetryPolicy()); //retryPolicy max attempt 3
        return retryTemplate;
    }

    private RetryPolicy getRetryPolicy() {



        Map<Class<? extends Throwable>, Boolean> exceptionMap=new HashMap();
        exceptionMap.put(IllegalArgumentException.class,false);
        exceptionMap.put(RecoverableDataAccessException.class,true);
        SimpleRetryPolicy retryPolicy=new SimpleRetryPolicy(3,exceptionMap,true);
        retryPolicy.setMaxAttempts(3);
        return retryPolicy;
    }

}

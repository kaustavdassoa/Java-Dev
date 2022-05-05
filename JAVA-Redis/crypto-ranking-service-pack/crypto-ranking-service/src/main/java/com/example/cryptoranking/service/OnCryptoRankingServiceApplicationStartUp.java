package com.example.cryptoranking.service;

import com.example.cryptoranking.service.service.CoinDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OnCryptoRankingServiceApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CoinDataService coinDataService;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Inside onApplicationEvent() method");
        //coinDataService.fetchCoins();
      //  coinDataService.fetchCoinsHistory();
        log.info("Exited onApplicationEvent() method");
    }


}

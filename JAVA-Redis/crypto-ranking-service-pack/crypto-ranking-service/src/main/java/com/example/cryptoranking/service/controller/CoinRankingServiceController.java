package com.example.cryptoranking.service.controller;


import com.example.cryptoranking.service.model.CoinInfo;
import com.example.cryptoranking.service.model.CoinInfoShort;
import com.example.cryptoranking.service.model.Coins;
import com.example.cryptoranking.service.service.CoinDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CoinRankingServiceController {

    @Autowired
    private CoinDataService coinsDataService;

    @GetMapping (path = "/coins")
    public ResponseEntity<Coins> fetchCoinsAll() {
        return  ResponseEntity.ok().body(coinsDataService.fetchCoins().getBody());
    }

    @GetMapping (path = "/coin")
    public ResponseEntity<CoinInfoShort> fetchCoinDetaills(@RequestParam String coinName)
    {
        return  ResponseEntity.ok().body(coinsDataService.fetchCoinInfo(coinName));
    }
}

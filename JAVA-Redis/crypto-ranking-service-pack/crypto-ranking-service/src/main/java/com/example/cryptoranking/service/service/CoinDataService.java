package com.example.cryptoranking.service.service;

import com.example.cryptoranking.service.model.*;
import com.example.cryptoranking.service.util.HttpUtils;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import io.github.dengliming.redismodule.redisjson.args.SetArgs;
import io.github.dengliming.redismodule.redisjson.utils.GsonUtils;
import io.github.dengliming.redismodule.redistimeseries.DuplicatePolicy;
import io.github.dengliming.redismodule.redistimeseries.RedisTimeSeries;
import io.github.dengliming.redismodule.redistimeseries.Sample;
import io.github.dengliming.redismodule.redistimeseries.TimeSeriesOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CoinDataService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisJSON redisJSON;

    @Autowired
    RedisTimeSeries redisTimeSeries;

    private static final String GET_COINS_API="https://coinranking1.p.rapidapi.com/coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&tiers%5B0%5D=1&orderBy=marketCap&orderDirection=desc&limit=50&offset=0";

    public static final String REDIS_KEY_COINS = "coins";
    public static final String GET_COIN_HISTORY_API = "https://coinranking1.p.rapidapi.com/coin/";
    public static final String COIN_HISTORY_TIME_PERIOD_PARAM = "/history?timePeriod=";
    public static final List<String> timePeriods = List.of("24h", "7d", "30d", "3m", "1y", "3y", "5y");

    public ResponseEntity<Coins> fetchCoins()
    {

        log.info("Inside fetchCoins() method");
        ResponseEntity<Coins> coinsEntity =
                restTemplate.exchange(GET_COINS_API,
                        HttpMethod.GET,
                        HttpUtils.getHttpEntities(),
                        Coins.class);

        coinsEntity.getBody().getData().getCoins()
                .stream()
                .forEach(coinInfo -> { log.info("{} data fetched ",coinInfo.getName());});

        storeCoinsToRedisJSON(coinsEntity.getBody());
        log.info("Exited fetchCoins() method");
        return coinsEntity;
    }


    public void fetchCoinsHistory()
    {
        log.info("Inside fetchCoinsHistory() method");

        List<CoinInfo> allCoins = getAllCoinsFromRedisJSON();
        allCoins.forEach(coinInfo -> {
            timePeriods.forEach(s -> {
                try {
                    fetchCoinHistoryForTimePeriod(coinInfo, s);
                    Thread.sleep(200); // To Avoid Rate Limit of rapid API of 5 Request/Sec
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });


        log.info("Exited fetchCoinsHistory() method");

    }


    public CoinInfoShort fetchCoinInfo(String coinName) {
        log.info("Inside fetchCoinInfo() method");
        CoinInfoShort coinInfoShort = null;

        try {
            log.debug("Before checking redisJSON cache");
            coinInfoShort = redisJSON.get(coinName,
                    CoinInfoShort.class,
                    new GetArgs().path(".").indent("\t").newLine("\n").space(" "));
            log.info("{} coin details found ",coinInfoShort.getName());
            coinInfoShort.setReturnedFromCached(true);
            return coinInfoShort;

        } catch (Exception e) {
            log.debug("redisJSON cache not found for {} key", coinName);
        }

//        List<CoinInfo> matchCoinInfo = matchCoinInfo = getCoinData().getCoins().stream()
//                .filter(coinInfo -> coinInfo.getName().equalsIgnoreCase(coinName))
//                .collect(Collectors.toList());


        log.info("Invoking Rapid API - CoinRanking Service ");
        ResponseEntity<Coins> coinsEntity =
                restTemplate.exchange(GET_COINS_API,
                        HttpMethod.GET,
                        HttpUtils.getHttpEntities(),
                        Coins.class);
        List<CoinInfo> matchCoinInfo= coinsEntity.getBody().getData().getCoins().stream()
                .filter(coinInfo -> coinInfo.getName().equalsIgnoreCase(coinName)).collect(Collectors.toList());

        log.info("found {} number of matching coin infos",matchCoinInfo.size());

        if (!matchCoinInfo.isEmpty())
        {

            log.info("Exited fetchCoinInfo() method");
            coinInfoShort = CoinInfoShort.builder()
                    .name(matchCoinInfo.get(0).getName())
                    .price(matchCoinInfo.get(0).getPrice())
                    .symbol(matchCoinInfo.get(0).getSymbol())
                    .build();
            log.info("{} info saved to RedisJSON", coinInfoShort.getName());
            storeCoinShortInfoToRedisJSON(coinInfoShort,coinName);
            coinInfoShort.setReturnedFromCached(false);
            return coinInfoShort;
        }
        log.info("{} coin details not-found ",coinName);
        log.info("Exited fetchCoinInfo() method");
        return CoinInfoShort.builder().name(coinName).symbol("not-found").price("not-found").build();
    }

//        if(! coinInfoShortOptional.isEmpty())
//        {
//            log.info("{} Coin Info return from redisJSON cache",coinInfoShortOptional.get().getName());
//            return coinInfoShortOptional.get();
//        }
//        else
//        {
//
//            List<CoinInfo> matchCoinInfo=matchCoinInfo=getCoinData().getCoins().stream()
//                    .filter(coinInfo -> coinInfo.getName().equalsIgnoreCase(coinName))
//                    .collect(Collectors.toList());
//
//            if(! matchCoinInfo.isEmpty())
//            {
//
//                log.info("Exited fetchCoinsHistory() method");
//                CoinInfoShort coinInfoShort= CoinInfoShort.builder()
//                        .name(matchCoinInfo.get(0).getName())
//                        .price(matchCoinInfo.get(0).getPrice())
//                        .symbol(matchCoinInfo.get(0).getSymbol())
//                        .build();
//                log.info("{} info saved to RedisJSON",coinInfoShort.getName());
//                storeCoinShortInfoToRedisJSON(coinInfoShort);
//                return coinInfoShort;
//
//            }
//        }
//
//
//        log.info("Exited fetchCoinsHistory() method");
      //  return CoinInfoShort.builder().build();
 //   }

    private CoinData getCoinData() {
        return redisJSON.get(REDIS_KEY_COINS,
                CoinData.class,
                new GetArgs().path(".data").indent("\t").newLine("\n").space(" "));
    }

    // Private methods

    private void storeCoinsToRedisJSON(Coins coins) {
        log.info("Inside storeCoinsToRedisJSON() method");
        redisJSON.set(REDIS_KEY_COINS, SetArgs
                .Builder.create(".", GsonUtils.toJson(coins)));
        log.info("Exited storeCoinsToRedisJSON() method");
    }
    private void storeCoinShortInfoToRedisJSON(CoinInfoShort coinInfoShort,String key) {
        log.info("Inside storeCoinShortInfoToRedisJSON() method");
        redisJSON.set(key, SetArgs
                .Builder.create(".", GsonUtils.toJson(coinInfoShort)));
        log.info("Exited storeCoinShortInfoToRedisJSON() method");
    }

    private List<CoinInfo> getAllCoinsFromRedisJSON() {
        CoinData coinData = getCoinData();
        log.info("allCoins: " + coinData);
        return coinData.getCoins();
    }


    private void fetchCoinHistoryForTimePeriod(CoinInfo coinInfo, String timePeriod) {
        log.info("Fetching Coin History of {} for Time Period {}", coinInfo.getName(), timePeriod);
        String url = GET_COIN_HISTORY_API + coinInfo.getUuid() + COIN_HISTORY_TIME_PERIOD_PARAM + timePeriod;
        ResponseEntity<CoinPriceHistory> coinPriceHistoryResponseEntity =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        HttpUtils.getHttpEntities(),
                        CoinPriceHistory.class);

        log.info("Data Fetched From API for Coin History of {} for Time Period {}", coinInfo.getName(), timePeriod);

        storeCoinHistoryToRedisTS(coinPriceHistoryResponseEntity.getBody(), coinInfo.getSymbol(), timePeriod);
    }

    private void storeCoinHistoryToRedisTS(CoinPriceHistory coinPriceHistory, String symbol, String timePeriod) {
        log.info("Storing Coin History of {} for Time Period {} into Redis TS", symbol, timePeriod);
        List<CoinPriceHistoryExchangeRate> coinExchangeRateData =
                coinPriceHistory.getData().getHistory();
        coinExchangeRateData.stream()
                .filter(ch -> ch.getPrice() != null && ch.getTimestamp() != null)
                .forEach(ch -> {
                    redisTimeSeries.add(new Sample(symbol + ":" + timePeriod, Sample.Value.of(Long.valueOf(ch.getTimestamp()),
                            Double.valueOf(ch.getPrice()))), new TimeSeriesOptions()
                            .unCompressed()
                            .duplicatePolicy(DuplicatePolicy.LAST));
                    // .labels(new Label(symbol, timePeriod)));
                });
        log.info("Complete: Stored Coin History of {} for Time Period {} into Redis TS", symbol, timePeriod);
    }
}

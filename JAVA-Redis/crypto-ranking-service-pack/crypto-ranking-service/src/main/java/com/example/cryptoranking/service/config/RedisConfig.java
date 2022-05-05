package com.example.cryptoranking.service.config;

import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.client.RedisJSONClient;
import io.github.dengliming.redismodule.redistimeseries.RedisTimeSeries;
import io.github.dengliming.redismodule.redistimeseries.client.RedisTimeSeriesClient;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    private static final String REDIS_SERVER_URL="redis://192.168.1.33:6379";

    @Bean
    public Config getRedisConfig()
    {
        Config redisConfig= new Config();
        redisConfig.useSingleServer().setAddress(REDIS_SERVER_URL);
        return redisConfig;
    }

    @Bean
    public RedisTimeSeriesClient getRedisTimeSeriseClient(Config redisConfig)
    {
        return new RedisTimeSeriesClient(redisConfig);
    }

    @Bean
    public RedisTimeSeries getRedisTimeSeries(RedisTimeSeriesClient redisTimeSeriesClient)
    {
        return redisTimeSeriesClient.getRedisTimeSeries();
    }

    @Bean
    public RedisJSONClient redisJSONClient(Config config) {
        return new RedisJSONClient(config);
    }

    @Bean
    public RedisJSON redisJSON(RedisJSONClient redisJSONClient) {
        return redisJSONClient.getRedisJSON();
    }
}

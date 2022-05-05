package com.example.cryptoranking.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoinInfoShort {

    private String symbol;
    private String name;
    private String price;
    private Boolean returnedFromCached;
}

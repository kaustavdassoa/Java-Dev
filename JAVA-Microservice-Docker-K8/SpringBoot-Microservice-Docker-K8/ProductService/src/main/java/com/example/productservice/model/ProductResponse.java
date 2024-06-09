package com.example.productservice.modle;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productName;
    private long productId;
    private long quantity;
    private long price;
}

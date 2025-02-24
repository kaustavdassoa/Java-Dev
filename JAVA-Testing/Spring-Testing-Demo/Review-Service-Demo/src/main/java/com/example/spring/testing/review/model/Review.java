package com.example.spring.testing.review.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Reviews")
public class Review {

    /**
     * The ID, or primary key, of the Review, as generated by MongoDB.
     */
    private String id;

    /**
     * The product ID for which this review is applicable.
     */
    private Integer productId;

    /**
     * The version of the review, which is an increasing number starting with 1.
     */
    private Integer version = 1;

    /**
     * A list of review entries - these contain user reviews of the product.
     */
    private List<ReviewEntry> entries = new ArrayList<>();

    public Review() {
    }

    public Review(Integer productId) {
        this.productId = productId;
    }

    public Review(String id, Integer productId) {
        this.id = id;
        this.productId = productId;
    }

    public Review(String id, Integer productId, Integer version) {
        this.id = id;
        this.productId = productId;
        this.version = version;
    }

    public Review(Integer productId, Integer version) {
        this.productId = productId;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<ReviewEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", version=" + version +
                ", entries=" + entries +
                '}';
    }
}

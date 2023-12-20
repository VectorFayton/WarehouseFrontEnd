package com.example.warehousefrontend.varianceOfProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTotalCount implements Product {
    @JsonProperty("amountProducts")
    int totalCount;
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(@JsonProperty("amountProducts") int totalCount) {
        this.totalCount = totalCount;
    }
    public ProductTotalCount(@JsonProperty("amountProducts") int totalCount) {
        this.totalCount = totalCount;
    }
    @Override
    public Product clone(){
        return new ProductTotalCount(totalCount);
    }
    @Override
    public String toString() {
        return String.valueOf(totalCount);
    }
}

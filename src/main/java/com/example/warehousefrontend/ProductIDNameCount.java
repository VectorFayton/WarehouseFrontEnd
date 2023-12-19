package com.example.warehousefrontend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductIDNameCount implements Product {
    @JsonProperty("productId")
    private int productId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("count")
    private int count;
    public int getId() {
        return productId;
    }
    public void setId(@JsonProperty("productId") int productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(@JsonProperty("name") String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(@JsonProperty("count") int count) {
        this.count = count;
    }
    public ProductIDNameCount(@JsonProperty("productId") int productId, @JsonProperty("name") String name, @JsonProperty("count") int count) {
        this.productId = productId;
        this.name = name;
        this.count = count;
    }
    @Override
    public Product clone() {
        return new ProductIDNameCount(productId, name, count);
    }
    @Override
    public String toString() {
        return "ProductIDNameCount{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}

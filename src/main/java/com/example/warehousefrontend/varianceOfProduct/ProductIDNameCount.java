package com.example.warehousefrontend.varianceOfProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductIDNameCount implements Product {
    @JsonProperty("productId")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("count")
    private int count;
    public int getId() {
        return id;
    }
    public void setId(@JsonProperty("productId") int productId) {
        this.id = productId;
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
        this.id = productId;
        this.name = name;
        this.count = count;
    }
    @Override
    public Product clone() {
        return new ProductIDNameCount(id, name, count);
    }
    @Override
    public String toString() {
        return "ProductIDNameCount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}

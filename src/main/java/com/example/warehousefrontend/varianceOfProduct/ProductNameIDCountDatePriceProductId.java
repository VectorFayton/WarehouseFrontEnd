package com.example.warehousefrontend.varianceOfProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductNameIDCountDatePriceProductId implements Product{
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
    @JsonProperty("count")
    private int count;
    @JsonProperty("date")
    private String date;
    @JsonProperty("price")
    private double price;
    @JsonProperty("productId")
    private int productId;
    public String getName() {
        return name;
    }
    public void setName(@JsonProperty("name") String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(@JsonProperty("id") int id) {
        this.id = id;
    }
    public int getCount() {
        return count;
    }
    public void setCount(@JsonProperty("count") int count) {
        this.count = count;
    }
    public String getDate() {
        return date;
    }
    public void setDate(@JsonProperty("date") String date) {
        this.date = date;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(@JsonProperty("price") double price) {
        this.price = price;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(@JsonProperty("productId") int productId) {
        this.productId = productId;
    }
    public ProductNameIDCountDatePriceProductId(String name, int id, int count, String date, double price, int productId) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.date = date;
        this.price = price;
        this.productId = productId;
    }
    @Override
    public Product clone(){
        return new ProductNameIDCountDatePriceProductId(name, id, count, date, price, productId);
    }
    @Override
    public String toString() {
        return "ProductNameIDCountDatePriceProductId{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", count=" + count +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", productId=" + productId +
                '}';
    }

}

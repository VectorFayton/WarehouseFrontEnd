package com.example.warehousefrontend.varianceOfProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductIDNameCategoryPrice implements Product {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("categoryId")
    private int categoryId;
    @JsonProperty("price")
    private double price;
    public int getId() {
        return id;
    }
    public void setId(@JsonProperty("id") int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(@JsonProperty("name") String name) {
        this.name = name;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(@JsonProperty("categoryId") int category_id) {
        this.categoryId = category_id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(@JsonProperty("price") int price) {
        this.price = price;
    }
    public ProductIDNameCategoryPrice(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("categoryId") int categoryID) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryID;
        this.price = price;
    }
    @Override
    public Product clone() {
        return new ProductIDNameCategoryPrice(id, name, price, categoryId);
    }
    @Override
    public String toString() {
        return "ProductID{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category_id=" + categoryId +
            ", price=" + price +
            '}';
    }
}

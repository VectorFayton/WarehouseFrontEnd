package com.example.warehousefrontend.varianceOfProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductID implements Product{
    @JsonProperty("id")
    private static int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ProductID(int id) {
        this.id = id;
    }
    @Override
    public Product clone(){
        return new ProductID(id);
    }
}

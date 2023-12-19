package com.example.warehousefrontend;

public class ProductNamePriceCategory implements Product {
    private String name;
    private double price;
    private int categoryId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public ProductNamePriceCategory(String name, double price, int categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
    @Override
    public Product clone() {
        return new ProductNamePriceCategory(name, price, categoryId);
    }
    @Override
    public String toString() {
        return "ProductNamePriceCategory{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }
}

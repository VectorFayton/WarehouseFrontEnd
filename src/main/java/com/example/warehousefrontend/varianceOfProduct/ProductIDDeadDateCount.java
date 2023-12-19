package com.example.warehousefrontend.varianceOfProduct;

public class ProductIDDeadDateCount implements Product{
    int productId;
    String deadDate;
    int count;
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(String deadDate) {
        this.deadDate = deadDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public ProductIDDeadDateCount(int id, String date, int count) {
        this.productId = id;
        this.deadDate = date;
        this.count = count;
    }
    @Override
    public Product clone(){
        return new ProductIDArriveDateCount(productId, deadDate, count);
    }
    @Override
    public String toString() {
        return "ProductIDDateCount{" +
                "productId=" + productId +
                ", deadDate='" + deadDate + '\'' +
                ", count=" + count +
                '}';
    }
}

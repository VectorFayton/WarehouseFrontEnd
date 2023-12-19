package com.example.warehousefrontend;

public class ProductIDArriveDateCount implements Product{
    int productId;
    String arriveDate;
    int count;
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public ProductIDArriveDateCount(int id, String date, int count) {
        this.productId = id;
        this.arriveDate = date;
        this.count = count;
    }
    @Override
    public Product clone(){
        return new ProductIDArriveDateCount(productId, arriveDate, count);
    }
    @Override
    public String toString() {
        return "ProductIDDateCount{" +
                "productId=" + productId +
                ", arriveDate='" + arriveDate + '\'' +
                ", count=" + count +
                '}';
    }
}

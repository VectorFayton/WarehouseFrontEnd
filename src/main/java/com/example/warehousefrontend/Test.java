package com.example.warehousefrontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String jsonString = "[{\"id\":1,\"name\":\"Laptop\",\"price\":1000,\"categoryId\":1},{\"id\":2,\"name\":\"Smartphone\",\"price\":500,\"categoryId\":2},{\"id\":3,\"name\":\"Tablet\",\"price\":300,\"categoryId\":3},{\"id\":4,\"name\":\"Desk Chair\",\"price\":150,\"categoryId\":4},{\"id\":5,\"name\":\"Office Desk\",\"price\":200,\"categoryId\":5},{\"id\":6,\"name\":\"Printer\",\"price\":120,\"categoryId\":6},{\"id\":7,\"name\":\"Scanner\",\"price\":80,\"categoryId\":7},{\"id\":8,\"name\":\"Coffee Maker\",\"price\":50,\"categoryId\":8},{\"id\":9,\"name\":\"Toaster\",\"price\":30,\"categoryId\":9},{\"id\":10,\"name\":\"Bluetooth Speaker\",\"price\":70,\"categoryId\":10},{\"id\":11,\"name\":\"Headphones\",\"price\":100,\"categoryId\":11},{\"id\":12,\"name\":\"Running Shoes\",\"price\":80,\"categoryId\":12},{\"id\":13,\"name\":\"Backpack\",\"price\":40,\"categoryId\":13},{\"id\":14,\"name\":\"Sunglasses\",\"price\":25,\"categoryId\":14},{\"id\":15,\"name\":\"Watch\",\"price\":150,\"categoryId\":15},{\"id\":16,\"name\":\"IPhone\",\"price\":1000,\"categoryId\":2},{\"id\":17,\"name\":\"Samsung s24\",\"price\":1200,\"categoryId\":2}]\n";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Person> productList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(jsonString);

                    for (JsonNode productNode : rootNode) {
                        Person product = objectMapper.readValue(productNode.traverse(), Person.class);
                        System.out.println(product);
                        productList.add(product);
                        System.out.println(product.toString());
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Person {
        @JsonProperty("id")
        private int id;
    @JsonProperty("name")
    private String name;

    public Person(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("categoryId") int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    @JsonProperty("price")
    private double price;

    @JsonProperty("categoryId")
    private int categoryId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(@JsonProperty("price") double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@JsonProperty("categoryId") int categoryId) {
        this.categoryId = categoryId;
    }



        // Getters and setters for the fields

    // Getters and Setters

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
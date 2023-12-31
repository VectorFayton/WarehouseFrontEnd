package com.example.warehousefrontend;

import com.example.warehousefrontend.Adapters.JsonAdapter;
import com.example.warehousefrontend.Adapters.JsonObjectAdapter;
import com.example.warehousefrontend.varianceOfProduct.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Manager {
    JsonAdapter jsonAdapter = new JsonObjectAdapter();
    public ObservableList getAllProducts() throws IOException {
        StringBuilder response = Connection("http://localhost:8080/warehouse/getAllProducts", "GET");
        ObservableList<Product> products = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());
            products = FXCollections.observableArrayList(JsonConverter(rootNode, "Price"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void addProduct(String name, String price, String categoryId) throws IOException {

        if (name.isEmpty() || price.isEmpty() || categoryId.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        Product product = new ProductNamePriceCategory(name, Double.parseDouble(price), Integer.parseInt(categoryId));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(product);
        System.out.println(jsonRequest);

        URL url = new URL("http://localhost:8080/warehouse/createProduct");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        connection.disconnect();
    }
    public void removeProduct(String ID) throws IOException {
        int selectedProductId = Integer.valueOf(ID);
        System.out.println("ID: " + selectedProductId);

        URL url = new URL("http://localhost:8080/warehouse/deleteProduct/" + selectedProductId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        connection.disconnect();
    }
    public void searchProducts(TableView<Product> tableView, ObservableList<Product> products, String searchTerm) throws IOException {
        int toID = Integer.parseInt(searchTerm);
        StringBuilder response = Connection("http://localhost:8080/warehouse/getProduct/" + toID, "GET");
        System.out.println(response.toString());
        Product product = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());
            product = objectMapper.readValue(rootNode.traverse(), ProductIDNameCategoryPrice.class);
            System.out.println(product.toString());

            int index = 0;
            for (Product currentProduct : products) {
                System.out.println(currentProduct.toString());
                System.out.println(product.toString().equals(currentProduct.toString()));
                if (!product.toString().equals(currentProduct.toString())) {
                    index++;
                    System.out.println(index);
                } else if(product.toString().equals(currentProduct.toString())){
                    break;
                }
            }
            if (index != -1) {
                tableView.getSelectionModel().select(index);
                tableView.scrollTo(index);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList sortProduct(String sortType) throws IOException {
        StringBuilder response = Connection("http://localhost:8080/warehouse/sort?sort=" + sortType, "GET");

        System.out.println(response.toString());
        ObservableList<Product> list = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());

            list = FXCollections.observableArrayList(JsonConverter(rootNode, "Price"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList getWarehouseInfo() throws IOException {
        StringBuilder response = Connection("http://localhost:8080/warehouse", "GET");

        System.out.println(response.toString());
        ObservableList<Product> products = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());
            products = FXCollections.observableArrayList(JsonConverter(rootNode, "Count"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    public void arriveProduct(String id, String date, String count) throws IOException {

        if (id.isEmpty() || count.isEmpty() || date.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }
        Product product = new ProductIDArriveDateCount(Integer.parseInt(id), date, Integer.parseInt(count));
        String jsonRequest = jsonAdapter.objectToJson(product);
        System.out.println(jsonRequest);

        URL url = new URL("http://localhost:8080/arrivedProducts/arrive");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        connection.disconnect();
    }
    public void deadProduct(String id, String date, String count) throws IOException {
        if (id.isEmpty() || count.isEmpty() || date.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }
        Product product = new ProductIDDeadDateCount(Integer.parseInt(id), date, Integer.parseInt(count));
        String jsonRequest = jsonAdapter.objectToJson(product);
        System.out.println(jsonRequest);

        URL url = new URL("http://localhost:8080/deadProducts/dead");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        connection.disconnect();
    }
    public ObservableList getCountProducts(String type) throws IOException {
        StringBuilder response = Connection("http://localhost:8080/" + type, "GET");

        System.out.println(response.toString());
        ObservableList<Product> products = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());
            products = FXCollections.observableArrayList(JsonConverter(rootNode, "Amount"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    public String getTotalCountProducts(String type) throws IOException {
        StringBuilder response = Connection("http://localhost:8080/analytics/" + type, "GET");
        Product product = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.toString());
        product = jsonAdapter.jsonToObject(rootNode, ProductTotalCount.class);
        return product.toString();
    }
    public StringBuilder Connection(String URL, String requestMethod) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response;
    }
    public ArrayList<Product> JsonConverter(JsonNode rootNode, String productDetailType) throws IOException {
        ArrayList<Product> productList = new ArrayList<>();
        switch (productDetailType) {
            case "Price":
                for (JsonNode productNode : rootNode) {
                    Product product = jsonAdapter.jsonToObject(productNode, ProductIDNameCategoryPrice.class);
                    System.out.println(product);
                    productList.add(product);
                }
                break;
            case "Count":
                for (JsonNode productNode : rootNode) {
                    Product product = jsonAdapter.jsonToObject(productNode, ProductIDNameCount.class);
                    System.out.println(product);
                    productList.add(product);
                }
                break;
            case "Amount":
                for (JsonNode productNode : rootNode) {
                    Product product = jsonAdapter.jsonToObject(productNode, ProductNameIDCountDatePriceProductId.class);
                    System.out.println(product);
                    productList.add(product);
                }
                break;
        }
        return productList;
    }
}

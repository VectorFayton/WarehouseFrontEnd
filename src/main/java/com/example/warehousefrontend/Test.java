package com.example.warehousefrontend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.warehousefrontend.varianceOfProduct.Product;
import com.example.warehousefrontend.varianceOfProduct.ProductIDArriveDateCount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test {

    public static void main(String[] args) throws IOException {
        List<Product> products = getWarehouseInfo();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static List<Product> getWarehouseInfo() throws IOException {
        List<Product> products = new ArrayList<>();
        String jsonResponse = getResponseFromURL("http://localhost:8080/warehouse");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        for (JsonNode productNode : jsonNode) {
            Product product = objectMapper.treeToValue(productNode, Product.class);
            products.add(product);
        }
        return products;
    }

    public static void arriveProduct(String id, String date, String count) throws IOException {
        if (id.isEmpty() || count.isEmpty() || date.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        Product product = new ProductIDArriveDateCount(Integer.parseInt(id), date, Integer.parseInt(count));
        sendProductInfoToURL("http://localhost:8080/arrivedProducts/arrive", product);
    }

    public static void deadProduct(String id, String date, String count) throws IOException {
        if (id.isEmpty() || count.isEmpty() || date.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        Product product = new ProductIDArriveDateCount(Integer.parseInt(id), date, Integer.parseInt(count));
        sendProductInfoToURL("http://localhost:8080/deadProducts/dead", product);
    }

    private static String getResponseFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed to retrieve data. Response Code: " + responseCode);
        }

        String jsonResponse;
        try (Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8)) {
            jsonResponse = scanner.useDelimiter("\\A").next();
        }

        connection.disconnect();
        return jsonResponse;
    }

    private static void sendProductInfoToURL(String urlString, Product product) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest;
        try {
            jsonRequest = objectMapper.writeValueAsString(product);
            System.out.println(jsonRequest);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Product information sent successfully!");
            } else {
                System.out.println("Failed to send product information. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
package com.example.warehousefrontend;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GraphicsView extends VBox {
    private static final String serverURL = "http://localhost:8080/warehouse/getAllProducts";
    URL url;
    HttpURLConnection httpURLConnection;
    OutputStream outputStream = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;

    public GraphicsView() {
        Button getBtn = new Button("GET запрос");
//            getBtn.setOnAction(e -> sendHttpRequest(BASE_URL, HttpRequest.Method.GET));

        Button postBtn = new Button("POST запрос");


        Button putBtn = new Button("PUT запрос");
//            putBtn.setOnAction(e -> sendHttpRequest(BASE_URL, HttpRequest.Method.PUT));

        Button deleteBtn = new Button("DELETE запрос");
//            deleteBtn.setOnAction(e -> sendHttpRequest(BASE_URL, HttpRequest.Method.DELETE));

        getChildren().addAll(getBtn, postBtn, putBtn, deleteBtn);
    }
    private void sendHttpRequest(String apiUrl) throws IOException {
        Map<String, String> post = new HashMap<>();
        post.put("user", "Bob");
        post.put("password", "123");

        StringBuilder stringBuilder = new StringBuilder();
        byte[] out = post.toString().getBytes();

        try {
            url = new URL(serverURL);
            httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
            httpURLConnection.addRequestProperty("Content-Type", "application/x-www-form-url-urlencoded");

            httpURLConnection.setConnectTimeout(200);
            httpURLConnection.setReadTimeout(200);
            httpURLConnection.connect();

            try {
                outputStream =httpURLConnection.getOutputStream();
                outputStream.write(out);
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } finally {
            try {
                inputStreamReader.close();
            } catch(IOException e){
                e.printStackTrace();
            }
            bufferedReader.close();
            outputStream.close();
        }
    }
}


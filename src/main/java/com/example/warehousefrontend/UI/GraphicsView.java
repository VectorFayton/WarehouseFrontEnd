package com.example.warehousefrontend.UI;

import com.example.warehousefrontend.Manager;
import com.example.warehousefrontend.varianceOfProduct.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private TableView<Product> tableView;
    Manager manager = new Manager();
    ObservableList<Product> products = manager.getAllProducts();
    public GraphicsView() throws IOException {
        tableView = new TableView<>();
        tableView.setItems(products);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        GridPane searchGrid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(4);
        grid.setPadding(new Insets(5));

        Label countDeathLabel = new Label("Amount of seized products:");
        Label countArrivedLabel = new Label("Amount of arrived products:");


        Button countDeathButton = new Button("Show seized products");
        Button countArrivedButton = new Button("Show arrived products");

        TextField countDeathField = new TextField();
        TextField countArrivedField = new TextField();

        countDeathButton.setOnAction(e -> {
            try {
                products = manager.sortProduct("asc");
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        countArrivedButton.setOnAction(e -> {
            try {
                products = manager.sortProduct("desc");
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        countDeathField.setText("1000");
        countDeathField.setEditable(false);
        countArrivedField.setText("1000");
        countArrivedField.setEditable(false);

        TableColumn<Product, Integer> productIdColumn = new TableColumn<>("Product ID");
        TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
        TableColumn<Product, Double> productPriceColumn = new TableColumn<>("Product Price");
        TableColumn<Product, String> productCategoryColumn = new TableColumn<>("Category ID");

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

        tableView.getColumns().addAll(productIdColumn, productNameColumn, productPriceColumn, productCategoryColumn);

        tableView.setItems(products);

        grid.addRow(0, countDeathLabel, countDeathField);
        grid.addRow(1, countArrivedLabel, countArrivedField);
        grid.addRow(2, countDeathButton, countArrivedButton);


        getChildren().add(searchGrid);
        getChildren().add(tableView);
        getChildren().add(grid);
    }
}


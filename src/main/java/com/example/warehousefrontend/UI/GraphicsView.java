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
    ObservableList<Product> products = manager.getCountProducts("deadProducts");
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

        Label countLabel = new Label("Amount products:");
        Label statusTable = new Label("Seized products");
        statusTable.setStyle("-fx-font: 24 arial;");


        Button countDeathButton = new Button("Show seized products");
        Button countArrivedButton = new Button("Show arrived products");

        TextField countField = new TextField();

        countDeathButton.setOnAction(e -> {
            try {
                statusTable.setText("Seized products");
                products = manager.getCountProducts("deadProducts");
                countField.setText(manager.getTotalCountProducts("amountDead"));
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        countArrivedButton.setOnAction(e -> {
            try {
                statusTable.setText("Arrived products");
                products = manager.getCountProducts("arrivedProducts");
                countField.setText(manager.getTotalCountProducts("amountArrive"));
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        countField.setText(manager.getTotalCountProducts("amountDead"));
        countField.setEditable(false);

        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Product, Integer> productIdColumn = new TableColumn<>("Product ID");
        TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
        TableColumn<Product, Integer> productCountColumn = new TableColumn<>("Product Count");
        TableColumn<Product, Double> productPriceColumn = new TableColumn<>("Product Price");
        TableColumn<Product, String> productDateColumn = new TableColumn<>("Date");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().addAll(idColumn, productIdColumn, productNameColumn, productCountColumn, productPriceColumn, productDateColumn);

        tableView.setItems(products);

        grid.addRow(0, countLabel, countField);
        grid.addRow(1, countDeathButton, countArrivedButton);


        getChildren().add(statusTable);
        getChildren().add(tableView);
        getChildren().add(grid);
    }
}


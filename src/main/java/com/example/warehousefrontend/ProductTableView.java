package com.example.warehousefrontend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProductTableView extends VBox {
    private TableView<Product> tableView;
    Manager manager = new Manager();
    ObservableList<Product> products = manager.getAllProducts();
    public ProductTableView() throws IOException {
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

        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label priceLabel = new Label("Price:");
        Label categoryLabel = new Label("Category ID:");
        Label searchLabel = new Label("Search: ");
        Button addButton = new Button("Add Product");
        Button removeButton = new Button("Remove Product");
        Button searchButton = new Button("Search Product");
        Button sortAscButton = new Button("Sort Asc");
        Button sortDescButton = new Button("Sort Desc");

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField categoryField = new TextField();
        TextField searchField = new TextField();

        searchButton.setOnAction(e -> {
            try {
                manager.searchProducts(tableView, products, "GET");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        addButton.setOnAction(e -> {
            try {
                manager.addProduct(nameField.getText(), priceField.getText(), categoryField.getText());
                tableView.setItems(manager.getAllProducts());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e -> {
            try {
                manager.removeProduct(idField.getText());
                tableView.setItems(manager.getAllProducts());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        searchButton.setOnAction(e -> {
            try {
                manager.searchProducts(tableView, products, searchField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        sortAscButton.setOnAction(e -> {
            try {
                products = manager.sortProduct("asc");
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        sortDescButton.setOnAction(e -> {
            try {
                products = manager.sortProduct("desc");
                tableView.setItems(products);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        idField.setPromptText("examle: 1");
        nameField.setPromptText("example: Apples");
        priceField.setPromptText("example: 45.67");
        categoryField.setPromptText("example: 21");
        searchField.setPromptText("name of product...");

        HBox buttonBox = new HBox(10, addButton, removeButton);
        HBox searchBox = new HBox(10, searchField, searchButton, sortAscButton, sortDescButton);

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

        grid.addRow(0, idLabel, idField);
        grid.addRow(1, removeButton);
        grid.addRow(3, nameLabel, nameField);
        grid.addRow(4, priceLabel, priceField);
        grid.addRow(5, categoryLabel, categoryField);
        grid.addRow(6, addButton);

        searchGrid.add(searchLabel, 0, 0);
        searchGrid.addRow(3, searchBox);

        getChildren().add(searchGrid);
        getChildren().add(tableView);
        getChildren().add(grid);
    }
}

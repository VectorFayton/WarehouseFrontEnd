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

import java.io.IOException;

public class WarehouseTableView extends VBox {
    private TableView<Product> tableView;
    Manager manager = new Manager();
    ObservableList<Product> products = manager.getWarehouseInfo();
    public WarehouseTableView() throws IOException {
        tableView = new TableView<>();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        GridPane searchGrid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(4);
        grid.setPadding(new Insets(5));

        Label idLabel = new Label("ID:");
        Label dateLabel = new Label("Date:");
        Label countLabel = new Label("Count:");
        Button addButton = new Button("Add Product");
        Button removeButton = new Button("Remove Product");

        TextField idField = new TextField();
        TextField dateField = new TextField();
        TextField countField = new TextField();

        addButton.setOnAction(e -> {
            try {
                System.out.println("Hello!");
                manager.arriveProduct(idField.getText(), dateField.getText(), countField.getText());
                tableView.setItems(manager.getWarehouseInfo());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e -> {
            try {
                System.out.println("Hello!");
                manager.deadProduct(idField.getText(), dateField.getText(), countField.getText());
                tableView.setItems(manager.getWarehouseInfo());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        idField.setPromptText("example: 1");
        dateField.setPromptText("example: 2023-01-10");
        countField.setPromptText("example: 990");

        HBox buttonBox = new HBox(10, addButton, removeButton);

        TableColumn<Product, Integer> productIdColumn = new TableColumn<>("Product ID");
        TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
        TableColumn<Product, Integer> productCountColumn = new TableColumn<>("Product Count");

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));


        tableView.getColumns().addAll(productIdColumn, productNameColumn, productCountColumn);

        tableView.setItems(products);

        grid.addRow(0, idLabel, idField);
        grid.addRow(1, dateLabel, dateField);
        grid.addRow(2, countLabel, countField);
        grid.addRow(3, removeButton, addButton);


        getChildren().add(searchGrid);
        getChildren().add(tableView);
        getChildren().add(grid);
    }
}

package com.example.warehousefrontend;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;


public class UI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ProductTableView productTableView = new ProductTableView();
        WarehouseTableView warehouseTableView = new WarehouseTableView();
        GraphicsView graphicsView = new GraphicsView();


        Tab firstTab = new Tab("Products", productTableView);
        Tab secondTab = new Tab("Warehouse", warehouseTableView);
        Tab thirdTab = new Tab("Analytics", graphicsView);

        TabPane tabPane = new TabPane(firstTab, secondTab, thirdTab);

        Scene scene = new Scene(tabPane, 800, 750);
        primaryStage.setTitle("Warehouse System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
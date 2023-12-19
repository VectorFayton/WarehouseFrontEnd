module com.example.warehousefrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    opens com.example.warehousefrontend to com.fasterxml.jackson.databind, javafx.fxml;

    exports com.example.warehousefrontend;
    exports com.example.warehousefrontend.varianceOfProduct;
    opens com.example.warehousefrontend.varianceOfProduct to com.fasterxml.jackson.databind, javafx.fxml;
}
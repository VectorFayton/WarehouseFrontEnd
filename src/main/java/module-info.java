module com.example.warehousefrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;

    opens com.example.warehousefrontend to javafx.fxml;
    exports com.example.warehousefrontend;
}
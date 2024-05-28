module com.example.jan22023 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jan22023 to javafx.fxml;
    exports com.example.jan22023;
}
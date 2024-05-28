module com.example.smestaj22 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.smestaj22 to javafx.fxml;
    exports com.example.smestaj22;
}
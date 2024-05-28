module com.example.nastava2019 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nastava2019 to javafx.fxml;
    exports com.example.nastava2019;
}
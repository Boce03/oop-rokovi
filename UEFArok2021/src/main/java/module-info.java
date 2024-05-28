module com.example.uefarok2021 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uefarok2021 to javafx.fxml;
    exports com.example.uefarok2021;
}
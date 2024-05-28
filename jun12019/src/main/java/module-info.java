module com.example.jun12019 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jun12019 to javafx.fxml;
    exports com.example.jun12019;
}
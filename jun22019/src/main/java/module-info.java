module com.example.jun22019 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jun22019 to javafx.fxml;
    exports com.example.jun22019;
}
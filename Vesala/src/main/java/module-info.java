module com.example.bogdan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bogdan to javafx.fxml;
    exports com.example.bogdan;
}
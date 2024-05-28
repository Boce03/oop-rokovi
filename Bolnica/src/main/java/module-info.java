module com.example.bolnica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bolnica to javafx.fxml;
    exports com.example.bolnica;
}
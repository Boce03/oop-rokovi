module com.example.lozinke {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lozinke to javafx.fxml;
    exports com.example.lozinke;
}
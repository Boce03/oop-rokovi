module com.example.jan12023paketici {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jan12023paketici to javafx.fxml;
    exports com.example.jan12023paketici;
}
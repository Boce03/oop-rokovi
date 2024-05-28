module com.example.sportkseigre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sportkseigre to javafx.fxml;
    exports com.example.sportkseigre;
}
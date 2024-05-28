module com.example.prodavnicajun2019 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prodavnicajun2019 to javafx.fxml;
    exports com.example.prodavnicajun2019;
}
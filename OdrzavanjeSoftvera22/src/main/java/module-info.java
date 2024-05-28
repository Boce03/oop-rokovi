module com.example.odrzavanjesoftvera22 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.odrzavanjesoftvera22 to javafx.fxml;
    exports com.example.odrzavanjesoftvera22;
}
package com.example.sportkseigre;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(30);
        koren.setPadding(new Insets(20, 20, 20, 20));
        VBox vbLevi = new VBox(10);

        VBox vbDesni = new VBox(20);
        vbDesni.setAlignment(Pos.CENTER_LEFT);

        koren.getChildren().addAll(vbLevi, vbDesni);

        //vbLevi
        Label lbEsportovi = new Label("E-sportovi:");
        TextArea taEsportovi = new TextArea();
        taEsportovi.setEditable(false);

        Label lbSportovi = new Label("Sportovi:");
        TextArea taSportovi = new TextArea();
        taSportovi.setEditable(false);

        vbLevi.getChildren().addAll(lbEsportovi, taEsportovi, lbSportovi, taSportovi);

        //vbDesni
        RadioButton rbSortNasumicno = new RadioButton("Sortiraj nasumicno");
        rbSortNasumicno.setSelected(true);
        RadioButton rbSortUspesnost = new RadioButton("Sortiraj po uspesnosti");
        ToggleGroup tg = new ToggleGroup();
        rbSortNasumicno.setToggleGroup(tg);
        rbSortUspesnost.setToggleGroup(tg);

        Button btEsportovi = new Button("E-sportovi");
        Button btSportovi = new Button("Sportovi");
        Button btSvi = new Button("Svi");

        vbDesni.getChildren().addAll(rbSortNasumicno, rbSortUspesnost, btEsportovi, btSportovi, btSvi);

        //akcije

        //scena
        Scene scena = new Scene(koren, 800, 400);
        stage.setTitle("Sportovi");
        stage.setScene(scena);
        stage.show();
    }
}

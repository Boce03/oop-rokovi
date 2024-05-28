package com.example.lozinke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static boolean ucitaniPodaci = false;
    private static Recnik recnik = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);
        HBox hbGornje = new HBox(10);
        koren.getChildren().addAll(hbGornje, taIspis);

        //hbGornje
        VBox vbLevi = new VBox(10);
        VBox vbDesni = new VBox(10);
        hbGornje.getChildren().addAll(vbLevi, vbDesni);

        //vbLevi
        Button btUcitaj = new Button("Ucitaj");

        RadioButton rbNasumicno = new RadioButton("Nasumicno");
        rbNasumicno.setSelected(true);
        RadioButton rbRecnik = new RadioButton("Napad recnikom");
        RadioButton rbRecnikSaCiframa = new RadioButton("Napad recnikom sa ciframa");
        ToggleGroup tg = new ToggleGroup();
        rbNasumicno.setToggleGroup(tg);
        rbRecnikSaCiframa.setToggleGroup(tg);
        rbRecnik.setToggleGroup(tg);

        Button btZapocniNapad = new Button("Zapocni napad");

        vbLevi.getChildren().addAll(btUcitaj, rbNasumicno, rbRecnik, rbRecnikSaCiframa, btZapocniNapad);

        //vbDesni
        Label lbLozinka = new Label("Lozinka koju treba pogoditi: ");

        TextField tfLozinka = new TextField();
        tfLozinka.setEditable(false);

        vbDesni.getChildren().addAll(lbLozinka, tfLozinka);

        //akcije
        btUcitaj.setOnAction(e->{
            taIspis.clear();
            recnik = Recnik.ucitajRecnik();
            if(recnik == null){
                taIspis.appendText("Neuspesno ucitani podaci\n");
                return;
            }

            taIspis.appendText("Uspesno ucitani podaci\n" + recnik + "\n");

            Rec lozinka = recnik.odaberiNasumicno();
            tfLozinka.setText(lozinka.getRec());
            ucitaniPodaci = true;
        });

        btZapocniNapad.setOnAction(e->{
            if(!ucitaniPodaci){
                taIspis.appendText("\nMorate prvo ucitati podatke\n");
                return;
            }

            Algoritam algoritam = null;
            Okruzenje okruzenje = new Okruzenje(new Rec(tfLozinka.getText()), taIspis);

            if(rbNasumicno.isSelected()){
                algoritam = new NasumicnoAlgoritam(okruzenje);
            } else if(rbRecnik.isSelected()){
                algoritam = new RecnikAlgoritam(okruzenje, recnik);
            } else {
                algoritam = new RecnikSaBrojevimaAlgoritam(okruzenje, recnik);
            }

            var lozinka = algoritam.izvrsi();
            if(lozinka.isEmpty()){
                taIspis.appendText("Lozinka nije pogodjena! Lozinka je bila " + tfLozinka.getText());
            } else{
                taIspis.appendText("Lozinka je pogodjena! Lozinka je bila " + lozinka.get().getRec());
            }

            ucitaniPodaci = false;
        });

        //scena
        Scene scena = new Scene(koren, 600, 400);
        stage.setScene(scena);
        stage.setTitle("Terminator");
        stage.show();
    }
}

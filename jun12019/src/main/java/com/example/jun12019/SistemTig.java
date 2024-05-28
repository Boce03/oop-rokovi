package com.example.jun12019;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class SistemTig extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        List<Izmena> podaci = new LinkedList<>();

        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        HBox gornji = new HBox(10);
        HBox donji = new HBox(10);
        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);
        koren.getChildren().addAll(gornji, taIspis, donji);

        //hbox gornji
        Button btUcitaj = new Button("Ucitaj");
        Button btSacuvaj = new Button("Sacuvaj");
        gornji.getChildren().addAll(btUcitaj, btSacuvaj);

        //hbox donji
        VBox levi = new VBox(10);
        VBox desni = new VBox(10);
        donji.getChildren().addAll(levi, desni);

        //vbox levi
        Label lbAutor = new Label("Autor");
        TextField tfAutor = new TextField();
        Label lbDatum = new Label("Datum");
        TextField tfDatum = new TextField();
        Label lbPoruka = new Label("Poruka");
        TextField tfPoruka = new TextField();
        levi.getChildren().addAll(lbAutor, tfAutor, lbPoruka, tfPoruka, lbDatum, tfDatum);

        //vbox desni
        RadioButton rbOpcija1 = new RadioButton("Nova funkcionalnost");
        rbOpcija1.setSelected(true);
        RadioButton rbOpcija2 = new RadioButton("Ispravljen bag");
        RadioButton rbOpcija3 = new RadioButton("Baterija testova");
        ToggleGroup tg = new ToggleGroup();
        rbOpcija1.setToggleGroup(tg);
        rbOpcija2.setToggleGroup(tg);
        rbOpcija3.setToggleGroup(tg);
        Button btDodaj = new Button("Dodaj");
        desni.getChildren().addAll(rbOpcija1, rbOpcija2, rbOpcija3, btDodaj);

        //akcije
        btUcitaj.setOnAction(e->{
            try {
                List<String> linije = Files.readAllLines(Paths.get("zahtevi.txt"));
                for(String linija: linije){
                    String[] ulaz = linija.split(",");

                    if(ulaz[0].trim().equals("ir")){
                        podaci.add(new IzmenaRegularna(new Zaglavlje(ulaz[1].trim(), ulaz[3].trim()), ulaz[4].trim(),
                                Integer.parseInt(ulaz[2].trim()),
                                TipRegularneIzmene.izBroja(Integer.parseInt(ulaz[5].trim()))));
                    } else if(ulaz[0].equals("iz")){
                        podaci.add(new IzmenaZahtev(new Zaglavlje(ulaz[1].trim(), ulaz[3].trim()), ulaz[4].trim(),
                                Integer.parseInt(ulaz[2].trim())));
                    }else{
                        podaci.add(new IzmenaPrihvatanjeZahteva(new Zaglavlje(ulaz[1].trim(), ulaz[3].trim()), ulaz[4].trim(),
                                Integer.parseInt(ulaz[2].trim()), Integer.parseInt(ulaz[5].trim())));
                    }
                }

                taIspis.clear();
                podaci.sort((p1, p2)-> Integer.compare(p2.getId(), p1.getId()));
                for(Izmena iz : podaci){
                    taIspis.appendText(iz + "\n\n");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        btSacuvaj.setOnAction(e->{
            List<String> izlaz = new LinkedList<>();
            for(Izmena iz: podaci){
                izlaz.add(iz.serijalizuj());
            }

            try {
                Files.write(Paths.get("zahtevi.txt"), izlaz);
                taIspis.appendText("\nPodaci su sacuvani!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        btDodaj.setOnAction(e->{
            String autor = tfAutor.getText();
            String poruka = tfPoruka.getText();
            String datum = tfDatum.getText();

            if(autor.isEmpty() || poruka.isEmpty() || datum.isEmpty()) return;

            TipRegularneIzmene tipIzmene;
            if(rbOpcija1.isSelected()){
                tipIzmene = TipRegularneIzmene.NovaFunkcionalnost;
            } else if(rbOpcija2.isSelected()){
                tipIzmene = TipRegularneIzmene.IspravljenBag;
            } else{
                tipIzmene = TipRegularneIzmene.BaterijaTestova;
            }

            podaci.add(new IzmenaRegularna(new Zaglavlje(autor, datum), poruka, tipIzmene));

            taIspis.clear();
            podaci.sort((p1, p2)-> Integer.compare(p2.getId(), p1.getId()));
            for(Izmena iz : podaci){
                taIspis.appendText(iz + "\n\n");
            }
        });

        //scena
        Scene scena = new Scene(koren, 500, 500);
        stage.setScene(scena);
        stage.setTitle("The Tig");
        stage.show();
    }
}

package com.example.jan12023paketici;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DedaMraz extends Application {
    private List<Paketic> paketici = new LinkedList<>();
    private List<UredjenPar<Poklon, Integer>> spisakPoklona = new ArrayList<>();
    private boolean ucitaniPaketici = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        VBox vbDesni = new VBox(10);
        VBox vbLevi = new VBox(10);
        koren.getChildren().addAll(vbLevi, vbDesni);

        //vbLevi
        Label lbImePrezime = new Label("Ime i prezime:");
        TextField tfImePrezime = new TextField();

        Label lbPol = new Label("Pol deteta:");
        RadioButton rbMuski = new RadioButton("Muski");
        RadioButton rbZenski = new RadioButton("Zenski");
        ToggleGroup tg = new ToggleGroup();
        rbMuski.setToggleGroup(tg);
        rbZenski.setToggleGroup(tg);

        Label lbBudzet = new Label("Budzet za paketice");
        TextField tfBudzet = new TextField();

        Button btNapuniPaketic = new Button("Napuni paketic");

        vbLevi.getChildren().addAll(lbImePrezime, tfImePrezime,
                                    lbPol, rbMuski, rbZenski,
                                    lbBudzet, tfBudzet, btNapuniPaketic);

        //vbDesni
        TextArea taIspis = new TextArea();
        taIspis.setPrefSize(300, 400);
        taIspis.setEditable(false);

        HBox hbDugmici = new HBox(10);
        Button btUcitaj = new Button("Ucitaj poklone");
        Button btSpisak = new Button("Prikazi spisak za Deda Mraza");
        hbDugmici.getChildren().addAll(btUcitaj, btSpisak);

        vbDesni.getChildren().addAll(taIspis, hbDugmici);



        //akcije
        btUcitaj.setOnAction(e->{
            try {
                List<String> linije = Files.readAllLines(Paths.get("pokloni.txt"));

                for(String linija: linije){
                    String[] ulaz = linija.split(",");
                    Poklon poklon = null;
                    int cena = Integer.parseInt(ulaz[2].trim());
                    String tip = ulaz[0].substring(0, 2);

                    if(tip.equals("SN")){
                        poklon = new Slanis(ulaz[1].trim(), ulaz[0].trim(), cena);
                    } else if(tip.equals("ST")){
                        poklon = new Slatkis(ulaz[1].trim(), ulaz[0].trim(), cena);
                    }
                    else{
                        char skracenica = tip.charAt(1);
                        poklon = new Igracka(ulaz[1].trim(), ulaz[0].trim(), cena,
                                            VrstaIgracke.odSkracenice(skracenica));
                    }

                    spisakPoklona.add(new UredjenPar<>(poklon, 0));
                }

                btUcitaj.setDisable(true);
                ucitaniPaketici = true;
            } catch (IOException | NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        btNapuniPaketic.setOnAction(e->{
            try{
                taIspis.clear();

                if(!ucitaniPaketici){
                    taIspis.appendText("Nije ucitan spisak poklona!");
                    return;
                }

                String imePrezime = tfImePrezime.getText().trim();
                if(imePrezime.isEmpty()){
                    taIspis.appendText("Neispravni podaci!");
                    return;
                }

                String b = tfBudzet.getText().trim();
                if(b.isEmpty()){
                    taIspis.appendText("Neispravni podaci!");
                    return;
                }

                int budzet = Integer.parseInt(b);

                if(!rbMuski.isSelected() && !rbZenski.isSelected()){
                    taIspis.appendText("Morate izabrati pol deteta!");
                    return;
                }

                PolDeteta pol;
                if(rbMuski.isSelected()){
                    pol = PolDeteta.MUSKI;
                } else{
                    pol = PolDeteta.ZENSKI;
                }

                Paketic paketic = new Paketic(new Dete(imePrezime, pol), budzet);
                paketic.napuniPaketic(spisakPoklona);

                taIspis.appendText(paketic + "\n");
                paketici.add(paketic);
            } catch (NumberFormatException ex){
                taIspis.appendText("Neispravni podaci!");
            }
        });

        btSpisak.setOnAction(e->{
            taIspis.clear();
            if(spisakPoklona.isEmpty()){
                taIspis.appendText("Nema dostupnih poklona!");
                return;
            }

            spisakPoklona.sort((p1, p2) ->
                    Integer.compare(p1.getPrvi().getCena(), p2.getPrvi().getCena()));

            for(UredjenPar<Poklon, Integer> par: spisakPoklona){
                taIspis.appendText(par.getPrvi() + " - kom " + par.getDrugi() + "\n");
            }
        });

        //scena
        Scene scena = new Scene(koren, 530, 400);
        stage.setScene(scena);
        stage.setTitle("Deda Mrazov pomocnik");
        stage.show();
    }
}

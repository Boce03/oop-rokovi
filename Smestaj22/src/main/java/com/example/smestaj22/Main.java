package com.example.smestaj22;

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
import java.util.Random;

public class Main extends Application {
    private List<Hotel<PremiumSoba>> premiumHotel = new LinkedList<>();
    private List<Hotel<ObicnaSoba>> obicanHotel = new LinkedList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(20);
        koren.setPadding(new Insets(30, 30, 30, 30));
        VBox vbLevi = new VBox(20);
        VBox vbDesni = new VBox(20);
        koren.getChildren().addAll(vbLevi, vbDesni);

        //vbLevi
        Label lbIme = new Label("Ime:");
        TextField tfIme = new TextField();
        tfIme.setPrefHeight(35);

        Label lbBudzet = new Label("Budzet:");
        TextField tfBudzet = new TextField();
        tfBudzet.setPrefHeight(35);

        Label lbOcena = new Label("Ocena:");
        TextField tfOcena = new TextField();
        tfOcena.setPrefHeight(35);

        Label lbOd = new Label("Od:");
        TextField tfOd = new TextField();
        tfOd.setPrefHeight(35);

        Label lbDo = new Label("Do:");
        TextField tfDo = new TextField();
        tfDo.setPrefHeight(35);

        CheckBox chPremium = new CheckBox("Premium korisnik");

        vbLevi.getChildren().addAll(lbIme, tfIme, lbBudzet, tfBudzet, lbOcena, tfOcena, lbOd, tfOd, lbDo, tfDo, chPremium);

        //vbDesni
        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);
        taIspis.setPrefSize(300, 500);

        Button btUcitaj = new Button("Ucitaj smestaje");
        Button btPronadji = new Button("Pronadji smestaj");
        Button btPregled = new Button("Pregled");

        vbDesni.getChildren().addAll(taIspis, btUcitaj, btPronadji, btPregled);

        //akcije
        btUcitaj.setOnAction(e->{
            try {
                List<String> linije = Files.readAllLines(Paths.get("hoteli.txt"));
                Random random = new Random();
                for(String linija: linije){
                    String[] ulaz = linija.split(",");
                    int stotina = random.nextInt(1, 10);
                    for(String s: ulaz){
                        System.out.print(s + " ");
                    }
                    System.out.println();

                    if(ulaz[1].trim().equals("P")){
                        Hotel<PremiumSoba> hotel = new Hotel<>(ulaz[0].trim());
                        PremiumSoba.setBrojac(stotina * 100);
                        for(int i = 2; i < ulaz.length - 1; i += 2){
                            int cena = Integer.parseInt(ulaz[i].trim());
                            double ocena = Double.parseDouble(ulaz[i + 1].trim());
                            hotel.dodajSobu(new PremiumSoba(cena, ocena));
                        }

                        hotel.sortirajSobe();
                        premiumHotel.add(hotel);
                    } else{
                        Hotel<ObicnaSoba> hotel = new Hotel<>(ulaz[0].trim());
                        ObicnaSoba.setBrojac(stotina * 100);
                        for(int i = 1; i < ulaz.length; i++){
                            int cena = Integer.parseInt(ulaz[i].trim());
                            hotel.dodajSobu(new ObicnaSoba(cena));
                        }

                        hotel.sortirajSobe();
                        obicanHotel.add(hotel);
                    }
                }

                btUcitaj.setVisible(false);
            } catch (IOException | NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        btPronadji.setOnAction(e->{
            taIspis.clear();
            if(tfIme.getText().isEmpty() || tfBudzet.getText().isEmpty() || tfOcena.getText().isEmpty()
                || tfOd.getText().isEmpty() || tfDo.getText().isEmpty()){
                    taIspis.appendText("\nMorate uneti sve podatke o gostu!\n");
                    return;
            }

            String ime = tfIme.getText().trim();

            int budzet = Integer.parseInt(tfBudzet.getText().trim());
            if(budzet < 0){
                taIspis.appendText("\nBudzet ne moze biti negativan!\n");
                return;
            }

            double ocena = Double.parseDouble(tfOcena.getText().trim());
            if(ocena < 1 || ocena > 10){
                taIspis.appendText("\nOcena mora biti realan broj izmedju 1 i 10!\n");
                return;
            }

            String datumOd = tfOd.getText().trim();
            if(!Datum.validanDatum(datumOd)){
                taIspis.appendText("\nMorate uneti validan datum!\n");
                return;
            }

            String datumDo = tfDo.getText().trim();
            if(!Datum.validanDatum(datumDo)){
                taIspis.appendText("\nMorate uneti validan datum!\n");
                return;
            }

            Gost gost = new Gost(ime, budzet, ocena, chPremium.isSelected());

            Termin termin = new Termin(new Datum(datumOd), new Datum(datumDo));
            if(!termin.validanTermin()){
                taIspis.appendText("\nMorate uneti validan termin!\n");
                return;
            }

            if(premiumHotel.isEmpty() && obicanHotel.isEmpty()){
                taIspis.appendText("Hoteli i sobe nisu ucitani!\n");
                return;
            }

            if(gost.isPremium()){
                for(Hotel<PremiumSoba> hotel: premiumHotel){
                    var soba = hotel.smesti(termin, gost);
                    if(soba.isPresent()){
                        taIspis.appendText("Gost: " + gost + "\n");
                        taIspis.appendText("smesten je u: " + hotel.getNaziv() + " " + soba.get() + "\n");
                        taIspis.appendText("u terminu: " + termin + "\n");
                        return;
                    }
                }

                for(Hotel<ObicnaSoba> hotel: obicanHotel){
                    var soba = hotel.smesti(termin, gost);
                    if(soba.isPresent()){
                        taIspis.appendText("Gost: " + gost + "\n");
                        taIspis.appendText("smesten je u: " + hotel.getNaziv() + " " + soba.get() + "\n");
                        taIspis.appendText("u terminu: " + termin + "\n");
                        return;
                    }
                }

                taIspis.appendText("Smestaj nije nadjen\n");
            } else {
                for (Hotel<ObicnaSoba> hotel : obicanHotel) {
                    var soba = hotel.smesti(termin, gost);
                    if (soba.isPresent()) {
                        taIspis.appendText("Gost: " + gost + "\n");
                        taIspis.appendText("smesten je u: " + hotel.getNaziv() + " " + soba.get() + "\n");
                        taIspis.appendText("u terminu: " + termin + "\n");
                        return;
                    }
                }

                for (Hotel<PremiumSoba> hotel : premiumHotel) {
                    var soba = hotel.smesti(termin, gost);
                    if (soba.isPresent()) {
                        taIspis.appendText("Gost: " + gost + "\n");
                        taIspis.appendText("smesten je u: " + hotel.getNaziv() + " " + soba.get() + "\n");
                        taIspis.appendText("u terminu: " + termin + "\n");
                        return;
                    }
                }
                taIspis.appendText("Smestaj nije nadjen\n");
            }
        });

        btPregled.setOnAction(e->{
            premiumHotel.forEach(h->taIspis.appendText(h.toString()));
            obicanHotel.forEach(h->taIspis.appendText(h.toString()));
        });

        //scena
        Scene scena = new Scene(koren, 600, 600);
        stage.setScene(scena);
        stage.setTitle("Smestaj");
        stage.show();
    }
}

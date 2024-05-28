package com.example.odrzavanjesoftvera22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PracenjeStavki extends Application {
    private ListaPitanja pitanja = new ListaPitanja();
    private SkupBagova bagovi = new SkupBagova();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));

        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);
        taIspis.setPrefSize(400, 300);

        VBox vbDesni = new VBox(10);

        koren.getChildren().addAll(taIspis, vbDesni);

        //vbDesni
        Label lbNaslov = new Label("Naslov:");
        TextField tfNaslov = new TextField();

        Label lbKorisnik = new Label("Korisnik:");
        TextField tfKorisnik = new TextField();

        Label lbSadrzaj = new Label("Sadrzaj:");
        TextField tfSadrzaj = new TextField();
        tfSadrzaj.setPrefHeight(200);
        tfSadrzaj.setAlignment(Pos.TOP_LEFT);

        HBox hbIzbori = new HBox(30);
        hbIzbori.setAlignment(Pos.CENTER);
        RadioButton rbPitanje = new RadioButton("Pitanje");
        rbPitanje.setSelected(true);
        RadioButton rbBag = new RadioButton("Bag");
        ToggleGroup tg = new ToggleGroup();
        rbPitanje.setToggleGroup(tg);
        rbBag.setToggleGroup(tg);
        hbIzbori.getChildren().addAll(rbPitanje, rbBag);

        HBox hbPoljaUnos = new HBox(10);
        VBox pom = new VBox(10);
        Label lbOdgovor = new Label("Odgovor:");
        TextField tfOdgovor = new TextField();
        pom.getChildren().addAll(lbOdgovor, tfOdgovor);
        VBox vbPoljaUnos = new VBox(10);
        hbPoljaUnos.getChildren().addAll(pom, vbPoljaUnos);

        Label lbId = new Label("ID:");
        TextField tfId = new TextField();
        Label lbOzbiljnost = new Label("Ozbiljnost:");
        TextField tfOzbiljnost = new TextField();
        Label lbZaduzen = new Label("Zaduzen:");
        TextField tfZaduzen = new TextField();
        CheckBox chRazresen = new CheckBox("Razresen");
        vbPoljaUnos.getChildren().addAll(lbId, tfId, lbOzbiljnost, tfOzbiljnost, lbZaduzen, tfZaduzen, chRazresen);

        HBox hbDugmici = new HBox(25);
        hbDugmici.setAlignment(Pos.CENTER);
        Button btDodaj = new Button("Dodaj");
        Button btIzlistaj = new Button("Izlistaj");
        Button btSacuvaj = new Button("Sacuvaj");
        hbDugmici.getChildren().addAll(btDodaj, btIzlistaj, btSacuvaj);

        vbDesni.getChildren().addAll(lbNaslov, tfNaslov, lbKorisnik, tfKorisnik, lbSadrzaj, tfSadrzaj
                                    , hbIzbori, hbPoljaUnos, hbDugmici);
        //akcije
        btDodaj.setOnAction(e->{
            String korisnik = tfKorisnik.getText();
            if(korisnik.isEmpty()){
                taIspis.appendText("Morate uneti korisnika!\n\n");
                return;
            }

            String naslov = tfNaslov.getText();
            if(naslov.isEmpty()){
                taIspis.appendText("Morate uneti naslov!\n\n");
                return;
            }

            String sadrzaj = tfSadrzaj.getText();
            if(sadrzaj.isEmpty()){
                taIspis.appendText("Morate uneti sadrzaj!\n\n");
                return;
            }

            if(rbPitanje.isSelected()){
                Pitanje p = new Pitanje(korisnik, naslov, sadrzaj);
                String odgovor = tfOdgovor.getText();
                if(!odgovor.isEmpty()){
                    p.razresi(odgovor);
                }
                pitanja.dodaj(p);
                taIspis.appendText("Dodato pitanje:" + p + "\n\n");
            } else{
                String id = tfId.getId();
                if(id.isEmpty()){
                    taIspis.appendText("\nMorate uneti ID baga!\n");
                    return;
                }

                int brojBaga = 0;
                try{
                    brojBaga = Integer.parseInt(id);
                    if(brojBaga < 0){
                        taIspis.appendText("Nevalidan id baga!\n\n");
                        return;
                    }

                } catch (NumberFormatException ei){
                    taIspis.appendText("Nevalidan id baga!\n\n");
                }

                String ozbiljno = tfOzbiljnost.getText();
                if(ozbiljno.isEmpty()){
                    taIspis.appendText("Morate uneti ozbliljnost baga!\n\n");
                    return;
                }

                int ozbiljnost;
                try{
                    ozbiljnost = Integer.parseInt(ozbiljno);
                    if(ozbiljnost < 0){
                        taIspis.appendText("Nevalidna ozbiljnost baga!\n\n");
                        return;
                    }

                } catch (NumberFormatException ej){
                    taIspis.appendText("Nevalidna ozbiljnost baga!\n\n");
                    return;
                }

                Bag b = new Bag(korisnik, naslov, sadrzaj, brojBaga, ozbiljnost);

                String zaduzen = tfZaduzen.getText();
                if(!zaduzen.isEmpty()){
                    b.zaduzi(zaduzen);
                }

                if(!bagovi.dodaj(b)){
                    taIspis.appendText("Bag sa unetim id-em vec postoji!\n\n");
                }
            }
        });

        btIzlistaj.setOnAction(e->{
            taIspis.appendText("-------------\n");
            bagovi.listaj().forEach(b->taIspis.appendText(b + "\n"));
            pitanja.listaj().forEach(p->taIspis.appendText(p + "\n"));
            taIspis.appendText("-------------\n");
        });

        btSacuvaj.setOnAction(e->{
            if(Files.exists(Paths.get("spisak.txt"))){

            }
        });

        //scena
        Scene scena = new Scene(koren, 550, 550);
        stage.setScene(scena);
        stage.setTitle("Pracenje bagova v1.0");
        stage.show();
    }
}

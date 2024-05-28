package com.example.prodavnicajun2019;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Prodavnica extends Application {
    private Racun racun = new Racun();

    public static void main(String[] args) {
        Kasa.ucitajArtikle();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        koren.setAlignment(Pos.TOP_CENTER);

        Button btPrikaziAkcije = new Button("Prikazi akcije");
        btPrikaziAkcije.setAlignment(Pos.CENTER);

        HBox hbOstatak = new HBox(10);

        koren.getChildren().addAll(btPrikaziAkcije, hbOstatak);

        //hbOstatak
        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);
        taIspis.setMinSize(300, 450);

        VBox vbDesni = new VBox(10);
        vbDesni.setPadding(new Insets(100, 0, 0, 0));

        hbOstatak.getChildren().addAll(taIspis, vbDesni);

        //vbDesni
        Label lbSifra = new Label("Sifra:");
        TextField tfSifra = new TextField();
        Label lbBrKomada = new Label("Komada:");
        TextField tfBrKomada = new TextField();
        Button btOtkucaj = new Button("Otkucaj");
        Button btPonisti = new Button("Ponisti");
        Button btZavrsi = new Button("Zavrsi");
        vbDesni.getChildren().addAll(lbSifra, tfSifra, lbBrKomada, tfBrKomada, btOtkucaj, btPonisti, btZavrsi);

        //akcije
        btPrikaziAkcije.setOnAction(e->{
            List<Artikal> naAkciji = Kasa.artikliNaAkciji();

            if(naAkciji.isEmpty()){
                taIspis.appendText("Nema artikla na akciji\n");
                return;
            }

            taIspis.appendText("Na akciji:\n");
            for(Artikal artikal: naAkciji){
                taIspis.appendText(artikal + " " + artikal.getAkcija() + "\n");
            }

            taIspis.appendText("\n");
        });

        btOtkucaj.setOnAction(e->{
            try{
                if(tfSifra.getText().isEmpty() || tfBrKomada.getText().isEmpty()){
                    taIspis.appendText("Morate uneti vrednosti!\n");
                    return;
                }

                int sifra = Integer.parseInt(tfSifra.getText());
                Optional<Artikal> artikal = Kasa.ocitajArtikal(sifra);
                if(artikal.isEmpty()){
                    taIspis.appendText("Ne postoji artikal sa sifrom " + sifra + "...\n");
                    return;
                }

                int brKomada = Integer.parseInt(tfBrKomada.getText());
                if(brKomada < 1){
                    taIspis.appendText("Broj komada ne sme biti manji od 1\n");
                    return;
                }

                racun.dodajStavku(artikal.get(), brKomada);
                taIspis.appendText("Otkucano: " + artikal.get() + "\n");

            } catch(NumberFormatException p){
                taIspis.appendText("Neispravano unet podatak!");
            }
        });

        btPonisti.setOnAction(e->{
            try{
                if(tfSifra.getText().isEmpty()){
                    taIspis.appendText("Morate uneti sifru\n");
                    return;
                }

                int sifra = Integer.parseInt(tfSifra.getText());
                if(sifra < 0){
                    taIspis.appendText("Neispravno uneta sifra\n");
                    return;
                }

                Optional<Artikal> artikal = racun.getStavke().keySet().stream().
                                            filter(a -> a.getSifra() == sifra).findAny();

                if(artikal.isEmpty()){
                    taIspis.appendText("Artikal sa sifrom " + sifra + " nije otkucan\n");
                } else{
                    taIspis.appendText("Ponisten: " + artikal.get().getNaziv() + "\n");
                    racun.ukloniStavku(artikal.get());
                }
            } catch (NumberFormatException p){
                taIspis.appendText("Neispravno uneta sifra!\n");
            }
        });

        btZavrsi.setOnAction(e->{
            if(racun.getStavke().isEmpty()) {
                taIspis.appendText("Nema stavki na racunu\n");
            } else{
                taIspis.appendText(racun + "\n");
                racun = new Racun();
            }
        });

        //scena
        Scene scena = new Scene(koren, 600, 500);
        stage.setScene(scena);
        stage.setTitle("Prodavnica");
        stage.show();
    }
}

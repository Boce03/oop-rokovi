package com.example.telefoni20212022;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TelefonskiListing extends Application {

    private Map<Broj, List<Usluga>> evidentiraneUsluge = new TreeMap<>();

    public void ucitavanje(ChoiceBox<Broj> chBrojevi){
        try {
            List<String> linije = Files.readAllLines(Paths.get("evidentirane_usluge.txt"));
            for(String linija: linije){
                int id = linija.indexOf("\"");
                String[] pom = linija.split("\"");
                String[] ulaz = pom[0].split(",");

                Usluga usluga = null;
                String vreme = ulaz[0].trim();
                Broj brojOd = new Broj(ulaz[1].trim());
                Broj brojKa = new Broj(ulaz[2].trim());

                if(id != -1){
                    System.out.println("poruka");
                    boolean poslata = ulaz[3].trim().equals("1");
                    String tekst = linija.substring(id);
                    usluga = new Poruka(vreme, brojOd, brojKa, tekst, poslata);
                } else{
                    int trajanje = Integer.parseInt(ulaz[3].trim());
                    usluga = new Poziv(vreme, brojOd, brojKa, trajanje);
                }

                if(evidentiraneUsluge.containsKey(brojOd)){
                    evidentiraneUsluge.get(brojOd).add(usluga);
                } else{
                    evidentiraneUsluge.put(brojOd, new LinkedList<>(List.of(usluga)));
                }

                if(evidentiraneUsluge.containsKey(brojKa)){
                    evidentiraneUsluge.get(brojKa).add(usluga);
                } else{
                    evidentiraneUsluge.put(brojKa, new LinkedList<>(List.of(usluga)));
                }
            }

            evidentiraneUsluge.keySet().forEach(b -> chBrojevi.getItems().add(b));
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void ucitavanje1() throws IOException {
        List<String> lista = Files.readAllLines(Paths.get("evidentirane_usluge.txt"));
        for(String s: lista){
            String[] ulaz = s.split("\"");
            System.out.println(ulaz[0]);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));

        HBox hbIzbor = new HBox(10);
        hbIzbor.setAlignment(Pos.CENTER);

        TextArea taIspis = new TextArea();
        taIspis.setEditable(false);

        koren.getChildren().addAll(hbIzbor, taIspis);

        //hbIzbor
        Label lbBrTelefona = new Label("Broj telefona: ");
        ChoiceBox<Broj>  chBrojevi = new ChoiceBox<>();
        chBrojevi.setPrefWidth(180);
        Button btListing = new Button("Listing");
        hbIzbor.getChildren().addAll(lbBrTelefona, chBrojevi, btListing);

        //akcije
        ucitavanje(chBrojevi);
        //ucitavanje1();

        btListing.setOnAction(e->{
            taIspis.clear();
            Broj broj = null;
            broj = chBrojevi.getValue();

            if(broj == null){
                taIspis.appendText("Morate izabrati broj!");
                return;
            }

            List<Usluga> listaUsluga = evidentiraneUsluge.get(broj);
            Collections.sort(listaUsluga);

            listaUsluga.forEach(u -> taIspis.appendText(u + "\n"));

            double ukupnaCena = listaUsluga.stream().mapToDouble(Usluga::cena).reduce(0, Double::sum);
            taIspis.appendText("\nUkupna cena: " + ukupnaCena);
        });


        //scena
        Scene scena = new Scene(koren, 700, 200);
        stage.setScene(scena);
        stage.setTitle("Generisanje telefonskog listinga");
        stage.show();
    }
}

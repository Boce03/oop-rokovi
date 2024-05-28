package com.example.uefarok2021;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UEFA extends Application {
    private static boolean chBoxSelected = true;
    private UEFATakmicenje takmicenje = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        //koren
        HBox koren = new HBox(30);
        koren.setPadding(new Insets(10, 10, 10, 10));
        VBox vbDesno = new VBox(30);

        TextArea taIspis = new TextArea();
        taIspis.setMaxSize(300, 400);
        taIspis.setEditable(false);

        koren.getChildren().addAll(taIspis, vbDesno);

        //vbDesno
        vbDesno.setAlignment(Pos.CENTER);
        Button btUcitaj = new Button("Ucitaj");
        CheckBox chSortiraj = new CheckBox("Sortiraj");

        //vbOpcije
        VBox vbOpcije = new VBox(10);
        RadioButton rbRastuce = new RadioButton("Rastuce");
        rbRastuce.setDisable(true);

        RadioButton rbOpadajuce = new RadioButton("Opadajuce");
        rbOpadajuce.setDisable(true);

        ToggleGroup tg = new ToggleGroup();
        rbOpadajuce.setToggleGroup(tg);
        rbRastuce.setToggleGroup(tg);

        vbOpcije.getChildren().addAll(rbRastuce, rbOpadajuce);

        Button btTimovi = new Button("Timovi");
        Button btGrupe = new Button("Grupe");

        vbDesno.getChildren().addAll(btUcitaj, chSortiraj, vbOpcije, btTimovi, btGrupe);

        //akcije
        chSortiraj.setOnAction(e->{
            chBoxSelected = !chBoxSelected;
            rbOpadajuce.setDisable(chBoxSelected);
            rbRastuce.setDisable(chBoxSelected);
            rbRastuce.setSelected(false);
            rbOpadajuce.setSelected(false);
        });

        btUcitaj.setOnAction(e->{
            try {
                Map<String, Grupa> mp = new TreeMap<>();
                List<String> linije = Files.readAllLines(Paths.get("takmicenje.txt"));
                List<Tim> timovi = new LinkedList<>();
                if(linije.isEmpty()) return;

                String[] t = linije.removeFirst().split(" ");
                int koeficijentTakmicenja = Integer.parseInt(t[1].trim());

                for(String linija: linije){
                    String[] ulaz = linija.split(" ");
                    String nazivTima = ulaz[0].trim();
                    List<Double> koeficijenti = new LinkedList<>();
                    for(int i = 1; i <= 5; i++){
                        koeficijenti.add(Double.parseDouble(ulaz[i].trim()));
                    }
                    if(ulaz[6].equals("Povlasceni")){
                        Tim tim = new Nosilac(nazivTima, koeficijenti);
                        if(!mp.containsKey(ulaz[7])){
                            mp.put(ulaz[7], new Grupa(ulaz[7]));
                        }

                        mp.get(ulaz[7]).dodajTim(tim);
                        timovi.add(tim);
                    } else{
                        double nacionalniKoef = Double.parseDouble(ulaz[7]);
                        Tim tim = new Nenosilac(nazivTima, koeficijenti, nacionalniKoef);
                        if(!mp.containsKey(ulaz[8])){
                            mp.put(ulaz[8], new Grupa(ulaz[8]));
                        }

                        mp.get(ulaz[8]).dodajTim(tim);
                        timovi.add(tim);
                    }
                }

                if(t[0].equals("LS")){
                    takmicenje = new LigaSampiona("Liga Sampiona",
                            timovi,
                            mp.values().stream().toList(),
                            koeficijentTakmicenja);
                } else if(t[0].equals("LE")){
                    takmicenje = new LigaSampiona("Liga Evrope",
                            timovi,
                            mp.values().stream().toList(),
                            koeficijentTakmicenja);
                } else{
                    takmicenje = new LigaSampiona("Liga Konferencije",
                            timovi,
                            mp.values().stream().toList(),
                            koeficijentTakmicenja);
                }
            } catch (IOException | NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        btTimovi.setOnAction(e->{
            taIspis.clear();
            if(takmicenje == null){
                taIspis.appendText("\nTimovi nisu jos ucitani\n");
                return;
            }

            if(takmicenje.getTimovi().isEmpty()){
                taIspis.appendText("\nNema timova u takmicenju.\n");
                return;
            }

            if(chSortiraj.isSelected()){
                if(!rbRastuce.isSelected() && !rbOpadajuce.isSelected()){
                    taIspis.appendText("\nMorate izabrati opciju sortiranja\n");
                    return;
                }

                takmicenje.getTimovi().sort((t1, t2) -> t1.getIme().compareTo(t2.getIme()));

                if(rbOpadajuce.isSelected()){
                    Collections.reverse(takmicenje.getTimovi());
                }
            } else{
                Collections.shuffle(takmicenje.getTimovi());
            }

            taIspis.appendText(takmicenje + "\n\n");
            taIspis.appendText(takmicenje.prikaziSveTimove());
        });

        btGrupe.setOnAction(e->{
            taIspis.clear();
            if(takmicenje == null){
                taIspis.appendText("\nGrupe nisu jos ucitane\n");
                return;
            }

            if(takmicenje.getGrupe().isEmpty()){
                taIspis.appendText("\nNema grupi\n");
                return;
            }

            taIspis.appendText(takmicenje + "\n\n");
            taIspis.appendText(takmicenje.prikaziGrupe());
        });

        //scena
        Scene scena = new Scene(koren, 500, 400);
        stage.setTitle("UEFA takmicenje");
        stage.setScene(scena);
        stage.show();
    }
}

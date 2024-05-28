package com.example.jan22023;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Tocilica extends Application {
    private List<Bure<Pivo>> buradi = new ArrayList<>();
    private Map<Pivo, Double> mapa = new TreeMap<>();

    private void ucitaj(){
        try {
            List<String> linije = Files.readAllLines(Paths.get("piva.txt"));
            for(String linija: linije){
                String[] ulaz = linija.split(" ");
                Pivo p = null;
                String naziv = ulaz[2].trim();
                String zemljaPorekla = ulaz[4].trim();
                double kolicina = Double.parseDouble(ulaz[1].trim());
                double abv = Double.parseDouble(ulaz[3].trim());

                if(ulaz[0].equals("Psenicno")){
                    int procenatPsenice = Integer.parseInt(ulaz[5].trim());
                    p = new Psenicno(zemljaPorekla, naziv, abv, procenatPsenice);
                } else if(ulaz[0].equals("IPA")){
                    int IBU = Integer.parseInt(ulaz[5].trim());
                    p = new IPA(zemljaPorekla, naziv, abv, IBU);
                } else{
                    p = new Lager(zemljaPorekla, naziv, abv);
                }

                buradi.add(new Bure<>(p, kolicina));
                mapa.put(p, 0.0);
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(20, 20, 20, 20));
        HBox hbGornji = new HBox(10);

        VBox vbLevi = new VBox(10);

        HBox hbDonji = new HBox(10);
        hbDonji.setAlignment(Pos.CENTER);

        koren.getChildren().addAll(hbGornji, hbDonji);

        //hbGornji
        TextArea taIspis = new TextArea();
        taIspis.setPrefSize(300, 300);
        taIspis.setEditable(false);

        vbLevi.getChildren().addAll(taIspis, hbDonji);

        VBox vbDesni = new VBox(10);
        hbGornji.getChildren().addAll(vbLevi, vbDesni);

        //vbDesni
        Label lbVrstaPiva = new Label("Vrsta piva:");
        RadioButton rbLager = new RadioButton("Lager");
        RadioButton rbPsenicno = new RadioButton("Psenicno");
        RadioButton rbIPA = new RadioButton("IPA");
        ToggleGroup tg = new ToggleGroup();
        rbLager.setToggleGroup(tg);
        rbPsenicno.setToggleGroup(tg);
        rbIPA.setToggleGroup(tg);

        HBox hbKolicina = new HBox(10);
        TextArea taKolicina = new TextArea();
        taKolicina.setPrefSize(30, 30);
        Label lbLitar = new Label("L");
        hbKolicina.getChildren().addAll(taKolicina, lbLitar);

        Button btNatoci = new Button("Natoci");

        vbDesni.getChildren().addAll(lbVrstaPiva, rbLager, rbPsenicno, rbIPA, hbKolicina, btNatoci);

        //hbDonji
        Button btStanje = new Button("Stanje na tocilici");
        Button btPopijeno = new Button("Popijeno");
        hbDonji.getChildren().addAll(btStanje, btPopijeno);

        //akcije
        ucitaj();

        btStanje.setOnAction(e->{
            if(buradi.isEmpty()){
                taIspis.appendText("Nema dostupnih buradi piva\n");
                return;
            }

            taIspis.clear();
            Collections.sort(buradi);
            buradi.forEach(b->taIspis.appendText(b + "\n"));
        });

        btNatoci.setOnAction(e->{
            taIspis.clear();
            if(!rbLager.isSelected() && !rbPsenicno.isSelected() && !rbIPA.isSelected()){
                taIspis.appendText("Morate izabrati tip piva!");
                return;
            }

            if(taKolicina.getText().isEmpty()){
                taIspis.appendText("Morate uneti zeljenu kolicinu piva!");
                return;
            }

            if(buradi.isEmpty()){
                taIspis.appendText("Nema buradi!\n");
                return;
            }

            double kolicina;

            try{
                kolicina = Double.parseDouble(taKolicina.getText());
            }catch (NumberFormatException ei){
                throw new RuntimeException(ei);
            }

            List<Bure<Pivo>> lista = null;

            if(rbLager.isSelected()){
                lista = buradi.stream().filter(b->b.getPivo() instanceof Lager).toList();
            } else if(rbPsenicno.isSelected()){
                lista = buradi.stream().filter(b->b.getPivo() instanceof Psenicno).toList();
            } else{
                lista = buradi.stream().filter(b->b.getPivo() instanceof IPA).toList();
            }


            for(Bure<Pivo> b: lista){
                if(b.dovoljnoZaTocenje(kolicina)){
                    double cena = b.natoci(kolicina);
                    taIspis.appendText("Natocili ste " + kolicina + b.getPivo()
                                        + "\nVas racun je: " + String.format("%.1f", cena) + "din.\n");
                    double staraKolicina = mapa.get(b.getPivo());
                    mapa.put(b.getPivo(), staraKolicina + kolicina);
                    return;
                }
            }

            taIspis.appendText("Ne postoji bure sa zeljenom kolicinom trazenog piva");
        });

        btPopijeno.setOnAction(e->{
            taIspis.clear();

            List<Map.Entry<Pivo, Double>> ispisLista = mapa.entrySet().stream().sorted((v1, v2)->{
                return Double.compare(v2.getValue(), v1.getValue());
            }).toList();

            taIspis.appendText("Do sada je ispijeno: \n");
            for(Map.Entry<Pivo, Double> p: ispisLista){
                taIspis.appendText(String.format("%.1f", p.getValue()) + " litara " + p.getKey() + "\n");
            }
        });

        //scena
        Scene scena = new Scene(koren, 500, 300);
        stage.setScene(scena);
        stage.setTitle("Tocilica");
        stage.show();
    }
}

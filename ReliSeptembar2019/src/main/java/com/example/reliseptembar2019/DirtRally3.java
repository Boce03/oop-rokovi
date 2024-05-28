package com.example.reliseptembar2019;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.IllegalFormatException;
import java.util.List;

public class DirtRally3 extends Application {
    private static ReliRaspored raspored;

    public static void main(String[] args) {
        raspored = new ReliRaspored();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        TextArea taIspis = new TextArea();
        taIspis.setMinSize(580, 440);
        taIspis.setEditable(false);
        VBox vbDesni = new VBox(10);
        koren.getChildren().addAll(taIspis, vbDesni);

        //vbDesni
        TextField tfIme = new TextField();

        HBox hbOpcije = new HBox(10);
        RadioButton rbGrupaA = new RadioButton("Grupa A");
        RadioButton rbGrupaB = new RadioButton("Grupa B");
        ToggleGroup tg = new ToggleGroup();
        rbGrupaB.setToggleGroup(tg);
        rbGrupaA.setToggleGroup(tg);
        hbOpcije.getChildren().addAll(rbGrupaA, rbGrupaB);

        TextField tfModel = new TextField();
        TextField tfPogon = new TextField("Pogon");
        TextField tfGodiste = new TextField();

        HBox hbTurbo = new HBox(10);
        Label lbTurbo = new Label("Supercharger: ");
        RadioButton rbImaTurbo = new RadioButton();
        RadioButton rbNemaTurbo = new RadioButton();
        ToggleGroup tg1 = new ToggleGroup();
        rbImaTurbo.setToggleGroup(tg1);
        rbNemaTurbo.setToggleGroup(tg1);
        hbTurbo.getChildren().addAll(lbTurbo, rbImaTurbo, rbNemaTurbo);

        TextField tfSat = new TextField();
        TextField tfMinut = new TextField();

        Button btIzlistaj = new Button("Izlistaj");
        Button btDodaj = new Button("Dodaj");
        Button btBrAutomobila = new Button("Broj automobila");
        Button btSaPogonom = new Button("Sa pogonom");
        vbDesni.getChildren().addAll(tfIme, hbOpcije, tfModel, tfPogon, tfGodiste,
                                    hbTurbo, tfSat, tfMinut, btIzlistaj, btDodaj,
                                    btBrAutomobila, btSaPogonom);

        //akcije
        btIzlistaj.setOnAction(e->{
            raspored.sortiraj();
            if(!raspored.getRedVoznje().isEmpty())
                taIspis.appendText("\n" + raspored + "\n");
            else
                taIspis.appendText("\nNema vozaca!\n");
        });

        btDodaj.setOnAction(e->{
            try{
                String ime = tfIme.getText();
                if(ime.isEmpty()){
                    taIspis.appendText("\nMorate uneti ime!\n");
                    return;
                }

                if(!rbGrupaA.isSelected() && !rbGrupaB.isSelected()){
                    taIspis.appendText("\nMorate izabrati tip auta!\n");
                    return;
                }

                String model = tfModel.getText();
                if(model.isEmpty()){
                    taIspis.appendText("\nMorate uneti model!\n");
                    return;
                }

                Pogon p = Pogon.odNaziva(tfPogon.getText());

                int godiste = Integer.parseInt(tfGodiste.getText());
                if(godiste < 0 || godiste > 2024){
                    taIspis.appendText("\nNeispravno godiste!\n");
                    return;
                }

                if(rbGrupaB.isSelected()
                        && !rbImaTurbo.isSelected()
                        && !rbNemaTurbo.isSelected()){
                    taIspis.appendText("\nMorate odabrati turbo opciju\n");
                    return;
                }

                int sat = Integer.parseInt(tfSat.getText());
                if(sat < 0 || sat >= 24){
                    taIspis.appendText("\nNeispravan format sata\n");
                    return;
                }

                int minut = Integer.parseInt(tfMinut.getText());
                if(minut < 0 || minut >= 60){
                    taIspis.appendText("\nNeispravan format minuta\n");
                    return;
                }

                ReliAuto auto;
                if(rbGrupaA.isSelected()){
                    auto = new GrupaAReliAuto(model, p, godiste);
                } else{
                    auto = new GrupaBReliAuto(model, p, godiste, rbImaTurbo.isSelected());
                }

                if(raspored.dodaj(new ReliVozac(ime, auto), sat, minut)){
                    taIspis.appendText("\nDodato u " + sat + ":" + minut + "\n");
                } else{
                    taIspis.appendText("\nKonflikt u rasporedu za vreme " +
                                        sat + ":" + minut + "\n");
                }
            } catch (IllegalStateException e1){
                taIspis.appendText("\nNeispravan tip pogona!\n");
                return;
            } catch (RuntimeException e4){
                taIspis.appendText("\nNeispravni podaci!\n");
            }
        });

        btBrAutomobila.setOnAction(e->{
            try{
                int godiste = Integer.parseInt(tfGodiste.getText());
                if(godiste < 0 || godiste > 2024){
                    taIspis.appendText("\nNeispravan format godine!\n");
                    return;
                }

                int broj = raspored.brojAutomobilaSaGodistemVecimOd(godiste);
                taIspis.appendText("\nBroj automobila sa godistem vecim od "
                                    + godiste + ": " + broj + "\n");
            } catch(NumberFormatException p){
                taIspis.appendText("\nNeispravan format godine!\n");
                return;
            }
        });

        btSaPogonom.setOnAction(e->{
            try{
                Pogon pogon = Pogon.odNaziva(tfPogon.getText());
                List<ReliAuto> rez = raspored.saPogonom(pogon);

                if(rez.isEmpty()){
                    taIspis.appendText("\nNema trazenih automobila!\n");
                    return;
                }

                taIspis.appendText("\nSa pogonom " + pogon + ":\n");
                for(ReliAuto r: rez){
                    taIspis.appendText(r + "\n");
                }
            } catch (IllegalStateException i){
                taIspis.appendText("\nNepostojeci tip pogona\n");
                return;
            }
        });

        //scena
        if(raspored.ucitaj("vozaci.txt")){
            taIspis.appendText("\nUcitavanje je uspesno\n");
        } else{
            taIspis.appendText("\nUcitavanje nije uspesno\n");
        }

        Scene scena = new Scene(koren, 780, 460);
        stage.setScene(scena);
        stage.setTitle("Dirt Rally 3.0");
        stage.show();
    }
}

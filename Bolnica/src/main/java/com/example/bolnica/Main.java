package com.example.bolnica;

import com.example.bolnica.Bolnica.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {

    private Bolnica b = new Bolnica();

    private static <T> void ispis(List<T> lista, TextArea ta){
        ta.clear();
        for(T p : lista){
            ta.appendText(p + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(10);
        koren.setPadding(new Insets(10, 80, 10, 10));
        VBox levi = new VBox(30);
        VBox desni = new VBox(10);
        koren.getChildren().addAll(levi, desni);

        //levi vbox
        VBox gornji = new VBox(10);
        Button btUcitaj = new Button("Ucitaj pacijente");
        Button btSledeci = new Button("Sledeci");
        Label lbBrojDana = new Label("Broj dana: ");
        TextField tfBrojDana = new TextField();
        Button btUbrzajVreme = new Button("Ubrzaj vreme!");
        Label lbGreska = new Label();
        lbGreska.setTextFill(Color.RED);
        gornji.getChildren().addAll(btUcitaj, btSledeci, lbBrojDana, tfBrojDana, btUbrzajVreme, lbGreska);

        VBox donji = new VBox(10);
        RadioButton rbSve = new RadioButton("Sve");
        rbSve.setSelected(true);
        RadioButton rbKorona = new RadioButton("Korona");
        ToggleGroup tg = new ToggleGroup();
        rbSve.setToggleGroup(tg);
        rbKorona.setToggleGroup(tg);
        Button btPrikaziStatistike = new Button("Prikazi statistike");
        Button btUnesi = new Button("Unesi");
        donji.getChildren().addAll(rbSve, rbKorona, btPrikaziStatistike, btUnesi);

        levi.getChildren().addAll(gornji, donji);

        //desni vbox
        Label lbCekanje = new Label("Cekaju:");
        TextArea taCekanje = new TextArea();
        taCekanje.setEditable(false);

        Label lbIzolacija = new Label("U izolaciji:");
        TextArea taIzolacija = new TextArea();
        taIzolacija.setEditable(false);

        Label lbZdravi = new Label("Zdravi:");
        TextArea taZdravi = new TextArea();
        taZdravi.setEditable(false);

        desni.getChildren().addAll(lbCekanje, taCekanje, lbIzolacija, taIzolacija, lbZdravi, taZdravi);

        //akcije
        btUcitaj.setOnAction(e->{
            b.unesi();
        });

        btSledeci.setOnAction(e->{
            if(b.getCekaonica().isEmpty()){
                taCekanje.clear();
                taCekanje.setText("Nema vise nikoga u cekaonici!");
            } else{
                b.sledeci();
                ispis(b.getCekaonica(), taCekanje);
                ispis(b.getIzolacija(), taIzolacija);
                ispis(b.getZdravi(), taZdravi);
            }
        });

        btUcitaj.setOnAction(e->{
            b.ucitaj();
            b.getCekaonica().sort((Pacijent p1, Pacijent p2)->{
                ZaraznaBolest z1 = p1.getBolest();
                ZaraznaBolest z2 = p2.getBolest();

                if(z1 instanceof Korona && z2 instanceof Grip){
                    return -1;
                } else if(z1 instanceof Grip && z2 instanceof Korona){
                    return 1;
                }

                return Integer.compare(z2.getDuzinaBolesti(), z1.getDuzinaBolesti());
            });

            ispis(b.getCekaonica(), taCekanje);
            btUcitaj.setDisable(true);
        });

        btUbrzajVreme.setOnAction(e->{
            lbGreska.setText("");
            String vreme = tfBrojDana.getText();
            int brojDana = Integer.parseInt(vreme);

            if(brojDana < 0){
                lbGreska.setText("Greska!");
                return;
            }

            Iterator<Pacijent> it = b.getIzolacija().iterator();

            while(it.hasNext()){
                Pacijent p = it.next();
                p.leci(brojDana);
                if(p.izlecen()){
                    it.remove();
                    p.setDuzinaLecenja(0);
                    p.getBolest().setDuzinaBolesti(0);
                    b.getZdravi().add(p);
                }
            }

            taIzolacija.clear();
            ispis(b.getIzolacija(), taIzolacija);

            taZdravi.clear();
            ispis(b.getZdravi(), taZdravi);
        });

        btPrikaziStatistike.setOnAction(e->{
            double procenatZarazenih;
            double procenatIzlecenih;
            if(rbSve.isSelected()){
                procenatZarazenih = (double) b.getIzolacija().size() /
                        (b.getIzolacija().size() + b.getZdravi().size()) * 100.00;

                double brZarazenihCekaonica = 0;
                for(Pacijent p : b.getZdravi()){
                    if(p.isZarazen()){
                        brZarazenihCekaonica++;
                    }
                }
                procenatIzlecenih = b.getZdravi().size() / (b.getIzolacija().size() + brZarazenihCekaonica) * 100.00;

                ispis(b.getIzolacija(), taIzolacija);
                taIzolacija.appendText("\nProcenat zarazenih u odnosu na ukupan broj Pacijenata koji nisu u cekaonici: "
                 + procenatZarazenih + "%");

                ispis(b.getZdravi(), taZdravi);
                taZdravi.appendText("\nProcenat izlecenih u odnosu na ukupan broj zarazenih: " + procenatIzlecenih + "%");
            } else{

                int pom1 = 0, pom2 = 0;
                for(Pacijent p : b.getIzolacija()){
                    if(p.getBolest() instanceof Korona){
                        pom1++;
                    }
                }

                for(Pacijent p : b.getZdravi()){
                    if(p.getBolest() instanceof Korona){
                        pom2++;
                    }
                }

                procenatZarazenih = (double)b.getIzolacija().size() / (pom1 + pom2) * 100;
                ispis(b.getIzolacija(), taIzolacija);
                taIzolacija.appendText("\nProcenat zarazenih u odnosu na broj testiranih: "
                        + procenatZarazenih + "%");

                int pom3 = 0;
                for(Pacijent p : b.getZdravi()){
                    if(p.getBolest() instanceof Korona && p.isZarazen()){
                        pom3++;
                    }
                }

                procenatIzlecenih = (double)b.getZdravi().size() / (pom1 + pom3) * 100;
                ispis(b.getZdravi(), taZdravi);
                taZdravi.appendText("\nProcenat izlecenih u odnosu na broj zarazenih: " + procenatIzlecenih + "%");
            }
        });

        btUnesi.setOnAction(e->{
            b.unesi();
        });

        //scena
        Scene scena = new Scene(koren, 750, 650);
        stage.setScene(scena);
        stage.setTitle("Bolnica");

        stage.show();
    }
}

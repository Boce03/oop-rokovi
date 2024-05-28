package com.example.jun22019;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;

public class GSPVoziloGUI extends Application {
    private static GSPVozilo gsp = null;
    private static List<BusPlus> kartice = null;

    private static boolean neispravnoVozilo = false;
    private static boolean nemaPutnika = false;

    private Comparator<BusPlus> kriterijum = (p1, p2)->{
        if(p1 instanceof Personalizovana && p2 instanceof Nepersonalizovana) return -1;
        else if(p1 instanceof Nepersonalizovana && p2 instanceof Personalizovana) return 1;
        else if(p1 instanceof Personalizovana && p2 instanceof Personalizovana){
            return ((Personalizovana) p1).getKategorija().compareTo(((Personalizovana) p2).getKategorija());
        }

        return Integer.compare(((Nepersonalizovana) p2).getKredit(), ((Nepersonalizovana) p1).getKredit());
    };

    private void ispisPutnikaUListu(TextArea ta){
        kartice = gsp.getKartice();
        kartice.sort(kriterijum);

        for(BusPlus putnik: kartice){
            ta.appendText(putnik + "\n");
        }
    }

    public static void main(String[] args) {
        gsp = new GSPVozilo();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        HBox koren = new HBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));
        VBox vbLevi = new VBox(10);
        VBox vbDesni = new VBox(10);
        koren.getChildren().addAll(vbLevi, vbDesni);

        //vbLevi
        TextArea taLevi = new TextArea();
        taLevi.setPrefSize(500, 500);
        taLevi.setEditable(false);
        Button btPutniciUVozilu = new Button("Putnici u vozilu");

        // hbSrednji
        HBox hbSrednji = new HBox(10);
        RadioButton rbPersonalizovano = new RadioButton("Personalizovane");
        rbPersonalizovano.setSelected(true);
        RadioButton rbNepersonalizovano = new RadioButton("Nepersonalizovane");
        ToggleGroup tg = new ToggleGroup();
        rbPersonalizovano.setToggleGroup(tg);
        rbNepersonalizovano.setToggleGroup(tg);
        hbSrednji.getChildren().addAll(rbPersonalizovano, rbNepersonalizovano);

        Button btNoviPutnik = new Button("Novi putnik");
        vbLevi.getChildren().addAll(taLevi, btPutniciUVozilu, hbSrednji, btNoviPutnik);

        //vbDesni
        TextArea taDesni = new TextArea();
        taDesni.setPrefSize(500, 485);
        taDesni.setEditable(false);
        Button btKontrola = new Button("Kontrola");
        Button btNapustanjeVozila = new Button("Napustanje vozila");
        vbDesni.getChildren().addAll(taDesni, btKontrola, btNapustanjeVozila);

        //akcije
        btPutniciUVozilu.setOnAction(e->{
            neispravnoVozilo = false;

            if(gsp == null){
                taLevi.appendText("Vozilo je u kvaru!");
                neispravnoVozilo = true;
                return;
            }

            if(!gsp.putniciUVozilu("kartice.txt")){
                taLevi.appendText("Vozilo je trenutno bez putnika!");
                nemaPutnika = true;
                return;
            }

            ispisPutnikaUListu(taLevi);
        });

        btNoviPutnik.setOnAction(e->{
            if(neispravnoVozilo) return;
            nemaPutnika = false;

            if(rbPersonalizovano.isSelected()){
                taLevi.appendText("\n" + "Novi putnik u vozilu:\n" + gsp.noviPutnik(1));
            } else{
                taLevi.appendText("\n" + "Novi putnik u vozilu:\n" + gsp.noviPutnik(2));
            }
        });

        btKontrola.setOnAction(e->{
            if(neispravnoVozilo || nemaPutnika) return;

            taDesni.appendText(gsp.kontrola() + "\n");
        });

        btNapustanjeVozila.setOnAction(e->{
            if(neispravnoVozilo || nemaPutnika) return;

            if(gsp.izbaciPutnike()){
                taDesni.appendText("Putnici sa nevalidnim kartama su napustili vozilo\n");
                taDesni.appendText("U vozilu:\n");
                ispisPutnikaUListu(taDesni);
            } else{
                taDesni.appendText("Sve karte su validne!");
            }
        });

        //scena
        Scene scena = new Scene(koren, 700, 600);
        stage.setScene(scena);
        stage.setTitle("Gradski prevoz");
        stage.show();
    }
}

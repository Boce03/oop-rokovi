package com.example.nastava2019;

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

public class OceniNastavu extends Application {
    private Map<NastavniMaterijal, OcenaKvaliteta[]> nastavniMaterijal = new TreeMap<>();
    private List<NastavniMaterijal> preporuceno = new ArrayList<>();
    private List<NastavniMaterijal> ocenjeno = new ArrayList<>();
    private static Random random = new Random();

    private static NastavniMaterijal odabran = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //koren
        VBox koren = new VBox(10);
        koren.setPadding(new Insets(10, 10, 10, 10));

        Button btUcitaj = new Button("Ucitaj");

        TextArea taGornji = new TextArea();
        taGornji.setEditable(false);

        HBox hbUnos = new HBox(10);
        hbUnos.setAlignment(Pos.CENTER);

        HBox hbIzbor = new HBox(10);
        hbIzbor.setAlignment(Pos.CENTER);

        HBox hbOcena = new HBox(10);
        hbOcena.setAlignment(Pos.CENTER);

        TextArea taDonji = new TextArea();
        taDonji.setEditable(false);

        koren.getChildren().addAll(btUcitaj, taGornji, hbUnos, taDonji, hbIzbor, hbOcena);

        //hbUnos
        Label lbInfo1 = new Label("Unesite kriterijum pretrage:");
        TextField tfKriterijum = new TextField();
        Button btPretrazi = new Button("Pretrazi");
        hbUnos.getChildren().addAll(lbInfo1, tfKriterijum, btPretrazi);

        //hbIzbor
        RadioButton rbKorisno = new RadioButton("KORISNO");
        rbKorisno.setSelected(true);
        RadioButton rbInteresantno = new RadioButton("INTERESANTNO");
        RadioButton rbRazumljivo = new RadioButton("RAZUMLJIVO");

        ToggleGroup tg = new ToggleGroup();
        rbInteresantno.setToggleGroup(tg);
        rbKorisno.setToggleGroup(tg);
        rbRazumljivo.setToggleGroup(tg);

        hbIzbor.getChildren().addAll(rbKorisno, rbInteresantno, rbRazumljivo);

        //hbOcena
        Label lbInfo2 = new Label("Ocenite kvalitet materijala:");
        TextField tfOcena = new TextField();
        Button btOceni = new Button("Oceni");

        Button btSveOcene = new Button("Sve ocene");
        HBox hbPom = new HBox();
        hbPom.getChildren().add(btSveOcene);
        hbPom.setPadding(new Insets(0, 0, 0, 20));

        hbOcena.getChildren().addAll(lbInfo2, tfOcena, btOceni, hbPom);

        //akcije
        btUcitaj.setOnAction(e->{
            try {
                List<String> linije = Files.readAllLines(Paths.get("materijali.txt"));
                for(String linija: linije){
                    String[] ulaz = linija.split("[,;]");
                    String naslov = ulaz[0].trim();
                    String format = ulaz[1].trim();
                    OcenaKvaliteta[] ocene = new OcenaKvaliteta[3];
                    NastavniMaterijal kljuc = null;
                    int i, ciklus;

                    if(format.equals("mp4")){
                        int duzinaTrajanja = Integer.parseInt(ulaz[2].trim());
                        int brojPregleda = Integer.parseInt(ulaz[3].trim());
                        int brojSvidjanja = Integer.parseInt(ulaz[4].trim());

                        ciklus = (ulaz.length - 5) / 3;
                        i = 5;

                        kljuc = new Video(naslov, format, duzinaTrajanja, brojPregleda, brojSvidjanja);
                    } else{
                        boolean prateciSadrzaj = ulaz[2].equals("da");

                        ciklus = (ulaz.length - 3) / 3;
                        i = 3;

                        kljuc = new Tekstualni(naslov, format, prateciSadrzaj);
                    }

                    ocene[0] = new OcenaKvaliteta(Kvalitet.KORISNO);
                    for(int j = 0; j < ciklus; j++, i++){
                        ocene[0].dodajOcenu(Integer.parseInt(ulaz[i].trim()));
                    }

                    ocene[1] = new OcenaKvaliteta(Kvalitet.INTERESANTNO);
                    for(int j = 0; j < ciklus; j++, i++){
                        ocene[1].dodajOcenu(Integer.parseInt(ulaz[i].trim()));
                    }

                    ocene[2] = new OcenaKvaliteta(Kvalitet.RAZUMLJIVO);
                    for(int j = 0; j < ciklus; j++, i++){
                        ocene[2].dodajOcenu(Integer.parseInt(ulaz[i].trim()));
                    }

                    nastavniMaterijal.put(kljuc, ocene);
                }

                if(nastavniMaterijal.isEmpty()){
                    taGornji.appendText("Nema nastavnih materijalan\n");
                    return;
                }

                for(Map.Entry<NastavniMaterijal, OcenaKvaliteta[]> mp : nastavniMaterijal.entrySet()){
                    taGornji.appendText(mp.getKey() + "\n");
                    double ukProsecnaOcena = 0.0;

                    for(OcenaKvaliteta ocena: mp.getValue()){
                        taGornji.appendText(ocena + " ");
                        ukProsecnaOcena += ocena.prosecnaOcena();
                    }

                    taGornji.appendText("\nProsecna ocena: " + String.format("%.2f", ukProsecnaOcena / 3) + "\n\n\n");
                }
            } catch (IOException | NumberFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        btPretrazi.setOnAction(e->{
            taDonji.clear();
            String kriterijum = tfKriterijum.getText();
            if(kriterijum.isEmpty()){
                taDonji.appendText("Morate unteti kriterijum!\n");
                return;
            }

            preporuceno = nastavniMaterijal.keySet().stream().filter(k->k.zaOcenu(kriterijum)).toList();
            if(preporuceno.isEmpty()){
                taDonji.appendText("Nema preporucenih materijala\n");
                return;
            }

            preporuceno.forEach(p->taDonji.appendText(p + "\n"));

            boolean sviOcenjeni = true;
            for(NastavniMaterijal nm: preporuceno){
                if(!ocenjeno.contains(nm)){
                    sviOcenjeni = false;
                    break;
                }
            }

            if(sviOcenjeni){
                taDonji.appendText("Morate izabrati po nekom drugom kriterijumu, jer su svi preporuceni materijali ocenjeni");
                return;
            }

            odabran = preporuceno.get(random.nextInt(0, preporuceno.size()));
            while(ocenjeno.contains(odabran)){
                taDonji.appendText("\nOdabran materijal: " + odabran.getNaslov() + "\n");
                taDonji.appendText("\nMaterijal je vec ocenjen!\n");
                odabran = preporuceno.get(random.nextInt(0, preporuceno.size()));
            }

            taDonji.appendText("\nOdabran materijal: " + odabran.getNaslov() + "\n");
            taDonji.appendText("Postojece ocene:\n");
            for(OcenaKvaliteta oc: nastavniMaterijal.get(odabran)){
                taDonji.appendText(oc.sveOcene() + " ");
            }
            taDonji.appendText("\n");

            if(odabran.getFormat().equals("mp4")){
                ((Video) odabran).povecajPregled();

                if(random.nextBoolean()){
                    ((Video) odabran).povecajBrojLajkova();
                }
            }
        });

        btOceni.setOnAction(e->{
            try{
                int ocena = Integer.parseInt(tfOcena.getText());
                if(ocena < 1 || ocena > 5){
                    tfOcena.setText("Ocena nije ispravna!");
                    return;
                }

                taDonji.clear();
                boolean vecOcenjen = false;
                OcenaKvaliteta[] ocene = nastavniMaterijal.get(odabran);
                if(rbKorisno.isSelected()){
                    if(ocene[0].nijeOcenjeno()){
                        ocene[0].dodeljenaOcena();
                        ocene[0].dodajOcenu(ocena);
                    } else{
                        vecOcenjen = true;
                    }
                }else if(rbInteresantno.isSelected()){
                    if(ocene[1].nijeOcenjeno()){
                        ocene[1].dodeljenaOcena();
                        ocene[1].dodajOcenu(ocena);
                    } else{
                        vecOcenjen = true;
                    }
                }else{
                    if(ocene[2].nijeOcenjeno()){
                        ocene[2].dodeljenaOcena();
                        ocene[2].dodajOcenu(ocena);
                    } else{
                        vecOcenjen = true;
                    }
                }

                if(!vecOcenjen)
                    nastavniMaterijal.put(odabran, ocene);

                taDonji.appendText(odabran + "\n");
                for(OcenaKvaliteta o: nastavniMaterijal.get(odabran)){
                    taDonji.appendText(o.sveOcene() + " ");
                }

                if(!vecOcenjen)
                    taDonji.appendText("\nKvalitet je ocenjen!\n");

                if(!ocenjeno.contains(odabran))
                    ocenjeno.add(odabran);
            } catch(NumberFormatException ei){
                tfOcena.setText("Ocena nije ispravna!");
            }
        });

        btSveOcene.setOnAction(e->{
            taDonji.clear();
            if(ocenjeno.isEmpty()){
                taDonji.appendText("Nema ocenjenih materijala\n");
                return;
            }

            for(NastavniMaterijal nm: ocenjeno){
                taDonji.appendText(nm + "\n");
                for(OcenaKvaliteta o: nastavniMaterijal.get(nm)){
                    taDonji.appendText(o.sveOcene() + " ");
                }
                taDonji.appendText("\n");
            }
        });

        //scena
        Scene scena = new Scene(koren, 700, 500);
        stage.setScene(scena);
        stage.setTitle("Oceni kvalitet nastave");
        stage.show();
    }
}

package com.example.lozinke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Recnik {
    private List<Rec> reci = new ArrayList<>();
    private List<Rec> lozinke = new ArrayList<>();
    private static Random random = new Random();

    public List<Rec> getReci() {
        return reci;
    }

    public List<Rec> getLozinke() {
        return lozinke;
    }

    public void dodajRec(Rec rec){
        reci.add(rec);
    }

    public void dodajLozinke(Rec rec){
        lozinke.add(rec);
    }

    public void sortiraj(){
        Collections.sort(reci);
        Collections.sort(lozinke);
    }

    public Rec odaberiNasumicno(){
        return lozinke.get(random.nextInt(0, lozinke.size()));
    }

    @Override
    public String toString() {
        return "Broj reci u recniku: " + reci.size() + "\nBroj lozinki u recniku: " + lozinke.size();
    }

    public static Recnik ucitajRecnik(){
        try {
            List<String> linije = Files.readAllLines(Paths.get("recnik.txt"));
            Recnik recnik = new Recnik();

            for(String linija: linije){
                String[] ulaz = linija.split(",");
                if(ulaz[0].equals("r")){
                    recnik.dodajRec(new Rec(ulaz[1].trim()));
                } else{
                    recnik.dodajLozinke(new Rec(ulaz[1].trim()));
                }
            }

            recnik.sortiraj();
            return recnik;
        } catch (IOException e) {
            return null;
        }
    }
}

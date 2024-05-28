package com.example.uefarok2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public abstract class UEFATakmicenje {
    private String imeTakmicenja;
    private List<Tim> timovi;
    private List<Grupa> grupe;
    private int koeficijentTakmicenja;

    public UEFATakmicenje(String imeTakmicenja, List<Tim> timovi, List<Grupa> grupe, int koeficijentTakmicenja) {
        this.imeTakmicenja = imeTakmicenja;
        this.timovi = new ArrayList<>(timovi);
        this.grupe = new ArrayList<>(grupe);
        this.koeficijentTakmicenja = koeficijentTakmicenja;
    }

    public double nagradniFond(){
       double prosecanKoefTimova = 0.0;
       for (Tim t: timovi){
           prosecanKoefTimova += t.getKoeficijent();
       }
       prosecanKoefTimova /= timovi.size();

       return prosecanKoefTimova*koeficijentTakmicenja / timovi.size();
    }

    public String getImeTakmicenja() {
        return imeTakmicenja;
    }

    public List<Tim> getTimovi() {
        return timovi;
    }

    public List<Grupa> getGrupe() {
        return grupe;
    }

    public int getKoeficijentTakmicenja() {
        return koeficijentTakmicenja;
    }



    public abstract double jacinaTakmicenja();


    public String prikaziGrupe(){
        StringJoiner sj = new StringJoiner("\n");
        for(Grupa g: grupe){
            sj.add(g.toString() + "\n");
        }

        return sj.toString();
    }

    public String prikaziSveTimove(){
        StringJoiner sj = new StringJoiner("\n");
        for(Tim tim: timovi){
            sj.add(tim.toString());
        }

        return sj.toString();
    }

    @Override
    public String toString() {
        return imeTakmicenja + " "
                + koeficijentTakmicenja
                + " " + String.format("%.2f", jacinaTakmicenja())
                + " " + String.format("%.2f", nagradniFond());
    }
}

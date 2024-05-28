package com.example.uefarok2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Grupa {
    private String nazivGrupe;
    private TezinaZreba tz = new TezinaZreba(this);
    List<Tim> timovi;

    public Grupa(String nazivGrupe) {
        this.nazivGrupe = nazivGrupe;
        timovi = new ArrayList<>();
    }

    public void dodajTim(Tim tim){
        timovi.add(tim);
    }

    public String getNazivGrupe() {
        return nazivGrupe;
    }

    public List<Tim> getTimovi() {
        return timovi;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add(nazivGrupe);

        for(Tim tim: timovi){
            sj.add(tim.getIme() + " " + ((tim instanceof Povlasceni)? String.format("%.2f", tz.tezinaZreba()) : ""));
        }

        return sj.toString();
    }
}

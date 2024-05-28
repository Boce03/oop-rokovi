package com.example.sportkseigre;

public class Igrica {
    private String naziv;
    private int godinaIzlaska;

    public Igrica(String naziv, int godinaIzlaska) {
        this.naziv = naziv;
        this.godinaIzlaska = godinaIzlaska;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getGodinaIzlaska() {
        return godinaIzlaska;
    }
}

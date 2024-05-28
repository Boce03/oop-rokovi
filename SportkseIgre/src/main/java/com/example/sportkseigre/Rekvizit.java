package com.example.sportkseigre;

public class Rekvizit {
    private String naziv;
    private int cena;

    public Rekvizit(String naziv, int cena) {
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getCena() {
        return cena;
    }

}

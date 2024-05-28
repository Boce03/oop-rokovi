package com.example.jan12023paketici;

public abstract class Poklon {
    private String naziv;
    private String ID;
    private int cena;

    public Poklon(String naziv, String ID, int cena) {
        this.naziv = naziv;
        this.ID = ID;
        this.cena = cena;
    }

    public int getCena() {
        return cena;
    }

    public abstract boolean prikladanPoklon(PolDeteta polDeteta);

    @Override
    public String toString() {
        return ID + " - " + naziv + ", " + cena + " din";
    }
}

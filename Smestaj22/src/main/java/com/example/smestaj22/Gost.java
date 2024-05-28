package com.example.smestaj22;

public class Gost {
    private String ime;
    private int budzet;
    private double ocena;
    private boolean premium;

    public Gost(String ime, int budzet, double ocena, boolean premium) {
        this.ime = ime;
        this.budzet = budzet;
        this.ocena = ocena;
        this.premium = premium;
    }

    public String getIme() {
        return ime;
    }

    public int getBudzet() {
        return budzet;
    }

    public double getOcena() {
        return ocena;
    }

    public boolean isPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return ime + " " + "(" + ocena + ")";
    }
}

package com.example.uefarok2021;

import java.util.List;

public abstract class Tim {
    private String ime;
    private double koeficijent;

    public Tim(String ime, List<Double> koeficijenti){
        this.ime = ime;
        koeficijent = koeficijenti.stream().reduce(0.00, Double::sum) / 5;
    }

    public double getKoeficijent() {
        return koeficijent;
    }

    public String getIme() {
        return ime;
    }

    public abstract double verovatnocaOsvajanja();

    @Override
    public String toString() {
        return ime + " " + String.format("%.2f", koeficijent) + " " + String.format("%.2f", verovatnocaOsvajanja());
    }
}

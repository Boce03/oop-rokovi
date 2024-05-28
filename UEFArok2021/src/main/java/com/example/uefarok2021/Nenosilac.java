package com.example.uefarok2021;

import java.util.List;

public class Nenosilac extends Tim{
    private double nacionalniKoeficijent;

    public Nenosilac(String ime, List<Double> koeficijenti, double nacionalniKoeficijent) {
        super(ime, koeficijenti);
        this.nacionalniKoeficijent = nacionalniKoeficijent;
    }



    @Override
    public double verovatnocaOsvajanja() {
        return getKoeficijent()*nacionalniKoeficijent / 100;
    }
}

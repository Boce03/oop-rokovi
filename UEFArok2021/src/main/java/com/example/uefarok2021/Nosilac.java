package com.example.uefarok2021;

import java.util.List;

public class Nosilac extends Tim implements Povlasceni{
    public Nosilac(String ime, List<Double> koeficijenti) {
        super(ime, koeficijenti);
    }

    @Override
    public double verovatnocaOsvajanja() {
        return  getKoeficijent()*30 / 100;
    }
}

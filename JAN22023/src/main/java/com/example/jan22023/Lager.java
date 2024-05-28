package com.example.jan22023;

public class Lager extends Pivo{
    public Lager(String zemljaPorekla, String naziv, double abv) {
        super(zemljaPorekla, naziv, abv);
    }

    @Override
    public double cena(double kolicina) {
        return 200*kolicina + 10*getAbv();
    }
}

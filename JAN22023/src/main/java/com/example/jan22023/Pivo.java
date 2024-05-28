package com.example.jan22023;

public abstract class Pivo implements Comparable<Pivo>{
    private String zemljaPorekla;
    private String naziv;
    private double abv;

    public Pivo(String zemljaPorekla, String naziv, double abv) {
        this.zemljaPorekla = zemljaPorekla;
        this.naziv = naziv;
        this.abv = abv;
    }

    public String getZemljaPorekla() {
        return zemljaPorekla;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getAbv() {
        return abv;
    }

    public abstract double cena(double kolicina);

    @Override
    public String toString() {
        return "(" + zemljaPorekla + ") " + naziv + " " + String.format("%.2f", abv) + "%";
    }

    @Override
    public int compareTo(Pivo p) {
        if(!zemljaPorekla.equals(p.zemljaPorekla)){
            return zemljaPorekla.compareTo(p.zemljaPorekla);
        }

        return Double.compare(abv, p.abv);
    }
}

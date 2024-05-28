package com.example.prodavnicajun2019;

public class Artikal implements Comparable<Artikal>{
    private int sifra;
    private String naziv;
    private double cena;
    private Akcija akcija;

    public Artikal(int sifra, String naziv, double cena, Akcija akcija) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.cena = cena;
        this.akcija = akcija;
    }

    public Artikal(int sifra, String naziv, double cena) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return sifra + " " + naziv + " " + cena;
    }

    public boolean naAkciji(){
        return akcija != null;
    }

    public Akcija getAkcija() {
        return akcija;
    }

    public double getCena() {
        return cena;
    }

    public int getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public int compareTo(Artikal o) {
        if(this.akcija != null && o.akcija == null)
            return -1;

        if(this.akcija == null && o.akcija != null)
            return 1;

        if(this.akcija != null && o.akcija != null){
            String[] datum1 = this.akcija.getDatumIsteka().split("/");
            String[] datum2 = o.akcija.getDatumIsteka().split("/");

            if(datum1[1].equals(datum2[1])){
                return (datum1[0].equals(datum2[0]))? o.naziv.compareTo(this.naziv) : datum1[0].compareTo(datum2[0]);
            } else{
                return datum1[1].compareTo(datum2[1]);
            }
        }

        return o.naziv.compareTo(this.naziv);
    }
}

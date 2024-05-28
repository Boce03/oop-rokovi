package com.example.telefoni20212022;

public abstract class Usluga implements Comparable<Usluga>, Kopirajuci<Broj>{
    private String vreme;
    private Broj brojOd;
    private Broj brojKa;



    public Usluga(String vreme, Broj brojOd, Broj brojKa) {
        this.vreme = vreme;
        this.brojOd = kopiraj(brojOd);
        this.brojKa = kopiraj(brojKa);
    }

    @Override
    public String toString() {
        return vreme + "\t" + brojOd + " -> " + brojKa;
    }

    public String getVreme() {
        return vreme;
    }

    public Broj getBrojOd() {
        return brojOd;
    }

    public Broj getBrojKa() {
        return brojKa;
    }

    public abstract double cena();

    @Override
    public int compareTo(Usluga u) {
        return vreme.compareTo(u.vreme);
    }

    @Override
    public boolean mozeDaSeKopira() {
        return true;
    }

    @Override
    public Broj kopiraj(Broj broj) {
        return new Broj(broj);
    }
}

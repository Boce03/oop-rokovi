package com.example.smestaj22;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public abstract class Soba implements Comparator<Soba> {
    private int cena;
    private int brojSobe;
    private Map<Termin, Gost> raspored = new TreeMap<>();
    private static int brojac = 100;

    public Soba(int cena) {
        this.cena = cena;
        this.brojSobe = brojac;
        brojac++;
    }

    public static void setBrojac(int brojac) {
        Soba.brojac = brojac;
    }

    public int getCena() {
        return cena;
    }

    public int getBrojSobe() {
        return brojSobe;
    }

    public Map<Termin, Gost> getRaspored() {
        return raspored;
    }

    public abstract boolean smesti(Termin termin, Gost gost);

    @Override
    public String toString() {
        return "Soba " + brojSobe;
    }

    @Override
    public int compare(Soba o1, Soba o2) {
        return Integer.compare(o2.cena, o1.cena);
    }
}

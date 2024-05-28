package com.example.nastava2019;

import java.util.ArrayList;
import java.util.List;

public class OcenaKvaliteta {
    private Kvalitet kvalitet;
    private List<Integer> ocene;

    private boolean ocenjeno;

    public OcenaKvaliteta(Kvalitet kvalitet) {
        this.kvalitet = kvalitet;
        ocene = new ArrayList<>();
    }

    public boolean nijeOcenjeno(){
        return !ocenjeno;
    }

    public void dodeljenaOcena(){
        ocenjeno = true;
    }

    public Kvalitet getKvalitet() {
        return kvalitet;
    }

    public List<Integer> getOcene() {
        return ocene;
    }

    public void dodajOcenu(Integer ocena){
        ocene.add(ocena);
    }

    public double prosecnaOcena(){
        return (ocene.isEmpty())? 0 :
                (double)ocene.stream().reduce(0, Integer::sum) / ocene.size();
    }

    @Override
    public String toString() {
        return kvalitet + " : " + String.format("%.2f", prosecnaOcena());
    }

    public String sveOcene(){
        return kvalitet + " : " + ocene + " (" + String.format("%.2f", prosecnaOcena()) + ")";
    }
}

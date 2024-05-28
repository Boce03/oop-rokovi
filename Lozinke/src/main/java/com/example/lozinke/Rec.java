package com.example.lozinke;

public class Rec implements Comparable<Rec>{
    private String rec;

    public Rec(String rec) {
        this.rec = rec.toLowerCase();
    }

    public Rec dodajBrojNaKraj(int x){
        return new Rec(rec + x);
    }

    public Rec dodajBrojNaPocetak(int x){
        return new Rec(x + rec);
    }

    public String getRec() {
        return rec;
    }

    public int getDuzina(){
        return rec.length();
    }

    @Override
    public int compareTo(Rec o) {
        return rec.compareTo(o.rec);
    }

}

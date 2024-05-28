package com.example.smestaj22;

import java.util.*;

public class Hotel<T extends Soba>{
    private String naziv;
    List<T> sobe = new ArrayList<>();

    public Hotel(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void dodajSobu(T soba){
        sobe.add(soba);
    }

    Optional<T> smesti(Termin termin, Gost gost){
        for(T soba: sobe){
            if(soba.smesti(termin, gost))
                return Optional.of(soba);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("{" + naziv + "}");

        for(T soba: sobe){
            sj.add(soba + " " + soba.getCena() + "$");
            for(Map.Entry<Termin, Gost> mp: soba.getRaspored().entrySet()){
                sj.add("-> " + mp.getKey() + " " + mp.getValue());
            }
        }
        sj.add("-----------");

        return sj.toString();
    }

    public void sortirajSobe(){
        sobe.sort((s1, s2)->Integer.compare(s2.getCena(), s1.getCena()));
    }
}

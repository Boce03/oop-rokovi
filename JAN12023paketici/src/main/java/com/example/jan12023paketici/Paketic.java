package com.example.jan12023paketici;

import java.util.*;

public class Paketic {
    private Dete dete;
    private int budzet;
    private List<Poklon> pokloni = new LinkedList<>();

    public Paketic(Dete dete, int budzet) {
        this.dete = new Dete(dete);
        this.budzet = budzet;
    }

    public void napuniPaketic(List<UredjenPar<Poklon, Integer>> spisakPoklona){
        int minCena = spisakPoklona.getFirst().getPrvi().getCena();
        List<UredjenPar<Poklon, Integer>> prikladniPokloni = new ArrayList<>();
        Iterator<UredjenPar<Poklon, Integer>> it = spisakPoklona.iterator();
        Random random = new Random();

        while(it.hasNext()){
            var par = it.next();
            if(par.getPrvi().prikladanPoklon(dete.getPol())){
                prikladniPokloni.add(par);
            }

            minCena = Math.min(minCena, par.getPrvi().getCena());
        }

        while(budzet >= minCena){
            int indeks = random.nextInt(0, prikladniPokloni.size());

            if(budzet - prikladniPokloni.get(indeks).getPrvi().getCena() >= 0) {
                pokloni.add(prikladniPokloni.get(indeks).getPrvi());
                prikladniPokloni.get(indeks).setDrugi(prikladniPokloni.get(indeks).getDrugi() + 1);
                budzet -= prikladniPokloni.get(indeks).getPrvi().getCena();
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");

        sj.add("Paketic za dete: " + dete + "\n");

        for(Poklon poklon: pokloni){
            sj.add(poklon.toString());
        }

        return sj.toString();
    }
}

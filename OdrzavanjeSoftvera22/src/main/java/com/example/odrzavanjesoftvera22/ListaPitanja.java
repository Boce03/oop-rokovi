package com.example.odrzavanjesoftvera22;

import java.util.LinkedList;
import java.util.List;

public class ListaPitanja implements KolekcijaStavki<Pitanje>{
    private List<Pitanje> lista = new LinkedList<>();

    @Override
    public List<Pitanje> listaj() {
        return lista;
    }

    @Override
    public boolean dodaj(Pitanje stavka) {
        lista.add(stavka);
        return true;
    }

    @Override
    public void sortiraj() {
        lista.sort((p1, p2)->{
            if(!p1.isRazresena() && p2.isRazresena())
                return -1;
            else if(p1.isRazresena() && !p2.isRazresena()){
                return 1;
            }

            return 0;
        });
    }
}

package com.example.odrzavanjesoftvera22;

import java.util.*;
import java.util.stream.Collectors;

public class SkupBagova implements KolekcijaStavki<Bag>{

    Set<Bag> skupBagova = new TreeSet<>((b1, b2)->{
        if(!b1.isRazresena() && b2.isRazresena())
            return -1;

        if(b1.isRazresena() && !b2.isRazresena())
            return 1;

        return Integer.compare(b2.getOzbiljnost(), b1.getOzbiljnost());
    });

    @Override
    public List<Bag> listaj() {
        return skupBagova.stream().toList();
    }

    @Override
    public boolean dodaj(Bag stavka) {
        if(skupBagova.contains(stavka))
            return false;

        skupBagova.add(stavka);
        return true;
    }

    @Override
    public void sortiraj() {
    }
}

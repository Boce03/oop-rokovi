package com.example.lozinke;

import java.util.Optional;

public class RecnikAlgoritam extends Algoritam{
    private Recnik recnik;

    public RecnikAlgoritam(Okruzenje okruzenje, Recnik recnik) {
        super(okruzenje);
        this.recnik = recnik;
    }

    public Recnik getRecnik() {
        return recnik;
    }

    @Override
    public Optional<Rec> izvrsi() {
        for(Rec rec: recnik.getReci()){
            if(getOkruzenje().proveriLozinku(rec)){
                return Optional.of(rec);
            }
        }

        return Optional.empty();
    }
}

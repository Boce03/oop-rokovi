package com.example.lozinke;

import java.util.Optional;

public class RecnikSaBrojevimaAlgoritam extends RecnikAlgoritam{
    public RecnikSaBrojevimaAlgoritam(Okruzenje okruzenje, Recnik recnik) {
        super(okruzenje, recnik);
    }

    @Override
    public Optional<Rec> izvrsi() {
        for(Rec rec: getRecnik().getReci()){
            for(int i = 0; i <= 9; i++){
                Rec generisanaRec = rec.dodajBrojNaKraj(i);
                if(getOkruzenje().proveriLozinku(generisanaRec)){
                    return Optional.of(generisanaRec);
                }

                generisanaRec = rec.dodajBrojNaPocetak(i);
                if(getOkruzenje().proveriLozinku(generisanaRec)){
                    return Optional.of(generisanaRec);
                }
            }
        }

        return Optional.empty();
    }
}

package com.example.uefarok2021;

import java.util.List;

public class LigaSampiona extends UEFATakmicenje{

    public LigaSampiona(String imeTakmicenja, List<Tim> timovi, List<Grupa> grupe, int koeficijentTakmicenja) {
        super(imeTakmicenja, timovi, grupe, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        double prosecniKoef = 0.0;
        for(Tim tim: getTimovi()){
            prosecniKoef += tim.getKoeficijent();
        }

        return (getTimovi().isEmpty())? 0.0 : prosecniKoef / getTimovi().size();
    }
}

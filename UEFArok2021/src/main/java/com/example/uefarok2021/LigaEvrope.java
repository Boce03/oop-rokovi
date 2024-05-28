package com.example.uefarok2021;

import java.util.List;

public class LigaEvrope extends UEFATakmicenje{
    public LigaEvrope(String imeTakmicenja, List<Tim> timovi, List<Grupa> grupe, int koeficijentTakmicenja) {
        super(imeTakmicenja, timovi, grupe, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        double maks = getTimovi().getFirst().getKoeficijent();
        double min = getTimovi().getFirst().getKoeficijent();

        for(Tim tim: getTimovi()){
            if(tim.getKoeficijent() > maks){
                maks = tim.getKoeficijent();
            } else if(tim.getKoeficijent() < min){
                min = tim.getKoeficijent();
            }
        }

        return (getTimovi().isEmpty())? 0.0 : (maks + min)/2;
    }
}

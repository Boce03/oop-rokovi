package com.example.uefarok2021;

import java.util.ArrayList;
import java.util.List;

public class LigaKonferencije extends UEFATakmicenje{
    public LigaKonferencije(String imeTakmicenja, List<Tim> timovi, List<Grupa> grupe, int koeficijentTakmicenja) {
        super(imeTakmicenja, timovi, grupe, koeficijentTakmicenja);
    }

    @Override
    public double jacinaTakmicenja() {
        if(getTimovi().isEmpty()) return 0.0;

        List<Tim> pom = new ArrayList<>(getTimovi());
        pom.sort((t1, t2)-> Double.compare(t1.getKoeficijent(), t2.getKoeficijent()));

        for(int i = 0; i < 5; i++){
            pom.removeFirst();
            pom.removeLast();
        }

        double prosekKoef = 0.0;
        for(Tim tim: pom){
            prosekKoef += tim.getKoeficijent();
        }

        return prosekKoef/ pom.size();
    }
}

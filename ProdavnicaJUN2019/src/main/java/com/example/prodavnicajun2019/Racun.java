package com.example.prodavnicajun2019;

import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

public class Racun {
    private Map<Artikal, Integer> stavke = new TreeMap<>();

    public void dodajStavku(Artikal artikal, int komada){
        if(stavke.containsKey(artikal)){
            int staraVr = stavke.get(artikal);
            stavke.put(artikal, staraVr + komada);
        } else{
            stavke.put(artikal, komada);
        }
    }

    public Map<Artikal, Integer> getStavke() {
        return stavke;
    }

    public Integer ukloniStavku(Artikal artikal){
        return stavke.remove(artikal);
    }

    private double cenaPoKomadu(Artikal artikal, int otkucanoKomada){
        return (artikal.naAkciji())?
                artikal.getAkcija().cenaPoKomadu(artikal.getCena(), otkucanoKomada) : artikal.getCena();
    }

    public String stampajStavku(Artikal artikal, int otkucanoKomada){
        double novaCena = cenaPoKomadu(artikal, otkucanoKomada);

        return artikal.getNaziv() + " " + otkucanoKomada + " x " + novaCena + " = " + otkucanoKomada * novaCena;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        double ukupno = 0.0;
        for(Map.Entry<Artikal, Integer> stavka: stavke.entrySet()){
            sj.add(stampajStavku(stavka.getKey(), stavka.getValue()));
            ukupno += cenaPoKomadu(stavka.getKey(), stavka.getValue()) * stavka.getValue();
        }

        sj.add("Ukupno: " + ukupno);

        return sj.toString();
    }
}

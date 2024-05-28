package com.example.telefoni20212022;

import java.util.StringJoiner;

public class Poziv extends Usluga{
    private int trajanjeS;

    public Poziv(String vreme, Broj brojOd, Broj brojKa, int trajanjeS) {
        super(vreme, brojOd, brojKa);
        this.trajanjeS = trajanjeS;
    }

    private String formatTrajanjaPoziva(){
        int brSati = trajanjeS / 360;
        int preostalo = trajanjeS % 360;
        int brMinuta = trajanjeS / 60;
        preostalo = trajanjeS % 60;

        StringJoiner sj = new StringJoiner(":");

        if(brSati > 9){
            sj.add(brSati + "");
        } else {
            sj.add("0" + brSati);
        }

        if(brMinuta > 9){
            sj.add(brMinuta + "");
        } else {
            sj.add("0" + brMinuta);
        }

        if(preostalo > 9){
            sj.add(preostalo + "");
        } else {
            sj.add("0" + preostalo);
        }

        return sj.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "\t\t" + formatTrajanjaPoziva() + "\t\t\t" + String.format("%.2f", cena()) + "din";
    }

    @Override
    public double cena() {
        if(trajanjeS == 0) return 0.0;

        double uspostavljanjeVeze;
        double tarifaPoMinutu;

        if(!getBrojOd().istaDrzava(getBrojKa())){
            uspostavljanjeVeze = 30;
            tarifaPoMinutu = 50;
        } else if(getBrojOd().isFiksniTelefon() && getBrojKa().isFiksniTelefon()){
            uspostavljanjeVeze = 0;
            tarifaPoMinutu = 8;
        } else if(!getBrojOd().isFiksniTelefon() && !getBrojKa().isFiksniTelefon()){
            uspostavljanjeVeze = 0;
            tarifaPoMinutu = 12;
        } else{
            uspostavljanjeVeze = 5;
            tarifaPoMinutu = 10;
        }

        int brMinuta = trajanjeS / 60 + ((trajanjeS % 60 != 0)? 1 : 0);
        return brMinuta * tarifaPoMinutu + uspostavljanjeVeze;
    }
}

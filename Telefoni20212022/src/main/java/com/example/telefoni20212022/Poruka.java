package com.example.telefoni20212022;

public class Poruka extends Usluga{
    private String tekst;
    private boolean poslata;

    public Poruka(String vreme, Broj brojOd, Broj brojKa, String tekst, boolean poslata) {
        super(vreme, brojOd, brojKa);
        this.tekst = tekst;
        this.poslata = poslata;
    }

    @Override
    public double cena() {
        if(!poslata)
            return 0;

        return (getBrojOd().istaDrzava(getBrojKa()))? 3 : 20;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\t" + ((poslata)? "POSLATO\t" : "NIJE POSLATO") + "\t\t" + String.format("%.2f", cena()) + "din";
    }
}

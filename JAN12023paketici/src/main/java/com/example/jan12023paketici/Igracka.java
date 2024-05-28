package com.example.jan12023paketici;

public class Igracka extends Poklon{
    private VrstaIgracke vrsta;

    public Igracka(String naziv, String ID, int cena, VrstaIgracke vrsta) {
        super(naziv, ID, cena);
        this.vrsta = vrsta;
    }

    @Override
    public boolean prikladanPoklon(PolDeteta polDeteta) {
        if(vrsta.equals(VrstaIgracke.NEUTRALNI))
            return true;

        return vrsta.getSkracenica() == polDeteta.getSkracenica();
    }
}

package com.example.sportkseigre;

public class CelosezonskiSport extends Sport{

    private int ulaganjeOprema;
    private int ulaganjeTreneri;

    public CelosezonskiSport(String naziv, boolean individualni, int ulaganjeOprema, int ulaganjeTreneri) {
        super(naziv, individualni);
        this.ulaganjeOprema = ulaganjeOprema;
        this.ulaganjeTreneri = ulaganjeTreneri;
    }

    @Override
    public int ulaganje() {
        return ulaganjeOprema + ulaganjeTreneri;
    }

    @Override
    public String toString() {
        return "naziv: " + getNaziv() + " tip: " + (isIndividualni()? "individualni" : "grupni") +
                " godisnje ulaganje: " + ulaganje();
    }
}

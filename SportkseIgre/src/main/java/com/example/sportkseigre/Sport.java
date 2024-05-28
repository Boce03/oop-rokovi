package com.example.sportkseigre;

public abstract class Sport implements Takmicenje{
    private String naziv;
    private boolean individualni;

    public Sport(String naziv, boolean individualni) {
        this.naziv = naziv;
        this.individualni = individualni;
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean isIndividualni() {
        return individualni;
    }

    public abstract int ulaganje();
}

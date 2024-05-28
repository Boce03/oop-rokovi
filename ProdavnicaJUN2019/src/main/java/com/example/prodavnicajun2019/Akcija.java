package com.example.prodavnicajun2019;

public abstract class Akcija {
    private String datumIsteka;

    public Akcija(String datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public String getDatumIsteka() {
        return datumIsteka;
    }

    public abstract double cenaPoKomadu(double cena, int otkucanoKomada);
}

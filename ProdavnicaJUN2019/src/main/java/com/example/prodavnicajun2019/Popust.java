package com.example.prodavnicajun2019;

public class Popust extends Akcija{

    private int procenat;

    public Popust(String datumIsteka, int procenat) {
        super(datumIsteka);
        this.procenat = procenat;
    }

    @Override
    public double cenaPoKomadu(double cena, int otkucanoKomada) {
        return (100.0 - procenat)/100 * cena;
    }

    @Override
    public String toString() {
        return "snizeno " + procenat + "% do " + getDatumIsteka();
    }
}

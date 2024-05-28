package com.example.prodavnicajun2019;

public class Gratis extends Akcija{
    private int potrebnoKomada;
    private int gratisKomada;

    public Gratis(String datumIsteka, int potrebnoKomada, int gratisKomada) {
        super(datumIsteka);
        this.potrebnoKomada = potrebnoKomada;
        this.gratisKomada = gratisKomada;
    }

    @Override
    public double cenaPoKomadu(double cena, int otkucanoKomada) {
        double ukCena = 0.0;
        int ukupnoKomada = potrebnoKomada + gratisKomada;

        while(otkucanoKomada > potrebnoKomada){
            otkucanoKomada -= ukupnoKomada;
            ukCena += potrebnoKomada * cena;
        }

        ukCena += (otkucanoKomada <= 0)? 0 : (otkucanoKomada * cena);

        return ukCena/otkucanoKomada;
    }

    @Override
    public String toString() {
        return "na akciji " + (gratisKomada + potrebnoKomada) + " za " + potrebnoKomada + " do " + getDatumIsteka();
    }
}

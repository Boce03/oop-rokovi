package com.example.odrzavanjesoftvera22;

public class Pitanje extends Stavka{
    private String odgovor;

    public Pitanje(String korisnickoIme, String naslov, String sadrzaj) {
        super(korisnickoIme, naslov, sadrzaj);
    }

    public void razresi(String odgovor){
        super.razresi();
        this.odgovor = odgovor;
    }

    public String getOdgovor() {
        if(odgovor != null)
            return odgovor;
        else return "Pitanje nema odgovor";
    }

    @Override
    public String toString() {
        return "(?) " + super.toString() + ((odgovor != null)? ("\n- " + odgovor) : "");
    }
}

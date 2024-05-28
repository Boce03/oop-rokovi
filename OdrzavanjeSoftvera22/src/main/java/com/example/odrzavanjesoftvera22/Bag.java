package com.example.odrzavanjesoftvera22;

public class Bag extends Stavka{
    private int id;
    private int ozbiljnost;
    private String zaduzen;

    public Bag(String korisnickoIme, String naslov, String sadrzaj, int id, int ozbiljnost, String zaduzen) {
        super(korisnickoIme, naslov, sadrzaj);
        this.id = id;
        this.ozbiljnost = ozbiljnost;
        zaduzi(zaduzen);
    }

    public Bag(String korisnickoIme, String naslov, String sadrzaj, int id, int ozbiljnost) {
        super(korisnickoIme, naslov, sadrzaj);
        this.id = id;
        this.ozbiljnost = ozbiljnost;
    }

    public int getId() {
        return id;
    }

    public int getOzbiljnost() {
        return ozbiljnost;
    }

    public String getZaduzen() {
        return zaduzen;
    }

    public void zaduzi(String zaduzen) {
        razresi();
        this.zaduzen = zaduzen;
    }

    public String zaduzen() {
        return (zaduzen != null)? zaduzen : "Bag nije dodeljen nijednom programeru";
    }

    @Override
    public String toString() {
        return "(! " + id + " " + ozbiljnost + " " + ((zaduzen != null)? zaduzen : "") + ") " + super.toString();
    }
}

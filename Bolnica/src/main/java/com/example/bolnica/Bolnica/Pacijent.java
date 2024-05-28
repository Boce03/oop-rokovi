package com.example.bolnica.Bolnica;

public class Pacijent implements Izleciv{
    private String ime, prezime;
    private int idKnjizica;
    private int duzinaLecenja;
    private boolean zarazen;
    private ZaraznaBolest bolest;

    public Pacijent(String ime, String prezime, int idKnjizica, ZaraznaBolest bolest) {
        this.ime = ime;
        this.prezime = prezime;
        this.idKnjizica = idKnjizica;
        this.bolest = bolest.klon();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getIdKnjizica() {
        return idKnjizica;
    }

    public int getDuzinaLecenja() {
        return duzinaLecenja;
    }

    public boolean isZarazen() {
        return zarazen;
    }

    public ZaraznaBolest getBolest() {
        return bolest;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setIdKnjizica(int idKnjizica) {
        this.idKnjizica = idKnjizica;
    }

    public void setDuzinaLecenja(int duzinaLecenja) {
        this.duzinaLecenja = duzinaLecenja;
    }

    public void setZarazen(boolean zarazen) {
        this.zarazen = zarazen;
    }

    public void setBolest(ZaraznaBolest bolest) {
        this.bolest = bolest.klon();
    }

    @Override
    public void leci(int brojDana) {
        if(brojDana < 0){
            throw new RuntimeException("brojDana ne sme biti manji od nule !!!");
        }

        duzinaLecenja += brojDana;
    }

    @Override
    public boolean izlecen() {
        return duzinaLecenja >= bolest.getDuzinaBolesti();
    }

    @Override
    public String toString() {
        if(duzinaLecenja == 0 && bolest.getDuzinaBolesti() == 0){
            return "id: " + idKnjizica + " " + ime + " " + prezime;
        } else{
            return "id: " + idKnjizica + " " + ime + " " + prezime + "\n " + bolest
                    + " Vreme do izlecenja: " + (bolest.getDuzinaBolesti() - duzinaLecenja);
        }
    }
}

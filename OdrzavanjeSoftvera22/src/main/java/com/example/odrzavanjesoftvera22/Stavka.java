package com.example.odrzavanjesoftvera22;

public abstract class Stavka {
    private String korisnickoIme;
    private String naslov;
    private String sadrzaj;
    private Labela labela;
    private boolean razresena;

    public Stavka(String korisnickoIme, String naslov, String sadrzaj) {
        this.korisnickoIme = korisnickoIme;
        this.naslov = naslov;
        this.sadrzaj = sadrzaj;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public Labela getLabela() {
        return labela;
    }

    public boolean isRazresena() {
        return razresena;
    }

    public void razresi(){
        razresena = true;
    }

    public void labeliraj(Labela labela){
        this.labela = labela;
    }

    @Override
    public String toString() {
        return "[" + ((razresena)? "x" : "") + "] " + ((labela != null)? ("(" + labela + ")") : "") + " "
                + korisnickoIme + ": " + naslov + "\n" + sadrzaj;
    }
}

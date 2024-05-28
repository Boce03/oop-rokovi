package com.example.telefoni20212022;

public class Broj implements Comparable<Broj>, Kopirajuci<Broj>{
    private String kodDrzave;
    private String pozivniBroj;
    private String brojTelefona;
    private boolean fiksniTelefon;

    public Broj(String ulaz){
        kodDrzave = ulaz.substring(1, 4);
        pozivniBroj = ulaz.substring(5, 7);
        brojTelefona = ulaz.substring(8);
        fiksniTelefon = pozivniBroj.charAt(0) != '6';
    }

    public Broj(Broj broj) {
        this(broj.toString());
    }

    public boolean istaDrzava(Broj b){
        return kodDrzave.equals(b.kodDrzave);
    }

    public boolean isFiksniTelefon() {
        return fiksniTelefon;
    }

    @Override
    public String toString() {
        return "+" + kodDrzave + " " + pozivniBroj + " " + brojTelefona;
    }


    @Override
    public int compareTo(Broj b) {
        return this.toString().compareTo(b.toString());
    }

    @Override
    public boolean mozeDaSeKopira() {
        return false;
    }

    @Override
    public Broj kopiraj(Broj broj) {
        return null;
    }
}

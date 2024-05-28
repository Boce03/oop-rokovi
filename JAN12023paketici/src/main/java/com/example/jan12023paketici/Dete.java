package com.example.jan12023paketici;

public class Dete {
    private String imePrezime;
    private PolDeteta pol;

    public Dete(String imePrezime, PolDeteta pol) {
        this.imePrezime = imePrezime;
        this.pol = pol;
    }

    public Dete(Dete d) {
        this(d.imePrezime, d.pol);
    }

    public PolDeteta getPol() {
        return pol;
    }

    @Override
    public String toString() {
        return imePrezime + " (" + pol.getSkracenica() + ")";
    }
}

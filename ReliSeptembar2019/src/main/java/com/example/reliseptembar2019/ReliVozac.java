package com.example.reliseptembar2019;

public class ReliVozac {
    private String ime;
    private ReliAuto auto;

    public ReliVozac(String ime, ReliAuto auto) {
        this.ime = ime;
        this.auto = auto;
    }

    public ReliAuto getAuto() {
        return auto;
    }

    @Override
    public String toString() {
        return ime + " - " + auto;
    }
}

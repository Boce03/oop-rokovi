package com.example.sportkseigre;

public class ESport implements Takmicenje{
    private Igrica igrica;
    private int brojIgraca;

    public ESport(Igrica igrica, int brojIgraca) {
        this.igrica = igrica;
        this.brojIgraca = brojIgraca;
    }

    public double popularnost(){
        return (double)brojIgraca / (2021 - igrica.getGodinaIzlaska());
    }

    @Override
    public String toString() {
        return "naziv: " + igrica.getNaziv() + " poplularnost: " + popularnost();
    }
}

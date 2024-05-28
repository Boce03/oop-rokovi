package com.example.reliseptembar2019;

public abstract class ReliAuto {
    private String model;
    private Pogon tipPogona;
    private int godiste;

    public ReliAuto(String model, Pogon tipPogona, int godiste) {
        this.model = model;
        this.tipPogona = tipPogona;
        this.godiste = godiste;
    }

    public String getModel() {
        return model;
    }

    public Pogon getTipPogona() {
        return tipPogona;
    }

    public int getGodiste() {
        return godiste;
    }
}

package com.example.reliseptembar2019;

public class GrupaBReliAuto extends ReliAuto{
    private boolean superCharger;

    public GrupaBReliAuto(String model, Pogon tipPogona, int godiste, boolean superCharger) {
        super(model, tipPogona, godiste);
        this.superCharger = superCharger;
    }

    public GrupaBReliAuto(String model, Pogon tipPogona, int godiste) {
        super(model, tipPogona, godiste);
    }

    @Override
    public String toString() {
        return "Grupa B: " + getModel() + " (" + getGodiste() + ") | " + getTipPogona()
                + ((superCharger)? " [S]" : "");
    }
}

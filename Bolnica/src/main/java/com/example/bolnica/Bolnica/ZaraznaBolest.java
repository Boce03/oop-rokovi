package com.example.bolnica.Bolnica;

public abstract class ZaraznaBolest {
    private int duzinaBolesti;

    public ZaraznaBolest(int duzinaBolesti) {
        if(duzinaBolesti >= 0)
            this.duzinaBolesti = duzinaBolesti;
        else throw new RuntimeException("duzinaBolesti mora biti veca ili jednaka 0!!!");
    }

    public int getDuzinaBolesti() {
        return duzinaBolesti;
    }

    public void setDuzinaBolesti(int duzinaBolesti) {
        if(duzinaBolesti >= 0)
            this.duzinaBolesti = duzinaBolesti;
        else throw new RuntimeException("duzinaBolesti mora biti veca ili jednaka 0!!!");
    }

    public abstract ZaraznaBolest klon();
}

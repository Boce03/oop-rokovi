package com.example.bolnica.Bolnica;

public class Grip extends ZaraznaBolest{
    public Grip(int duzinaBolesti) {
        super(duzinaBolesti);
    }

    @Override
    public ZaraznaBolest klon() {
        return new Grip(this);
    }

    public Grip(Grip g){
        super(g.getDuzinaBolesti());
    }

    @Override
    public String toString() {
        return "boluje od: Grip traje: " + getDuzinaBolesti();
    }

}

package com.example.bolnica.Bolnica;

import java.util.Random;

public class Korona extends ZaraznaBolest{
    private boolean pokazujeSimptome;

    public Korona(int duzinaBolesti, boolean pokazujeSimptome) {
        super(duzinaBolesti);
        this.pokazujeSimptome = pokazujeSimptome;
    }

    public Korona(Korona k){
        super(k.getDuzinaBolesti());
        pokazujeSimptome = k.pokazujeSimptome;
    }

    public boolean isPokazujeSimptome() {
        return pokazujeSimptome;
    }

    public boolean test(){
        Random r = new Random();
        double b = r.nextDouble(0, 1);
        if(pokazujeSimptome){
            return Double.compare(b, 0.8) <= 0;
        } else{
            return Double.compare(b, 0.4) <= 0;
        }
    }

    @Override
    public String toString() {
        if(pokazujeSimptome){
            return "boluje od: Korona traje: " + getDuzinaBolesti() + " dana sa simptomima";
        } else{
            return "boluje od: Korona traje: " + getDuzinaBolesti() + " dana bez simptomima";
        }
    }

    @Override
    public ZaraznaBolest klon() {
        return new Korona(this);
    }
}

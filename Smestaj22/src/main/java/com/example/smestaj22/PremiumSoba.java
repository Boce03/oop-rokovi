package com.example.smestaj22;

import java.util.Random;

public class PremiumSoba extends Soba{
    private double ocena;
    private static Random random = new Random();
    public PremiumSoba(int cena, double ocena) {
        super(cena);
        this.ocena = ocena;
    }

    @Override
    public boolean smesti(Termin termin, Gost gost) {
        for(Termin t: getRaspored().keySet()){
            if(t.preklapaSeSa(termin))
                return false;
        }

        if(!gost.isPremium() && (Double.compare(gost.getOcena(), ocena) <= 0 || gost.getBudzet() <= getCena()))
            return false;

        int popust = random.nextInt(1, 6);
        int novaCena = getCena() * (1 - popust/10);

        if(gost.isPremium() && gost.getBudzet() <= novaCena)
            return false;

        getRaspored().put(termin, gost);
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + ocena + ")";
    }
}

package com.example.smestaj22;

public class ObicnaSoba extends Soba{
    public ObicnaSoba(int cena) {
        super(cena);
    }

    @Override
    public boolean smesti(Termin termin, Gost gost) {
        for(Termin t: getRaspored().keySet()){
            if(t.preklapaSeSa(termin))
                return false;
        }

        if(gost.getBudzet() <= getCena())
            return false;

        getRaspored().put(termin, gost);
        return true;
    }
}

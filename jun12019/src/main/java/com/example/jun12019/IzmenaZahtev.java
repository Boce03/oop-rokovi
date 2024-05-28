package com.example.jun12019;

public class IzmenaZahtev extends Izmena{
    public IzmenaZahtev(Zaglavlje zaglavlje, String poruka, int id) {
        super(zaglavlje, poruka, id);
    }

    public IzmenaZahtev(Zaglavlje zaglavlje, String poruka) {
        super(zaglavlje, poruka);
    }

    @Override
    public String toString() {
        return "[iz]" + getZaglavlje() + " #" + getId() + "\n" + getPoruka();
    }

    @Override
    public String serijalizuj() {
        return "iz, " + getZaglavlje().getAutor() + ", "
                + getId() + ", " + getZaglavlje().getVremenskaOznaka() + ", "
                + getPoruka();
    }
}

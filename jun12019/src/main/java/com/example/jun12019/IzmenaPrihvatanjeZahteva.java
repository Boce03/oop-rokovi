package com.example.jun12019;

public class IzmenaPrihvatanjeZahteva extends Izmena{
    private int idPrihvacenogZahteva;

    public IzmenaPrihvatanjeZahteva(Zaglavlje zaglavlje, String poruka, int id, int idPrihvacenogZahteva) {
        super(zaglavlje, poruka, id);
        this.idPrihvacenogZahteva = idPrihvacenogZahteva;
    }

    public IzmenaPrihvatanjeZahteva(Zaglavlje zaglavlje, String poruka, int idPrihvacenogZahteva) {
        super(zaglavlje, poruka);
        this.idPrihvacenogZahteva = idPrihvacenogZahteva;
    }

    @Override
    public String toString() {
        return "[ipz]" + getZaglavlje() + " #" + getId() + " za " + idPrihvacenogZahteva
                + "\n" + getPoruka();
    }

    @Override
    public String serijalizuj() {
        return "ipz, " + getZaglavlje().getAutor() + ", " + getId() + ", "
                + getZaglavlje().getVremenskaOznaka() + ", " + getPoruka() + ", "
                + idPrihvacenogZahteva;
    }
}

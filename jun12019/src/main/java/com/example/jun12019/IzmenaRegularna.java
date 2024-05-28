package com.example.jun12019;

public class IzmenaRegularna extends Izmena{

    private TipRegularneIzmene tipIzmene;

    public IzmenaRegularna(Zaglavlje zaglavlje, String poruka, int id, TipRegularneIzmene tipIzmene) {
        super(zaglavlje, poruka, id);
        this.tipIzmene = tipIzmene;
    }

    public IzmenaRegularna(Zaglavlje zaglavlje, String poruka, TipRegularneIzmene tipIzmene) {
        super(zaglavlje, poruka);
        this.tipIzmene = tipIzmene;
    }

    @Override
    public String toString() {
        return "[ir] "
                + getZaglavlje().getAutor() + " "
                + getZaglavlje().getVremenskaOznaka() + " #"  + getId() + " "
                + tipIzmene + "\n" + getPoruka();
    }

    @Override
    public String serijalizuj() {
        return "ir, " + getZaglavlje().getAutor() + ", "
                + getId() + ", " + getZaglavlje().getVremenskaOznaka() + ", "
                + getPoruka() + ", " + TipRegularneIzmene.uBroj(tipIzmene);
    }
}

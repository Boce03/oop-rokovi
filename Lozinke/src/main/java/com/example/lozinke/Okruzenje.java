package com.example.lozinke;

import javafx.scene.control.TextArea;

public class Okruzenje {
    private Rec lozinka;
    private TextArea log;

    public Okruzenje(Rec lozinka, TextArea log) {
        this.lozinka = lozinka;
        this.log = log;
    }

    private void prijaviPokusaj(Rec rec){
        log.appendText("Pokusavam rec \"" + rec.getRec() + "\"\n");
    }

    public boolean proveriLozinku(Rec rec){
        prijaviPokusaj(rec);
        return lozinka.getRec().equals(rec.getRec());
    }

    public Rec getLozinka() {
        return lozinka;
    }
}

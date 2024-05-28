package com.example.lozinke;

import java.util.Optional;

public abstract class Algoritam {
    private Okruzenje okruzenje;

    public Algoritam(Okruzenje okruzenje) {
        this.okruzenje = okruzenje;
    }

    public Okruzenje getOkruzenje() {
        return okruzenje;
    }

    public abstract Optional<Rec> izvrsi();
}

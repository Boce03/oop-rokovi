package com.example.odrzavanjesoftvera22;

import java.util.List;

public interface KolekcijaStavki <T extends Stavka>{
    List<T> listaj();
    boolean dodaj(T stavka);
    void sortiraj();
}

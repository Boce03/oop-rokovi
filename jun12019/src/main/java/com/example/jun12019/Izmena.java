package com.example.jun12019;

import java.util.HashSet;
import java.util.Set;

public abstract class Izmena{
    private Zaglavlje zaglavlje;
    private String poruka;
    private int id;
    private static int sledeciSlobodan = 1;
    private static Set<Integer> s = new HashSet<>();

    public Izmena(Zaglavlje zaglavlje, String poruka, int id) {
        this.zaglavlje = new Zaglavlje(zaglavlje);
        this.poruka = poruka;
        this.id = id;
        s.add(id);
    }

    public Izmena(Zaglavlje zaglavlje, String poruka){
        this.zaglavlje = new Zaglavlje(zaglavlje);
        this.poruka = poruka;

        while(s.contains(sledeciSlobodan)){
            sledeciSlobodan++;
        }
        id = sledeciSlobodan++;
    }

    public static void postaviSledeciId(int sledeci){
        sledeciSlobodan = sledeci;
    }

    public Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    public String getPoruka() {
        return poruka;
    }

    public int getId() {
        return id;
    }

    public abstract String serijalizuj();
}

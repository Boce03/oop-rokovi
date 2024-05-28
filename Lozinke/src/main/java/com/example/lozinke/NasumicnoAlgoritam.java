package com.example.lozinke;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class NasumicnoAlgoritam extends Algoritam{
    private int maskBrojPokusaja;

    private Set<Rec> isprobaneReci = new HashSet<>();

    private static final String karakteri = "abcdefghijklmnopqristuvwxyz0123456789";
    private static Random random = new Random();

    public NasumicnoAlgoritam(Okruzenje okruzenje, int maskBrojPokusaja) {
        super(okruzenje);
        this.maskBrojPokusaja = maskBrojPokusaja;
    }

    public NasumicnoAlgoritam(Okruzenje okruzenje) {
        super(okruzenje);
        maskBrojPokusaja = 10;
    }

    public int getMaskBrojPokusaja() {
        return maskBrojPokusaja;
    }

    @Override
    public Optional<Rec> izvrsi() {

        Rec generisanaRec = null;
        for(int j = 0; j < maskBrojPokusaja; j++) {
            int duzina = getOkruzenje().getLozinka().getDuzina();
            char[] pokusaj = new char[duzina];

            do {
                for (int i = 0; i < duzina; i++) {
                    pokusaj[i] = karakteri.charAt(random.nextInt(0, karakteri.length()));
                }

                generisanaRec = new Rec(String.valueOf(pokusaj));
            } while (isprobaneReci.contains(generisanaRec));

            isprobaneReci.add(generisanaRec);
            maskBrojPokusaja--;

            if(getOkruzenje().proveriLozinku(generisanaRec)){
                return Optional.of(generisanaRec);
            }
        }

        return Optional.empty();
    }
}

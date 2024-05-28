package com.example.jan12023paketici;

import java.util.NoSuchElementException;

public enum VrstaIgracke {
    MUSKI('M'), ZENSKI('Z'), NEUTRALNI('N');

    private char skracenica;

    VrstaIgracke(char skracenica) {
        this.skracenica = skracenica;
    }

    public char getSkracenica() {
        return skracenica;
    }

    public static VrstaIgracke odSkracenice(char skracenica){
        return switch (skracenica){
            case 'M' -> MUSKI;
            case 'Z' -> ZENSKI;
            case 'N' -> NEUTRALNI;
            default -> throw new NoSuchElementException();
        };
    }
}

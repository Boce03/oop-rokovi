package com.example.jan12023paketici;

import java.util.NoSuchElementException;

public enum PolDeteta {
    MUSKI('M'), ZENSKI('Z');

    private char skracenica;

    PolDeteta(char skracenica) {
        this.skracenica = skracenica;
    }

    public char getSkracenica() {
        return skracenica;
    }

    public static PolDeteta odSkracenice(char skracenica){
        return switch (skracenica){
          case 'M' -> MUSKI;
          case 'Z' -> ZENSKI;
          default -> throw new NoSuchElementException();
        };
    }
}

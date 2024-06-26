package com.example.jun22019;

import java.util.NoSuchElementException;

public enum Kategorija {
    A1, P2, P3, P13;

    @Override
    public String toString() {
        switch (this){
            case A1: return "zaposleni";
            case P2: return "srednjoskolci";
            case P3: return "studenti";
            case P13: return "penzioneri";
            default: return ""; //ovaj slucaj se ne desava
        }
    }

    public static Kategorija izBroja(int i){
        switch (i){
            case 0: return A1;
            case 1: return P2;
            case 2: return P3;
            case 3: return P13;
            default: throw new NoSuchElementException("nepostojeca kategorija!");
        }
    }
}

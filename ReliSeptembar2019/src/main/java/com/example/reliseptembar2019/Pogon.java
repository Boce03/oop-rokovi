package com.example.reliseptembar2019;

public enum Pogon {
    Prednji, Zadnji, SvaCetiri;

    @Override
    public String toString() {
        return switch (this) {
            case Prednji -> "FWD";
            case Zadnji -> "RWD";
            case SvaCetiri -> "4WD";
        };
    }

    public static Pogon odNaziva(String naziv){
        return switch (naziv){
            case "FWD" -> Prednji;
            case "RWD" -> Zadnji;
            case "4WD" -> SvaCetiri;
            default -> throw new IllegalStateException();
        };
    }
}

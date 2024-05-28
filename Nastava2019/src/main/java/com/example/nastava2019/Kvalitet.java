package com.example.nastava2019;

public enum Kvalitet {
    KORISNO(0), INTERESANTNO(1), RAZUMLJIVO(2);

    private int rbr;

    Kvalitet(int rbr) {
        this.rbr = rbr;
    }

    public int getRbr() {
        return rbr;
    }

    Kvalitet() {
    }

    public static Kvalitet izBroja(int rbr){
        return switch (rbr){
            case 0 -> KORISNO;
            case 1 -> INTERESANTNO;
            case 2 -> RAZUMLJIVO;
            default -> RAZUMLJIVO;
        };
    }
}

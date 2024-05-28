package com.example.jan22023;

public class Bure<T extends Pivo> implements Comparable<Bure>{
    private T pivo;
    private double kolicina;

    public Bure(T pivo, double kolicina) {
        this.pivo = pivo;
        this.kolicina = kolicina;
    }

    public boolean dovoljnoZaTocenje(double kolicina){
        return this.kolicina >= (kolicina + 0.5);
    }

    public T getPivo() {
        return pivo;
    }

    public double getKolicina() {
        return kolicina;
    }

    public double natoci(double kolicina){
        if(dovoljnoZaTocenje(kolicina)){
            this.kolicina -= kolicina;
            return pivo.cena(kolicina);
        } else{
            throw new UnsupportedOperationException("nema dovoljno kolicine!");
        }
    }

    @Override
    public String toString() {
        return String.format("%.1f", kolicina) + "L " + pivo;
    }

    @Override
    public int compareTo(Bure b) {
        return pivo.compareTo(b.pivo);
    }
}

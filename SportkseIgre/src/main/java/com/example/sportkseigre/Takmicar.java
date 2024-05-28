package com.example.sportkseigre;

public class Takmicar <T extends Takmicenje> implements Comparable<Takmicar>{
    private String ime;
    private String prezime;
    private int uspesnost;
    private T sport;
    private double osvojenaNagrada;

    public Takmicar(String ime, String prezime, int uspesnost, T sport, double osvojenaNagrada) {
        this.ime = ime;
        this.prezime = prezime;
        this.uspesnost = uspesnost;
        this.sport = sport;
        this.osvojenaNagrada = osvojenaNagrada;
    }

    public double nagrada(int ukupnaNagrada){
        if(sport instanceof ESport s1){
            return ukupnaNagrada;
        }

        Sport s2 = (Sport) sport;
        return (s2.isIndividualni())? ukupnaNagrada : 0.1*ukupnaNagrada;
    }

    @Override
    public String toString() {
        return "Takmicar: " + ime + " " + prezime + " " + uspesnost + "\nVrsta sporta: "
                + ((sport instanceof ESport)? "Esport" : "Sport") + "\n Dodatan opis sporta: " + sport
                + "\n Poslednja osvojena nagrada: " + osvojenaNagrada;
    }


    @Override
    public int compareTo(Takmicar t) {
        return Integer.compare(t.uspesnost, uspesnost);
    }
}

package com.example.uefarok2021;

public class TezinaZreba{
    private Grupa grupa;

    public TezinaZreba(Grupa grupa) {
        this.grupa = grupa;
    }

    public double tezinaZreba(){
        double ukupniKoefNepovlascenih = 0.0;
        int brNepovlascenih = 0;

        for(Tim tim: grupa.getTimovi()){
            if(!(tim instanceof Povlasceni)){
                ukupniKoefNepovlascenih += tim.getKoeficijent();
                brNepovlascenih++;
            }
        }

        return (brNepovlascenih != 0)? (ukupniKoefNepovlascenih / brNepovlascenih) : 0;
    }
}

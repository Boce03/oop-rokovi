package com.example.jun22019;

public class Nepersonalizovana extends BusPlus{
    private int kredit;
    private boolean ocitana;
    private static int cenaVoznje = 90;

    public Nepersonalizovana(int id, int zona, int kredit, boolean ocitana) {
        super(id, zona);
        this.kredit = kredit;
        this.ocitana = ocitana;
    }

    public Nepersonalizovana(int id, int zona, int kredit) {
        super(id, zona);
        this.kredit = kredit;
    }

    public int getKredit() {
        return kredit;
    }

    public boolean isOcitana() {
        return ocitana;
    }
    public boolean nedovoljnoKredita(){
        return kredit >= cenaVoznje;
    }

    public boolean ocitajKarticu(){
        if(ocitana || !nedovoljnoKredita()){
            return false;
        }

        kredit -= cenaVoznje;
        ocitana = true;

        return true;
    }

    public static int getCenaVoznje() {
        return cenaVoznje;
    }

    @Override
    public String toString() {
        if(ocitana) {
            return "[NP] " + super.toString() + ", kredit: " + kredit + " | " + "ocitana";
        }

        return "[NP] " + super.toString() + ", kredit: " + kredit + " | " +
                ((nedovoljnoKredita())? "nije ocitana" : "nedovoljno kredita");
    }

}

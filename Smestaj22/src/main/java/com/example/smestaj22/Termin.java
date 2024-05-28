package com.example.smestaj22;

public class Termin implements Comparable<Termin>{
    Datum odDatum;
    Datum doDatum;

    public boolean validanTermin(){
        return odDatum.compareTo(doDatum) < 0;
    }

    public Termin(Datum odDatum, Datum doDatum) {
        this.odDatum = new Datum(odDatum);
        this.doDatum = new Datum(doDatum);

        if(this.validanTermin()){
            odDatum = new Datum(1, 1);
            doDatum = new Datum(12, 12);
        }
    }

    @Override
    public String toString() {
        return odDatum + " - " + doDatum;
    }

    public boolean preklapaSeSa(Termin termin){
        return (termin.odDatum.compareTo(odDatum) >= 0 && termin.odDatum.compareTo(doDatum) <= 0) ||
                (termin.doDatum.compareTo(odDatum) >= 0 && termin.doDatum.compareTo(doDatum) <= 0);
    }

    @Override
    public int compareTo(Termin o) {
        return odDatum.compareTo(o.odDatum);
    }
}

package com.example.smestaj22;

public class Datum implements Comparable<Datum>{
    private int dan;
    private int mesec;

    public Datum(int dan, int mesec) {
        this.dan = dan;
        this.mesec = mesec;
    }

    public static boolean validanDatum(String datum){
        int od = datum.indexOf('/');
        if(od == -1)
            return false;

        int mesec, dan;

        if(datum.substring(0, od).charAt(0) != '0')
            dan = Integer.parseInt(datum.substring(0, od));
        else
            dan = Integer.parseInt(datum.substring(1, od));

        if(dan <= 0)
            return false;

        if(datum.substring(od).charAt(0) != '0')
            mesec = Integer.parseInt(datum.substring(od + 1));
        else
            mesec = Integer.parseInt(datum.substring(od + 2));


        switch (mesec){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return dan <= 31;

            case 2:
                return dan <= 29;

            case 4:
            case 6:
            case 9:
            case 11:
                return dan <= 30;

            default:
                return false;
        }
    }

    public Datum(String datum){
        int od = datum.indexOf('/');

        if(datum.substring(0, od).charAt(0) != '0')
            dan = Integer.parseInt(datum.substring(0, od));
        else
            dan = Integer.parseInt(datum.substring(1, od));

        if(datum.substring(od).charAt(0) != '0')
            mesec = Integer.parseInt(datum.substring(od + 1));
        else
            mesec = Integer.parseInt(datum.substring(od + 2));
    }

    public Datum(Datum datum){
        this(datum.dan, datum.mesec);
    }

    public int getDan() {
        return dan;
    }

    public int getMesec() {
        return mesec;
    }

    @Override
    public String toString() {
        return dan + "/" + mesec;
    }

    @Override
    public int compareTo(Datum o) {
        if(mesec != o.mesec)
            return Integer.compare(mesec, o.mesec);

        return Integer.compare(dan, o.dan);
    }
}

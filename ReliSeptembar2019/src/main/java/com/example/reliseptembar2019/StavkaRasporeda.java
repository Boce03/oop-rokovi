package com.example.reliseptembar2019;

public class StavkaRasporeda implements Comparable<StavkaRasporeda>{
    private ReliVozac vozac;
    private int startH;
    private int startM;

    public StavkaRasporeda(ReliVozac vozac, int startH, int startM) {
        this.vozac = vozac;
        if(startH >= 0 && startH < 23)
            this.startH = startH;
        else
            this.startH = 0;

        if(startM >= 0 && startM < 60)
            this.startM = startM;
        else
            this.startM = 0;
    }

    public int getStartH() {
        return startH;
    }

    public int getStartM() {
        return startM;
    }

    public ReliVozac getVozac() {
        return vozac;
    }

    public int vratiMinute() {
        return 60 * startH + startM;
    }

    @Override
    public String toString() {
        return "[" + startH + ":" + startM + "] " + vozac;
    }

    @Override
    public int compareTo(StavkaRasporeda o) {
        if(startH != o.startH)
            return Integer.compare(startH, o.startH);
        else
            return Integer.compare(startM, o.startM);
    }
}

package com.example.nastava2019;

public class Tekstualni extends NastavniMaterijal{
    private boolean prateciSadrzaj;

    public Tekstualni(String naslov, String format, boolean dopunskiSadrzaj) {
        super(naslov, format);
        this.prateciSadrzaj = dopunskiSadrzaj;
    }

    @Override
    public boolean zaOcenu(String kriterijum) {
        return kriterijum.equals(getFormat())
                || (kriterijum.equals("da") && prateciSadrzaj)
                || (kriterijum.equals("ne") && !prateciSadrzaj);
    }

    public boolean isDopunskiSadrzaj() {
        return prateciSadrzaj;
    }

    @Override
    public String toString() {
        if(prateciSadrzaj){
            return getNaslov() + " (" + getFormat() + ") + " +
                    ((getFormat().equals("pdf"))? "za radoznale" : "domaci");
        }

        return getNaslov() + " (" + getFormat() + ")";
    }

    @Override
    public int compareTo(NastavniMaterijal o) {
        if(o instanceof Video) return -1;

        if(getFormat().equals("pdf") && o.getFormat().equals("zip"))
            return -1;
        else if(getFormat().equals("zip") && o.getFormat().equals("pdf"))
            return 1;
        else return getNaslov().compareTo(o.getNaslov());
    }
}

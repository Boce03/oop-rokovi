package com.example.nastava2019;

public abstract class NastavniMaterijal implements Comparable<NastavniMaterijal>{
    private String naslov;
    private String format;

    public NastavniMaterijal(String naslov, String format) {
        this.naslov = naslov;
        this.format = format;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getFormat() {
        return format;
    }

    public abstract boolean zaOcenu(String kriterijum);
}

package com.example.jun22019;

public abstract class BusPlus{
    private int id;
    private int zona;

    public BusPlus(int id, int zona) {
        this.id = id;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public int getZona() {
        return zona;
    }

    @Override
    public String toString() {
        return "zona: " + zona;
    }
}

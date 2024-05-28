package com.example.jan12023paketici;

public class UredjenPar <T1, T2>{
    private T1 prvi;
    private T2 drugi;

    public UredjenPar(T1 prvi, T2 drugi) {
        this.prvi = prvi;
        this.drugi = drugi;
    }

    public T1 getPrvi() {
        return prvi;
    }

    public T2 getDrugi() {
        return drugi;
    }

    public void setDrugi(T2 drugi) {
        this.drugi = drugi;
    }
}

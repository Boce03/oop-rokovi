package com.example.jun12019;

public class Zaglavlje {
    private String autor;
    private String vremenskaOznaka; //format DD.MM.GGGG SS:MM

    public Zaglavlje(String autor, String vremenskaOznaka) {
        this.autor = autor;
        this.vremenskaOznaka = vremenskaOznaka;
    }

    public Zaglavlje(Zaglavlje z){
        autor = z.autor;
        vremenskaOznaka = z.vremenskaOznaka;
    }

    public String getAutor() {
        return autor;
    }

    public String getVremenskaOznaka() {
        return vremenskaOznaka;
    }

    @Override
    public String toString() {
        return autor + " " + vremenskaOznaka;
    }
}

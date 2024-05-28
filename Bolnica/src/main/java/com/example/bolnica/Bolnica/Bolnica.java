package com.example.bolnica.Bolnica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bolnica {
    private List<Pacijent> cekaonica;
    private List<Pacijent> izolacija;
    private List<Pacijent> zdravi;

    public Bolnica() {
        cekaonica = new LinkedList<>();
        izolacija = new LinkedList<>();
        zdravi = new LinkedList<>();
    }

    public List<Pacijent> getCekaonica() {
        return cekaonica;
    }

    public List<Pacijent> getIzolacija() {
        return izolacija;
    }

    public List<Pacijent> getZdravi() {
        return zdravi;
    }

    public void setCekaonica(List<Pacijent> cekaonica) {
        this.cekaonica = cekaonica;
    }

    public void setIzolacija(List<Pacijent> izolacija) {
        this.izolacija = izolacija;
    }

    public void setZdravi(List<Pacijent> zdravi) {
        this.zdravi = zdravi;
    }

    public void ucitaj(){
        Path putanja = Paths.get("pacijenti.txt");
        try(Scanner sc = new Scanner(putanja)){
            int br = 0;
            while(sc.hasNext()){
                String ime = sc.next().replace(",", "");
                String prezime = sc.next().replace(",", "");
                String bolest = sc.next().replace(",", "");
                int duzina = Integer.parseInt(sc.next().replace(",", ""));

                if(bolest.equals("k")){
                    String simptomi = sc.next().replace(",", "");

                    boolean imaSimptome;
                    if(simptomi.equals("da")){
                        imaSimptome = true;
                    } else{
                        imaSimptome = false;
                    }

                    cekaonica.add(new Pacijent(ime, prezime, br, new Korona(duzina, imaSimptome)));
                } else{
                    cekaonica.add(new Pacijent(ime, prezime, br, new Grip(duzina)));
                }

                br++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sledeci(){
        Pacijent p = cekaonica.removeFirst();
        if(p.getBolest() instanceof Korona){
            if(((Korona) p.getBolest()).test()){
                p.setZarazen(true);
                izolacija.add(p);
            } else{
                p.setDuzinaLecenja(0);
                p.setBolest(new Korona(0, ((Korona) p.getBolest()).isPokazujeSimptome()));
                zdravi.add(p);
            }
        } else{
            p.setZarazen(true);
            izolacija.add(p);
        }
    }

    public void unesi(){
        Path putanja = Paths.get("izvestaji.txt");
        List<String> ulaz = new LinkedList<>();

        for (Pacijent p : cekaonica) {
            ulaz.add(p.toString() + "\n");
        }

        for(Pacijent p : izolacija){
            ulaz.add(p.toString() + "\n");
        }

        for(Pacijent p : zdravi){
            ulaz.add(p.toString() + "\n");
        }

        try {
            Files.write(putanja, ulaz, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

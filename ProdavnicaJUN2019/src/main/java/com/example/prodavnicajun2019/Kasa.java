package com.example.prodavnicajun2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kasa {
    private static List<Artikal> artikli = new ArrayList<>();
    public static void ucitajArtikle(){
        try {
            List<String> linije = Files.readAllLines(Paths.get("artikli.txt"));
            for(String linija: linije){
                String[] ulaz = linija.split(",");
                int sifra = Integer.parseInt(ulaz[0].trim());
                String naziv = ulaz[1].trim();
                double cena = Double.parseDouble(ulaz[2].trim());

                if(ulaz.length > 3){
                    String datum = ulaz[4].trim();
                    int id;
                    if((id = ulaz[3].indexOf("%")) != -1){
                        int procenat = Integer.parseInt(ulaz[3].substring(0, id).trim());
                        artikli.add(new Artikal(sifra, naziv, cena, new Popust(datum, procenat)));
                    } else{
                        String[] gratis = ulaz[3].trim().split("za");
                        int potrebnoKomada = Integer.parseInt(gratis[1].trim());
                        int gratisKomada = Integer.parseInt(gratis[0].trim()) - potrebnoKomada;
                        artikli.add(new Artikal(sifra, naziv, cena, new Gratis(datum, potrebnoKomada, gratisKomada)));
                    }
                } else{
                    artikli.add(new Artikal(sifra, naziv, cena));
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Artikal> ocitajArtikal(int sifra){
        return artikli.stream().filter(a -> a.getSifra() == sifra).findAny();
    }

    public static List<Artikal> artikliNaAkciji(){
        return artikli.stream().filter(Artikal::naAkciji).sorted().toList();
    }
}

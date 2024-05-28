package com.example.reliseptembar2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReliRaspored {
    private List<StavkaRasporeda> redVoznje = new LinkedList<>();
    private int maxVremeVoznje;

    public ReliRaspored(int maxVremeVoznje) {
        this.maxVremeVoznje = maxVremeVoznje;
    }

    public ReliRaspored(){
        this.maxVremeVoznje = 30;
    }

    public List<StavkaRasporeda> getRedVoznje() {
        return redVoznje;
    }

    public int getMaxVremeVoznje() {
        return maxVremeVoznje;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(StavkaRasporeda s: redVoznje){
            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public boolean ucitaj(String putanja){
        try {
            List<String> linije = Files.readAllLines(Paths.get(putanja));
            for(String linija: linije){
                String[] unos = linija.split(",");

                if(unos[3].trim().equals("Grupa B")){
                    boolean superCharger = false;
                    if(unos.length == 8){
                        superCharger = true;
                    }

                    ReliAuto auto = new GrupaBReliAuto(unos[4].trim(),
                                    Pogon.odNaziva(unos[6].trim()),
                                    Integer.parseInt(unos[5].trim()), superCharger);

                    ReliVozac vozac = new ReliVozac(unos[2].trim(), auto);

                    redVoznje.add(new StavkaRasporeda(vozac,
                            Integer.parseInt(unos[0].trim()),
                            Integer.parseInt(unos[1].trim())));
                } else{
                    ReliAuto auto = new GrupaAReliAuto(unos[4].trim(),
                            Pogon.odNaziva(unos[6].trim()),
                            Integer.parseInt(unos[5].trim()));

                    ReliVozac vozac = new ReliVozac(unos[2].trim(), auto);

                    redVoznje.add(new StavkaRasporeda(vozac,
                            Integer.parseInt(unos[0].trim()),
                            Integer.parseInt(unos[1].trim())));
                }
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean dodaj(ReliVozac v, int h, int m){
        int minuti = 60*h + m;
        for(StavkaRasporeda s: redVoznje){
            if(!(minuti > s.vratiMinute() + maxVremeVoznje
                    || minuti + maxVremeVoznje < s.vratiMinute())){
                return false;
            }
        }

        redVoznje.add(new StavkaRasporeda(v, h, m));

        return true;
    }

    public void sortiraj(){
        Collections.sort(redVoznje);
    }

    public int brojAutomobilaSaGodistemVecimOd(int g){
        if(redVoznje.isEmpty()) return 0;

        return (int)redVoznje.stream()
                .filter(s -> s.getVozac().getAuto().getGodiste() > g)
                .count();
    }

    public List<ReliAuto> saPogonom(Pogon p){
        List<ReliAuto> listaSaPogonom = new LinkedList<>();
        for(StavkaRasporeda s: redVoznje){
            if(s.getVozac().getAuto().getTipPogona().equals(p)){
                listaSaPogonom.add(s.getVozac().getAuto());
            }
        }

        return listaSaPogonom;
    }
}

package com.example.jun22019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GSPVozilo {
    private Map<Integer, BusPlus> kartice;
    private List<Integer> nevalidne;
    private static Random rand = new Random();
    private static int noviId = 1000;

    public GSPVozilo() {
        kartice = new HashMap<>();
        nevalidne = new LinkedList<>();
    }

    public List<BusPlus> getKartice(){
        List<BusPlus> izlaz = new LinkedList<>();
        kartice.forEach((key, value)->izlaz.add(value));
        return izlaz;
    }

    public boolean putniciUVozilu(String putanja){
        try {
            List<String> linije = Files.readAllLines(Paths.get(putanja));
            for(String linija: linije){
                String[] podaci = linija.split(",");

                int id = Integer.parseInt(podaci[1].trim());
                int zona = Integer.parseInt(podaci[2].trim());
                BusPlus putnik = null;

                if(podaci[0].trim().equals("P")){
                    Kategorija kategorija = Kategorija.valueOf(podaci[3].trim());
                    boolean imaDopunu = podaci[4].trim().equals("da");
                    putnik = new Personalizovana(id, zona, kategorija, imaDopunu);

                } else{
                    int kredit = Integer.parseInt(podaci[3].trim());
                    boolean ocitana = podaci[4].trim().equals("+");
                    putnik = new Nepersonalizovana(id, zona, kredit, ocitana);
                }

                kartice.put(id, putnik);
            }

            return !kartice.isEmpty();
        } catch (IOException e) {
            return false;
        }
    }

    public BusPlus noviPutnik(int vrstaKartice){

        while(kartice.containsKey(noviId)){
            noviId++;
        }
        int id = noviId++;
        int zona = vrstaKartice;


        if(vrstaKartice == 1){
            Kategorija kategorija = Kategorija.izBroja(rand.nextInt(0, 4));
            boolean imaDopuna = rand.nextDouble(0, 1) < 0.5;

            Personalizovana p = new Personalizovana(id, zona, kategorija, imaDopuna);
            kartice.put(id, p);
            return p;
        } else{
            int kredit = rand.nextInt(0, 2*Nepersonalizovana.getCenaVoznje()+1);
            Nepersonalizovana np = new Nepersonalizovana(id, zona, kredit);
            np.ocitajKarticu();

            kartice.put(id, np);
            return np;
        }
    }

    public String kontrola(){
        StringBuilder sb = new StringBuilder("Kontrola:\n");

        for(Map.Entry<Integer, BusPlus> mp: kartice.entrySet()){
            BusPlus putnik = mp.getValue();
            if(putnik instanceof Personalizovana && !((Personalizovana) putnik).isImaDopunu()){
                sb.append("-").append(putnik).append("\n");
                nevalidne.add(mp.getKey());
            } else if(putnik instanceof Nepersonalizovana && !((Nepersonalizovana) putnik).isOcitana()){
                sb.append("-").append(putnik).append("\n");
                nevalidne.add(mp.getKey());
            } else{
                sb.append("+").append(putnik).append("\n");
            }
        }

        return sb.toString();
    }

    public boolean izbaciPutnike(){
        if(nevalidne.isEmpty()) return false;

        for(int key: nevalidne){
            kartice.remove(key);
        }

        return true;
    }
}

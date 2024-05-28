package com.example.nastava2019;

import java.util.Optional;

public class Video extends NastavniMaterijal{
    private int duzinaTrajanja;
    private int brojPregleda;
    private int brojSvidjanja;

    public Video(String naslov, String format, int duzinaTrajanja, int brojPregleda, int brojSvidjanja) {
        super(naslov, format);
        this.duzinaTrajanja = duzinaTrajanja;
        this.brojPregleda = brojPregleda;
        this.brojSvidjanja = brojSvidjanja;
    }

    public int getDuzinaTrajanja() {
        return duzinaTrajanja;
    }

    public int getBrojPregleda() {
        return brojPregleda;
    }

    public int getBrojSvidjanja() {
        return brojSvidjanja;
    }

    public void povecajPregled(){
        brojPregleda++;
    }

    public void povecajBrojLajkova(){
        brojSvidjanja++;
    }

    private Optional<Integer> jelCeoBroj(String kriterijum){
        try{
            Integer test = Integer.parseInt(kriterijum);
                return Optional.of(test);
        } catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    @Override
    public boolean zaOcenu(String kriterijum) {
        if(kriterijum.equals("mp4")){
            return true;
        }

        Optional<Integer> vr = jelCeoBroj(kriterijum);
        if(vr.isPresent()){
            return vr.get() <= brojPregleda;
        }

        return false;
    }

    @Override
    public String toString() {
        int brojSati = duzinaTrajanja / 60;
        int brojMinuta = duzinaTrajanja % 60;

        return getNaslov() + " (" + getFormat() + ") [" + brojSati + ":" + brojMinuta
                + "] pregleda: " + brojPregleda + " svidjanja: " + brojSvidjanja;
    }

    @Override
    public int compareTo(NastavniMaterijal o) {
        if(o instanceof Tekstualni) return 1;

        Video v = (Video) o;
        return Integer.compare(v.brojPregleda, brojPregleda);
    }
}

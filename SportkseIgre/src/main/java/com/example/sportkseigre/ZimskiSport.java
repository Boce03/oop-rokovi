package com.example.sportkseigre;

import java.util.LinkedList;
import java.util.List;

public class ZimskiSport extends Sport{
    List<Rekvizit> rekviziti = new LinkedList<>();

    public ZimskiSport(String naziv, boolean individualni) {
        super(naziv, individualni);
    }

    @Override
    public int ulaganje() {
        if(rekviziti.isEmpty())
            return 0;

        int ukupnoUlaganje = 0;
        for(Rekvizit r: rekviziti){
            ukupnoUlaganje += r.getCena();
        }

        return ukupnoUlaganje;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("naziv: ").append(getNaziv()).append(", tip: ").
                append((isIndividualni())? "individualni" : "sportski").append(", potrebni rekviziti:\n");

        for(Rekvizit r: rekviziti){
            sb.append(r.getNaziv()).append("\n");
        }

        sb.append("godisnje ulaganje: ").append(ulaganje());

        return sb.toString();
    }
}

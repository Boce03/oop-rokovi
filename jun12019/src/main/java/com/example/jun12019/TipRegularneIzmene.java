package com.example.jun12019;

public enum TipRegularneIzmene {
    NovaFunkcionalnost(1),
    IspravljenBag(2),
    BaterijaTestova(3);

    TipRegularneIzmene(int i) {
    }

    public static TipRegularneIzmene izBroja(int i){
        switch (i){
            case 2: return IspravljenBag;
            case 3: return BaterijaTestova;
            case 1:
            default: return NovaFunkcionalnost;
        }
    }

    public static int uBroj(TipRegularneIzmene t){
        switch (t){
            case NovaFunkcionalnost: return 1;
            case IspravljenBag: return 2;
            case BaterijaTestova: return 3;
            default: throw new RuntimeException("nepostojeca opcija!!!");
        }
    }
}

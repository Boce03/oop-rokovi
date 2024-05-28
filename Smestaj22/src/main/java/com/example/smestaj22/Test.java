package com.example.smestaj22;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dOd = scanner.next();
        String dDo = scanner.next();

        Termin termin = new Termin(new Datum(dOd), new Datum(dDo));


        dOd = scanner.next();
        dDo = scanner.next();

        Termin noviTermin = new Termin(new Datum(dOd), new Datum(dDo));

        if(termin.preklapaSeSa(noviTermin)){
            System.out.println("preklapaju se");
        } else{
            System.out.println("ne preklapaju se");
        }
    }
}

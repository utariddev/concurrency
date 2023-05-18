package org.utarid;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Printer {

    private Printer() {
    }

    static void print(String name, int start) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");

        int i = 1;
        while (i <= 10) {
            System.out.println(formatter.format(new Date()) + " - " + (start++) + " - " + name);
            i++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException:");
                Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            }
        }
    }
}
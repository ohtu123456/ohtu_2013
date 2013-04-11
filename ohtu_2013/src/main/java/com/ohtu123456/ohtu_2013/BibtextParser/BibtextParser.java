/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtu123456.ohtu_2013.BibtextParser;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author sikuutti
 */
public class BibtextParser {

    public String tulostaBibTex(String id) {

        String paluu;
        id = id.substring(1);
        String str[] = id.split(",");


        paluu = ("@Tyyppi{tunnus," + "\n");
        for (int i = 0; i < str.length; i++) {
            String input = str[i];
            input = input.replaceAll("=", "={");
            input = korjaa_Aakkoset(input);
            input += ("},");


            paluu += input + "\n";
        }
        paluu += ("}" + "\n");


        tulostaFileen(paluu);

        return paluu;
    }

    public static String korjaa_Aakkoset(String sana) {

        sana = sana.replaceAll("ä", "{ä}");
        sana = sana.replaceAll("Ä", "{Ä}");
        sana = sana.replaceAll("å", "{å}");
        sana = sana.replaceAll("Å", "{Å}");
        sana = sana.replaceAll("ö", "{ö}");
        sana = sana.replaceAll("Ö", "{Ö}");

        return sana;
    }

    public static void tulostaFileen(String paluu) {

        try {

            FileWriter fs = new FileWriter("Bibtext.txt", true);
            BufferedWriter bw = new BufferedWriter(fs);
            bw.write(paluu);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

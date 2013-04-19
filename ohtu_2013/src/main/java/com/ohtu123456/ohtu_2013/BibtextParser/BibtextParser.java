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

    /**
     * Metodi muuttaa tekstin bibtext formaattiin
     *
     * @param id
     * @return Palauttaa tulostetun joukon takaisin.
     */
    public static String parseBibtext(String id) {

        String paluu = "";
        String alku = "@";
        String str[] = id.split("::");

        alku += str[0];
        str[0] = alku;

        String input = "";
        input = str[0];
        for (int i = 0; i < str.length; i++) {

            input = str[i];
            if (i != 0) {
                input = input.replaceAll("=", "={");
            } else {

                input = input.replaceAll("=", "{");
            }

            input = korjaa_Aakkoset(input);
            if (i != 0) {
                input += ("},");
            } else {
                input += (",");
            }

            paluu += input + "\n";

        }
        paluu += ("}" + "\n");


        tulostaFileen(paluu);
        System.out.println(paluu);
        return paluu;
    }

    /**
     * Metodi muuttaa ääkköset ja muut väärät kirjaimet bibtext muotuun
     *
     * @param sana
     * @return
     */
    public static String korjaa_Aakkoset(String sana) {

        sana = sana.replaceAll("ä", "{ä}");
        sana = sana.replaceAll("Ä", "{Ä}");
        sana = sana.replaceAll("å", "{å}");
        sana = sana.replaceAll("Å", "{Å}");
        sana = sana.replaceAll("ö", "{ö}");
        sana = sana.replaceAll("Ö", "{Ö}");

        return sana;
    }

    /**
     * Metodi tulostaa bibtext filen
     */
    public static void tulostaFileen(String paluu) {

        try {

            FileWriter fs = new FileWriter("./target/sigproc.bib", true);
            BufferedWriter bw = new BufferedWriter(fs);
            bw.write(paluu);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public String tulostaBibTex(String id) {

        parseBibtext(sewbok);
        parseBibtext(BA04);
        parseBibtext(royce70);
        parseBibtext(Begel_2008);
        parseBibtext(fox);
        parseBibtext(Martin09);
        parseBibtext(scrum);


        return id;

    }
    public String sewbok = "book=SWEBOK::publisher=IEEE Computer Society::editor=Abram, Aman and More, James W. and Bourgue Pierre and Dupuis Robert"
            + "::year=2004::title=Guide to the Software Engineering Body of Knownledge";
    public String BA04 = "book=BA04::author = Beck, Kent and Andres, Cynthia::title = Extreme Programming Explained: Embrace Change (2nd Edition)::"
            + "year=2004::publisher = Addison-Wesley Professional";
    public String royce70 = "INPROCEEDINGS=royce70::AUTHOR = Royce, Walker::TITLE = Managing the Development of Large Software Systems::"
            + "BOOKTITLE = Proceedings of IEEE WESCON 26::ORGANIZATION = TeX Users Group::MONTH = August::YEAR =1970";
    public String Begel_2008 = "inproceedings=Begel_2008::author = Begel, Andrew and Simon, Beth::title = Struggles of new college graduates in their first software development job::booktitle=Proceedings of the SIGCSE '08::"
            + "year =2008::publisher = ACM";
    public String fox = "article=fox::author = Fox, Armando and Patterson, David::title = Crossing the software education chasm::"
            + "journal=Communications of ACM::"
            + "volume=55::"
            + "number=5::"
            + "month=may::"
            + "year=2012::"
            + "pages=44--49::"
            + "publisher=ACM::"
            + "address =New York, NY, USA";
    public String Martin09 = "book=Martin09::"
            + "author=Martin, Robert C::"
            + "title=Clean Code: A Handbook of Agile Software Craftsmanship::"
            + "year=2008::"
            + "publisher =Prentice Hall::";
    public String scrum = "Book=scrum::"
            + "author = Ken Schwaber and Mike Beedle::"
            + "publisher = Prentice Hall::"
            + "title=Agile Software Development with SCRUM::"
            + "year=2002";
}

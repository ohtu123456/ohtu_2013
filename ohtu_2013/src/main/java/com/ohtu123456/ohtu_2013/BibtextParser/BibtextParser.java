package com.ohtu123456.ohtu_2013.BibtextParser;

import com.ohtu123456.ohtu_2013.Storage.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.directory.AttributeInUseException;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author sikuutti
 */
public class BibtextParser {

    /*
     * sisääntulo yksi HashMap 
     * kutsutaan ui:sta jokailelle referenssille erokseen
     * 
     * ulos Bibtex muotoinen string
     */
  

    public String convertToBibtext(Map<String, String> reference) {

        String paluu = "";

        paluu = "@" + reference.get("type") + "{" + reference.get("id") + ",\n";
        reference.remove("type");
        reference.remove("id");
        for (Map.Entry<String, String> entry : reference.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            paluu += key + " = {" + value + "},\n";


        }
        paluu += "}";
        System.out.println(paluu);



        paluu = korjaa_Aakkoset(paluu);

        return paluu;
    }
    /*
     * Korjaa ääkköset oikeaan muotoon
     * 
     * 
     */

    public  String korjaa_Aakkoset(String sana) {

        sana = sana.replaceAll("ä", "{ä}");
        sana = sana.replaceAll("Ä", "{Ä}");
        sana = sana.replaceAll("å", "{å}");
        sana = sana.replaceAll("Å", "{Å}");
        sana = sana.replaceAll("ö", "{ö}");
        sana = sana.replaceAll("Ö", "{Ö}");

        return sana;
    }

    /*
     * sisääntulo yksi HashMap 
     * 
     * Muokkaa oikeaan järjestykseen 
     * 
     * Pakolliset kentät 
     * Kenttien nimet otettu logic new reference 
     * ulos ihmiskielinen string
     */
    public String printClear(Map<String, String> reference) {

        String paluu = "";
        String type = reference.get("type") + "\n";
        paluu += "Type = " + reference.get("type") + "\n";
        paluu += "Author = " + reference.get("author") + "\n";
        paluu += "Title = " + reference.get("title") + "\n";
        paluu += "Year = " + reference.get("year") + "\n";
        paluu += "Publisher = " + reference.get("publisher") + "\n";

        if (type.equals("article")) {
            paluu += "Journal = " + reference.get("journal") + "\n";
            paluu += "Volume = " + reference.get("volume") + "\n";
            paluu += "Number = " + reference.get("number") + "\n";
            paluu += "Pages = " + reference.get("pages") + "\n";
            paluu += "Address = " + reference.get("address") + "\n";
        } else if (type.equals("inproceedings")) {

            paluu += "Booktitle = " + reference.get("booktitle") + "\n";
            paluu += "Pages = " + reference.get("pages") + "\n";
        }
        paluu = korjaa_Aakkoset(paluu);
        return paluu;
    }
    /*
     * sisään yksi hashmap
     * 
     * tulostaa tiiviin muodon yhdestä
     * author.tittle,id,vuosi
     * 
     * palauttaa String 
     */

    public  String printBibTex(Map<String, String> reference) {
        String paluu = "";

        paluu += "Author = " + reference.get("author") + "\n";
        paluu += "Title = " + reference.get("title") + "\n";
        paluu += "Year = " + reference.get("year") + "\n";
        paluu = korjaa_Aakkoset(paluu);
        return paluu;
    }
}

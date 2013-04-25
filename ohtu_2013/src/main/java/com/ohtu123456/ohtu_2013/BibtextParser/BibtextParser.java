package com.ohtu123456.ohtu_2013.BibtextParser;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sikuutti
 */
public class BibtextParser {

    LogicInterface logic;
    /*
     * sisääntulo yksi HashMap 
     * kutsutaan ui:sta jokailelle referenssille erokseen
     * 
     * ulos Bibtex muotoinen string
     */

    public BibtextParser() {
    }

    public BibtextParser(LogicInterface logic) {
        this.logic = logic;
    }

    public String getType(Map<String, String> reference) {
        String paluu = "";

        if (reference.size() == 5) {
            paluu = "book";
        }

        if (reference.size() == 8) {
            paluu = "inproceedings";
        }

        if (reference.size() == 10) {
            paluu = "article";
        }

        return paluu;

    }

    public String convertToBibtext(Map<String, String> reference) {

        String paluu = "";

        String tyyppi = getType(reference);

        paluu = "@" + tyyppi + "{" + reference.get("id") + ",";
        reference.remove("type");
        reference.remove("id");
        for (Map.Entry<String, String> entry : reference.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            paluu += key + " = {" + value + "},";


        }
        paluu += "}";




        paluu = korjaa_Aakkoset(paluu);

        return paluu;
    }

    public String convertToBibtextPrintAll(Map<String, String> reference) {

        String paluu = "";

        String tyyppi = getType(reference);

        paluu = "@" + tyyppi + "{" + reference.get("id") + ",\n";
        reference.remove("type");
        reference.remove("id");
        for (Map.Entry<String, String> entry : reference.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            paluu += "\t"+key + " = {" + value + "},\n";


        }
        paluu += "}";




        paluu = korjaa_Aakkoset(paluu);

        return paluu;
    }
    /*
     * Korjaa ääkköset oikeaan muotoon
     * 
     * 
     */

    public String korjaa_Aakkoset(String sana) {

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
        String tyyppi = getType(reference);
        String paluu = "";
        String type = tyyppi + "\n";
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

    public String printBibTex(Map<String, String> reference) {
        String paluu = "";

        paluu += "{ID: " + reference.get("id") + ", "
                + "AUTHOR: " + reference.get("author") + ", "
                + "TITLE: " + reference.get("title") + ", "
                + "YEAR: " + reference.get("year") + "}";
        return paluu;
    }

    public void saveAsBibTex(String filename, List d) throws java.io.IOException {

        List<Map<String, String>> k = d;
        File f1 = new File(filename);

        if (f1.exists()) {
            f1.delete();
        }



        FileWriter fstream = new FileWriter(filename, true);
        BufferedWriter out = new BufferedWriter(fstream);
        for (Map<String, String> ref : k) {
            out.write(convertToBibtextPrintAll(ref)+"\n");
        }
        out.close();




    }
}

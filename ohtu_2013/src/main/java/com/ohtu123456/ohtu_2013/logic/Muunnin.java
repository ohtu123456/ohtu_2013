/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtu123456.ohtu_2013.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

/**
 *
 * @author sikuutti
 */
public class Muunnin {

    public static String tulostaBibTex(String id) {
        
      String paluu;
        String str[] = id.split(";");
        
        paluu=("@" + str[0] + "{" + str[1] + ","+"\n");
        for (int i = 2; i < str.length; i++) {
            String input = str[i];
            input = input.replaceAll("=", "={");
            input = korjaa_Aakkoset(input);
            input += ("},");


            paluu+=input+"\n";
        }
        paluu+=("}"+"\n");



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
    
//   public static void main(String args[]){
       
//    String viite1 = "book;B234;author=Vähavainen, Arto and Paksula, Matti and Luukkainen, Matti;title =Extreme Apprenticeship Method in Teaching Programming for Beginners.;year=2011;booktitle=SIGCSE '11: Proceedings of the 42nd SIGCSE technical symposium on Computer science education";
   
//       System.out.println(tulostaBibTex(viite1)); 
//   }
    
    
}

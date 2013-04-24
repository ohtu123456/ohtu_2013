/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtu123456.ohtu_2013.BibtextParser;

import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
 * @author sikuutti
 */
public class BibtextParserTest extends TestCase {

    public BibtextParserTest(String testName) {
        super(testName);
    }
    BibtextParser bib;

    @Override
    protected void setUp() throws Exception {

        this.bib = new BibtextParser();
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testAakkoset() {
        String testi = "ä";
        String testi1 = "Ä";

        assertEquals("{ä}", bib.korjaa_Aakkoset(testi));
        assertEquals("{Ä}", bib.korjaa_Aakkoset(testi1));

    }

    public void testTulostaBibTex() {
        String paluu = "@book{SOMEBOOK1,\n" +
"author = {Maija Mallikas},\n" +
"title = {Maijan opas matematiikkaan},\n" +
"year = {2013},\n" +
"publisher = {AlmaMedia},\n" +
"}";

        
      Map<String, String> reference = new HashMap();
        
        reference.put("id", "SOMEBOOK1");
        reference.put("author", "Maija Mallikas");
        reference.put("title", "Maijan opas matematiikkaan");
        reference.put("year", "2013");
        reference.put("publisher", "AlmaMedia");

        assertEquals(paluu, bib.convertToBibtext(reference));
    }

    public void printClear() {
        String paluu ="Type = null\n" +
"Author = Maija Mallikas\n" +
"Title = Maijan opas matematiikkaan\n" +
"Year = 2013\n" +
"Publisher = AlmaMedia";

        Map<String, String> reference = new HashMap();
        
        reference.put("id", "SOMEBOOK1");
        reference.put("author", "Maija Mallikas");
        reference.put("title", "Maijan opas matematiikkaan");
        reference.put("year", "2013");
        reference.put("publisher", "AlmaMedia");


        assertEquals(paluu, bib.printClear(reference));
    }

    public void printBibTex() {
        String paluu = "Author = Maija Mallikas\n" +
"Title = Maijan opas matematiikkaan\n" +
"Year = 2013";

        Map<String, String> reference = new HashMap();
        reference.put("id", "SOMEBOOK1");
        reference.put("author", "Maija Mallikas");
        reference.put("title", "Maijan opas matematiikkaan");
        reference.put("year", "2013");
        reference.put("publisher", "AlmaMedia");

        assertEquals(paluu, bib.printClear(reference));
    }

    public void testType() {
        Map<String, String> reference = new HashMap();
        reference.put("id", "SOMEBOOK1");
        reference.put("author", "Maija Mallikas");
        reference.put("title", "Maijan opas matematiikkaan");
        reference.put("year", "2013");
        reference.put("publisher", "AlmaMedia");

        assertEquals("book", bib.getType(reference));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohtu123456.ohtu_2013.BibtextParser;

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
      
        this.bib=new BibtextParser(); 
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

public void testAakkoset(){
    String testi="ä";
    String testi1="Ä";
    
    assertEquals("{ä}",bib.korjaa_Aakkoset(testi));
    assertEquals("{Ä}",bib.korjaa_Aakkoset(testi1));

}
public void testtulostaBibTex (){
    
    assertNotSame("",bib.tulostaBibTex(""));
}

}

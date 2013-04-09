package com.ohtu123456.ohtu_2013.logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.ohtu123456.ohtu_2013.Storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Pelkkä testilogiikka käyttöliittymää varten, korvataan oikealla
 * logiikkaluokalla. Pistän toistaiseksi tänne vain yhden testimetodin, jotta
 * saan testattua UI:ta
 *
 * @author Leif Setälä
 */
@Component
public class Logic implements LogicInterface {
   
    Storage storage;
    
    public Logic() {
        storage = new Storage();
    }

    public boolean addReference(Map<String, String> reference) {
        storage.addReference(reference);
        return true;
    }

    public String printBibTex(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, String> printClear(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, String> giveFields(int id) {
        Map<String, String> fields = new LinkedHashMap<String, String>(); //LinkedHashMap säilyttää alkioiden järjestyksen
        fields.put("Author", null);
        fields.put("Title", null);
        fields.put("Year", null);
        fields.put("Publisher", null);
        return fields;
    }

    public ArrayList<Map<String, String>> giveAllReferences() {
        return storage.getReferences();
    }
}

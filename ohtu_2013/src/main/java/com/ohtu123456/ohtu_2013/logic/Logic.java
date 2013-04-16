package com.ohtu123456.ohtu_2013.logic;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ohtu123456.ohtu_2013.BibtextParser.BibtextParser;
import com.ohtu123456.ohtu_2013.Storage.Storage;
import java.util.List;

@Component
public class Logic implements LogicInterface {
   
    Storage storage;
    BibtextParser parser;
    
    public Logic() {
        storage = new Storage();
        parser= new BibtextParser();
    }

    public boolean addReference(Map<String, String> reference) {
        storage.addReference(reference);
        return true;
    }

    public String printBibTex(String id) {
       String g= parser.tulostaBibTex(id);
       return g;
    }

    public Map<String, String> printClear(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Map<String, String>> giveAllReferences() {
        return storage.getReferences();
    }

    public boolean saveAllReferences() {
        return true;
    }

    /*
     * Returns all available reference types "article, book,..."
     */
    public List<String> getReferenceTypes() {
        return null;
    }
}

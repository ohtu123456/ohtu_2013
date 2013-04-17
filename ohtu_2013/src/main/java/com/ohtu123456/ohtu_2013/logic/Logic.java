package com.ohtu123456.ohtu_2013.logic;

import com.ohtu123456.ohtu_2013.BibtextParser.BibtextParser;
import com.ohtu123456.ohtu_2013.Storage.Storage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Logic implements LogicInterface {

    Storage storage;
    BibtextParser parser;

    public Logic() {
        storage = new Storage();
        parser = new BibtextParser();
    }

    public boolean addReference(Map<String, String> reference) {
        storage.addReference(reference);
        return true;
    }

    public String printBibTex(String id) {
        String g = parser.tulostaBibTex(id);
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
     * Fetched from DB or hard-coded
     * 
     */
    public List<String> getReferenceTypes() {
        ArrayList<String> referenceTypes = new ArrayList<String>();
        referenceTypes.add("book");
        referenceTypes.add("article");
        referenceTypes.add("inproceedings");
        return referenceTypes;
    }

    public LinkedList<String> createNewReference(String type) {
        LinkedList<String> fields = new LinkedList<String>();
        fields.add("author");
        fields.add("year");
        return fields;
    }

    public boolean validateField(String name, Object value) {
        return true;
    }
}

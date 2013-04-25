package com.ohtu123456.ohtu_2013.logic;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ohtu123456.ohtu_2013.BibtextParser.BibtextParser;
import com.ohtu123456.ohtu_2013.Storage.StorageDatabase;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.naming.directory.AttributeInUseException;

@Component
public class Logic implements LogicInterface {

    StorageDatabase dbStorage;
    BibtextParser parser;
    private final String dbPrefix = "./target/";
    private final String dbSuffix = ".sqlite";

    public Logic() {
        parser = new BibtextParser();
    }

    /**
     * Constructor for testing purposes.
     *
     * @param db injected db
     */
    public Logic(StorageDatabase db) {
        parser = new BibtextParser();
        dbStorage = db;
    }

    public boolean addReference(Map<String, String> reference) throws AttributeInUseException {
        dbStorage.addReference(reference);
        return true;
    }

    public boolean addReference(String type, Map<String, String> reference) throws AttributeInUseException {
        dbStorage.addReference(type, reference);
        return true;
    }

    public String printBibTex(Map<String, String> reference) {
        String paluu="";
        paluu=parser.printBibTex(reference);
        return paluu;
    }

    public String convertToBibtext(Map<String, String> reference) {
       String paluu="";
       paluu=parser.convertToBibtext(reference);
       return paluu;
    }

    public String printClear(Map<String, String> reference) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Map<String, String>> giveAllReferences() {
        return dbStorage.getReferences();
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
        fields.add("id");
        fields.add("author");
        fields.add("title");
        fields.add("year");
        fields.add("publisher");
        if (type.equals("article")) {
            fields.add("journal");
            fields.add("volume");
            fields.add("number");
            fields.add("pages");
            fields.add("address");
        } else if (type.equals("inproceedings")) {
            fields.add("booktitle");
            fields.add("pages");
        }
        return fields;
    }

    public boolean validateField(String name, Object value) {
        return true;
    }

    public boolean databaseExists() {
        return (dbStorage != null);
    }

    public boolean initializeDatabase(String filename) {
        dbStorage = new StorageDatabase(dbPrefix + filename + dbSuffix);
        return databaseExists();
    }

    public void addFilter(String filter) {
        dbStorage.addFilter(filter);
    }

    public void clearFilters() {
        dbStorage.clearFilters();
    }

    public List<String> getFilters() {
        return dbStorage.getFilters();
    }

    /**
     * This should give the one reference identified by ID, a bibtex format is added to it and then printed as a detailed print of 
     * a single reference
     * @param id unique id of the reference
     * @return the whole reference as a map, with an additional bibtex field added to it
     */
    public Map<String, String> giveReference(String id) {
        //Map<String, String> reference = dbStorage.getReferences(id)
        HashMap<String, String> testiReference = new HashMap<String, String>();
        testiReference.put("id", "1255");
        testiReference.put("author", "Not so good programmer");
        testiReference.put("title", "Codesmell");
        String asBibTex = parser.convertToBibtext(testiReference);
        testiReference.put("bibtex", asBibTex);
        return testiReference;
    }

    public void saveAsBibTex(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Map<String, String>> giveFilteredReferences() {
        return dbStorage.getFiltered();
    }
}

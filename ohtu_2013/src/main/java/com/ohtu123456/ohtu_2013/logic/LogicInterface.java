package com.ohtu123456.ohtu_2013.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.naming.directory.AttributeInUseException;

public interface LogicInterface {

    public boolean addReference(Map<String, String> reference) throws AttributeInUseException; 
    
    public boolean addReference(String type, Map<String,String> reference) throws AttributeInUseException;

    public String printBibTex(Map<String, String> reference);

    public String printClear(Map<String, String> reference);

    public List<Map<String, String>> giveAllReferences();
    
    public String convertToBibtext(Map<String, String> reference);
    
    public Map<String, String> giveReference(String id);

    public boolean saveAllReferences();

    public List<String> getReferenceTypes();

    /*
     * Is given a reftype as parameter ("article", "book", ...). This method
     * should then return the required fields for given reference type as an LinkedList<String>
     */
    public LinkedList<String> createNewReference(String type);
      
    /*
     * Validates reference fields asked from user, for example Year should be 4-digit integer
     */
    public boolean validateField(String name, Object value);
    
    public boolean databaseExists();
    
    public boolean initializeDatabase(String filename);
    
    public void addFilter(String filter);
    
    public void clearFilters();
    
    public List<String> getFilters();
}

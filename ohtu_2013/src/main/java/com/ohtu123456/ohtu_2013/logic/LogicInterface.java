package com.ohtu123456.ohtu_2013.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface LogicInterface {

    public boolean addReference(Map<String, String> reference) throws Exception; 

    public String printBibTex(String id);

    public Map<String, String> printClear(String id);

    public List<Map<String, String>> giveAllReferences();

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
}

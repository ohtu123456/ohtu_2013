package com.ohtu123456.ohtu_2013.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LogicInterface {

    public boolean addReference(Map<String, String> reference); //Lisää viitteen ja palauttaa tiedon siitä, onnistuiko lisäys

    public String printBibTex(String id);

    public Map<String, String> printClear(String id);

    public ArrayList<Map<String,String>> giveAllReferences();
    
    public boolean saveAllReferences();
    
    public List<String> getReferenceTypes();
}

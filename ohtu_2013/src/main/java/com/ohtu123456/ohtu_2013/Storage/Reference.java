package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;

class Reference implements ReferenceInterface {
    
    private Map<String, String> ref;
    
    public Reference(){
        ref = new HashMap<String, String>();
    }

    @Override
    public String getField(String field) {
        if(!ref.containsKey(field))
            return "";
        return ref.get(field);
    }

    @Override
    public void setField(String field, String value) {
        ref.put(field, value);
    }
    
    @Override
    public Map<String, String> getInMap(){
        return ref;
    }
}
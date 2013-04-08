package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;

class Reference {
    
    private Map<String, String> ref;
    
    public Reference(){
        ref = new HashMap<String, String>();
    }

    Reference(Map<String, String> ref) {
        this.ref = ref;
    }

    public String getField(String field) {
        if(!ref.containsKey(field))
            return "";
        return ref.get(field);
    }

    public void setField(String field, String value) {
        ref.put(field, value);
    }

    public Map<String, String> getInMap(){
        return ref;
    }
}
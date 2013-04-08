package com.ohtu123456.ohtu_2013.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heikki Kalliokoski
 */
class Storage {

    private List<ReferenceInterface> references;
    
    public Storage() {
        references = new ArrayList<ReferenceInterface>();
    }

    public ArrayList<Map<String, String>> getReferences() {
        ArrayList<Map<String, String>> referencesInMapFormat = new ArrayList<Map<String, String>>();
        for(ReferenceInterface ref: references){
            Map<String, String> refInMap = ref.getInMap();
            referencesInMapFormat.add(refInMap);
        }
        return referencesInMapFormat;
    }
}

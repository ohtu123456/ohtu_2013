package com.ohtu123456.ohtu_2013.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Heikki Kalliokoski
 */
@Component
public class Storage {

    private List<Reference> references;
    
    public Storage() {
        references = new ArrayList<Reference>();
    }

    public ArrayList<Map<String, String>> getReferences() {
        ArrayList<Map<String, String>> referencesInMapFormat = new ArrayList<Map<String, String>>();
        for(Reference ref: references){
            Map<String, String> refInMap = ref.getInMap();
            referencesInMapFormat.add(refInMap);
        }
        return referencesInMapFormat;
    }

    public void addReference(Map<String, String> ref) {
        references.add(new Reference(ref));
    }
}

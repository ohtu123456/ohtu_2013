package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StorateTest extends TestCase {
    
    Storage storage;
    
    public StorateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        this.storage = new Storage();
    }
    
    public void testGetReferencesReturnsEmptyList(){
        assertTrue(storage.getReferences().isEmpty());
    }
    
    public void testaddReferenceGrowsAmountOfReferences(){
        Map<String, String> ref = new HashMap<String, String>();
        ref.put("nimi", "ohtu");
        ref.put("arvo", "23");
        
        storage.addReference(ref);
    
        assertEquals(1, storage.getReferences().size());
    }
    
    public void testAddedElementIsInReturnedList(){
        Map<String, String> ref = new HashMap<String, String>();
        ref.put("nimi", "ohtu");
        ref.put("arvo", "23");
        
        storage.addReference(ref);
        assertTrue(storage.getReferences().contains(ref));
    }
    
}

package com.ohtu123456.ohtu_2013.Storage;

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
    
}

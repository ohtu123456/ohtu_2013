package com.ohtu123456.ohtu_2013.Storage;

import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StorageDatabaseTest extends TestCase {
    
    private StorageDatabase storageDatabase;
    
    public StorageDatabaseTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testAfterInitDatabaseFileExists(){
        String databaseFilePath = "./testDatabase";
        storageDatabase = new StorageDatabase(databaseFilePath);
        File databaseFile = new File(databaseFilePath);
        databaseFile.deleteOnExit();
        assertTrue(databaseFile.exists());
    }
    
    
}

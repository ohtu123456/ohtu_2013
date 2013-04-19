package com.ohtu123456.ohtu_2013.Storage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StorageDatabaseTest extends TestCase {
    
    private StorageDatabase storageDatabase;
    Map<String, String> articleReference;
    String testDatabasePath;
    
    public StorageDatabaseTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testDatabasePath = "./testDatabase";
        articleReference = new HashMap<String, String>();
        articleReference.put("id", "AUTH123");
        articleReference.put("author", "Matti Mallkias");
        articleReference.put("title", "Opiskelijan elämää");
        articleReference.put("journal", "Nature");
        articleReference.put("volume", "56");
        articleReference.put("number", "435");
        articleReference.put("year", "2013");
        articleReference.put("pages", "5-10");
        articleReference.put("publisher", "OtavaMedia");
        articleReference.put("address", "Suomi, Finland");
    }
    
    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
        File dbFile = new File(testDatabasePath);
        if(dbFile.exists())
            dbFile.delete();
    }
    
    public void testAfterInitDatabaseFileExists(){
        storageDatabase = new StorageDatabase(testDatabasePath);
        File databaseFile = new File(testDatabasePath);
        databaseFile.deleteOnExit();
        assertTrue(databaseFile.exists());
    }
    
    public void testEmptyListIsReturnedWithNewDatabaseAfterInit(){
        storageDatabase = new StorageDatabase(testDatabasePath);
        assertTrue(storageDatabase.getReferences().isEmpty());
    }
    
    public void testDatabaseIsNotEmptyAfterAddingAReference() throws Exception{
        storageDatabase = new StorageDatabase(testDatabasePath);
        storageDatabase.addReference("article", articleReference);
        assertFalse(storageDatabase.getReferences().isEmpty());
    }
    
    public void testNotEmptyDatabaseIsNotDestroyedInInit() throws Exception{
        storageDatabase = new StorageDatabase("./asdf");
        storageDatabase.addReference("article", articleReference);
        storageDatabase = null;
        storageDatabase = new StorageDatabase(testDatabasePath);
        assertFalse(storageDatabase.getReferences().isEmpty());
    }
    
    public void testInputEqualsOutput() throws Exception{
        storageDatabase = new StorageDatabase(testDatabasePath);
        storageDatabase.addReference("article", articleReference);
        assertEquals(storageDatabase.getReferences().get(0), articleReference);
    }
    
    
    
    
}

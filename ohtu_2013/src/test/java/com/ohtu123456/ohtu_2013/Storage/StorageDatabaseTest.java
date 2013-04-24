//package com.ohtu123456.ohtu_2013.Storage;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.naming.directory.AttributeInUseException;
//import junit.framework.TestCase;
//
///**
// *
// * @author Heikki Kalliokoski
// */
//public class StorageDatabaseTest extends TestCase {
//    
//    private StorageDatabase storageDatabase;
//    Map<String, String> articleReference;
//    String testDatabasePath;
//    
//    public StorageDatabaseTest(String testName) {
//        super(testName);
//    }
//    
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        testDatabasePath = "./testDatabase";
//        articleReference = new HashMap<String, String>();
//        articleReference.put("id", "AUTH123");
//        articleReference.put("author", "Matti Mallkias");
//        articleReference.put("title", "Opiskelijan elämää");
//        articleReference.put("journal", "Nature");
//        articleReference.put("volume", "56");
//        articleReference.put("number", "435");
//        articleReference.put("year", "2013");
//        articleReference.put("pages", "5-10");
//        articleReference.put("publisher", "OtavaMedia");
//        articleReference.put("address", "Suomi, Finland");
//    }
//    
//    @Override
//    protected void tearDown() throws Exception{
//        super.tearDown();
//        File dbFile = new File(testDatabasePath);
//        if(dbFile.exists())
//            dbFile.delete();
//    }
//
//    public void testAfterInitDatabaseFileExists(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        File databaseFile = new File(testDatabasePath);
//        databaseFile.deleteOnExit();
//        assertTrue(databaseFile.exists());
//    }
//    
//    public void testEmptyListIsReturnedWithNewDatabaseAfterInit(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        assertTrue(storageDatabase.getReferences().isEmpty());
//    }
//    
//    public void testDatabaseIsNotEmptyAfterAddingAReference() throws Exception{
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        storageDatabase.addReference("article", articleReference);
//        assertFalse(storageDatabase.getReferences().isEmpty());
//    }
//    
////    public void testNotEmptyDatabaseIsNotDestroyedInInit() throws Exception{
////        storageDatabase = new StorageDatabase("./asdf");
////        storageDatabase.addReference("article", articleReference);
////        storageDatabase = null;
////        storageDatabase = new StorageDatabase("./asdf");
////        assertFalse(storageDatabase.getReferences().isEmpty());
////    }
//    
//    public void testInputEqualsOutput() throws Exception{
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        storageDatabase.addReference("article", articleReference);
//        assertEquals(storageDatabase.getReferences().get(0), articleReference);
//    }
//    
//    public void testExceptionIsRaisedWhenAddingSameData(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        try{
//            storageDatabase.addReference("article", articleReference);
//            storageDatabase.addReference("article", articleReference);
//        } catch (AttributeInUseException e){
//            assertTrue(true);
//        }
//    }
//    
//    public void testWithNoFiltersShowFiltersReturnsEmptyString(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        assertTrue(storageDatabase.getFilters().isEmpty());
//    }
//    
//    public void testAddedFiltersShowUpInGetFilters(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        String filter1 = "Science";
//        String filter2 = "Nature";
//        ArrayList<String> filters = new ArrayList<String>();
//        filters.add(filter1);
//        filters.add(filter2);
//        storageDatabase.addFilter(filter1);
//        storageDatabase.addFilter(filter2);
//        assertEquals(filters, storageDatabase.getFilters());
//    }
//    
//    public void testAfterCleanFiltersListIsEmpty(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        String filter1 = "Science";
//        storageDatabase.addFilter(filter1);
//        storageDatabase.clearFilters();
//        assertTrue(storageDatabase.getFilters().isEmpty());
//    }
//    
//    public void testGetFilteredWithNoFiltersReturnsAll(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        populateDatabase(storageDatabase);
//        List<Map<String, String>> unfiltered = storageDatabase.getReferences();
//        List<Map<String, String>> filtered = storageDatabase.getFiltered();
//        assertEquals(filtered, unfiltered);
//    }
//    
//    public void testGetFilteredWithFilterMatchingAllReturnsAll(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        populateDatabase(storageDatabase);
//        storageDatabase.addFilter("a");
//        List<Map<String, String>> unfiltered = storageDatabase.getReferences();
//        List<Map<String, String>> filtered = storageDatabase.getFiltered();
//        assertEquals(filtered, unfiltered);
//    }
//    
//    public void testGetFilteredReturnsOnlyMatchingList(){
//        storageDatabase = new StorageDatabase(testDatabasePath);
//        populateDatabase(storageDatabase);
//        storageDatabase.addFilter("Opiskelija");
//        List<Map<String, String>> wantedList = new ArrayList<Map<String, String>>();
//        Map<String, String> article = new HashMap<String, String>();
//        article.put("id", "OPIEL");
//        article.put("author", "Matti Mallkias");
//        article.put("title", "Opiskelijan elämää");
//        article.put("journal", "Nature");
//        article.put("volume", "56");
//        article.put("number", "435");
//        article.put("year", "2013");
//        article.put("pages", "5-10");
//        article.put("publisher", "OtavaMedia");
//        article.put("address", "Suomi, Finland");
//        wantedList.add(article);
//        List<Map<String, String>> filtered = storageDatabase.getFiltered();
//        assertEquals(filtered, wantedList);
//    }
//
//    private void populateDatabase(StorageDatabase storageDatabase) {
//        Map<String, String> article = new HashMap<String, String>();
//        article.put("id", "OPIEL");
//        article.put("author", "Matti Mallkias");
//        article.put("title", "Opiskelijan elämää");
//        article.put("journal", "Nature");
//        article.put("volume", "56");
//        article.put("number", "435");
//        article.put("year", "2013");
//        article.put("pages", "5-10");
//        article.put("publisher", "OtavaMedia");
//        article.put("address", "Suomi, Finland");
//        Map<String, String> book = new HashMap<String, String>();
//        book.put("id", "ÄÄRKERÄÄR");
//        book.put("title", "Ääretön kertaa ääretön");
//        book.put("author", "Mike Flynn");
//        book.put("year", "2005");
//        book.put("publisher", "Karisto");
//        try {
//            storageDatabase.addReference("article", article);
//            storageDatabase.addReference("book", book);
//        } catch (AttributeInUseException ex) {
//            Logger.getLogger(StorageDatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//    
//    
//}

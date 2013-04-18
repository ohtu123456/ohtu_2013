package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class InproceedingTest extends TestCase {
    
    private Map<String, String> reference;
    private Inproceeding inproceeding;
    
    public InproceedingTest(String testName) {
        super(testName);
        
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        reference = new HashMap<String, String>();
        reference.put("id", "AUTH123");
        reference.put("author", "Matti Mallkias");
        reference.put("title", "Opiskelijan elämää");
        reference.put("booktitle", "Jotain");
        reference.put("year", "2013");
        reference.put("pages", "5-10");
        reference.put("publisher", "OtavaMedia");
        reference.put("address", "Suomi, Finland");
    }
    
    public void testIdIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getShortId(), reference.get("id"));
    }
    
    public void testAuthorIsAssignedCorrecly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getAuthor(), reference.get("author"));
    }
    
    public void testTitleIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getTitle(), reference.get("title"));
    }
    
    public void testBookTitleIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getBooktitle(), reference.get("booktitle"));
    }
    
    public void testYearIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getPublishYear(), reference.get("year"));
    }
    
    public void testPagesIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getPages(), reference.get("pages"));
    }
    
    public void testPublisherIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getPublisher(), reference.get("publisher"));
    }
    
    public void testAddressIsAssignedCorrectly(){
        inproceeding = new Inproceeding(reference);
        assertEquals(inproceeding.getAddress(), reference.get("address"));
    }
    
    public void testMissingAuthorIsReplacedWithEmptyString(){
        reference.remove("author");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getAuthor().isEmpty());
    }
    
    public void testMissingTitleIsReplacedWithEmptyString(){
        reference.remove("title");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getTitle().isEmpty());
    }
    
    public void testMissingBookTitleIsReplacedWithEmptyString(){
        reference.remove("booktitle");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getBooktitle().isEmpty());
    }
    
    public void testMissingYearIsReplacedWithEmptyString(){
        reference.remove("year");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getPublishYear().isEmpty());
    }
    
    public void testMissingPagesIsReplacedWithEmptyString(){
        reference.remove("pages");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getPages().isEmpty());
    }
    
    public void testMissingPublisherIsReplacedWithEmptyString(){
        reference.remove("publisher");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getPublisher().isEmpty());
    }
    
    public void testMissingAddressIsReplacedWithEmptyString(){
        reference.remove("address");
        inproceeding = new Inproceeding(reference);
        assertTrue(inproceeding.getAddress().isEmpty());
    }
 
    
}
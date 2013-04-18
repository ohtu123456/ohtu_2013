package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class BookTest extends TestCase {
    
    private Book book;
    private Map<String, String> reference;
    
    public BookTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        reference = new HashMap<String, String>();
        reference.put("id", "SOMEBOOK1");
        reference.put("author", "Maija Mallikas");
        reference.put("title", "Maijan opas matematiikkaan");
        reference.put("year", "2013");
        reference.put("publisher", "AlmaMedia");
    }

    public void testIdIsAssignedCorrectly(){
        book = new Book(reference);
        assertEquals(book.getShortId(), reference.get("id"));
    }
    
    public void testAuthorIsAssignedCorrectly(){
        book = new Book(reference);
        assertEquals(book.getAuthor(), reference.get("author"));
    }
    
    public void testTitleIsAssignedCorrectly(){
        book = new Book(reference);
        assertEquals(book.getTitle(), reference.get("title"));
    }
    
    public void testYearIsAssignedCorrectly(){
        book = new Book(reference);
        assertEquals(book.getPublishYear(), reference.get("year"));
    }
    
    public void testPublisherIsAssignedCorrectly(){
        book = new Book(reference);
        assertEquals(book.getPublisher(), reference.get("publisher"));
    }
    
    public void testMissingAuthorIsReplacedWithEmptyString(){
        reference.remove("author");
        book = new Book(reference);
        assertTrue(book.getAuthor().isEmpty());
    }
    
    public void testMissingTitleIsReplacedWithEmptyString(){
        reference.remove("title");
        book = new Book(reference);
        assertTrue(book.getTitle().isEmpty());
    }
    
    public void testMissingYearIsReplacedWithEmptyString(){
        reference.remove("year");
        book = new Book(reference);
        assertTrue(book.getPublishYear().isEmpty());
    }
    
    public void testMissingPublisherIsReplacedWithEmptyString(){
        reference.remove("publisher");
        book = new Book(reference);
        assertTrue(book.getPublisher().isEmpty());
    }
}

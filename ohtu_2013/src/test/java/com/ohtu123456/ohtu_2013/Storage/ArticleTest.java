package com.ohtu123456.ohtu_2013.Storage;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class ArticleTest extends TestCase {
    
    private Map<String, String> reference;
    private Article article;
    
    public ArticleTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        reference = new HashMap<String, String>();
        reference.put("id", "AUTH123");
        reference.put("author", "Matti Mallkias");
        reference.put("title", "Opiskelijan elämää");
        reference.put("journal", "Nature");
        reference.put("volume", "56");
        reference.put("number", "435");
        reference.put("year", "2013");
        reference.put("pages", "5-10");
        reference.put("publisher", "OtavaMedia");
        reference.put("address", "Suomi, Finland");
    }
    
    public void testIdIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getShortId(), reference.get("id"));
    }
    
    public void testAuthorIsAssignedCorrecly(){
        article = new Article(reference);
        assertEquals(article.getAuthor(), reference.get("author"));
    }
    
    public void testTitleIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getTitle(), reference.get("title"));
    }

    public void testJournalIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getJournal(), reference.get("journal"));
    }
    
    public void testVolumeIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getVolume(), reference.get("volume"));
    }
    
    public void testNumberIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getNumber(), reference.get("number"));
    }
    
    public void testYearIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getPublishYear(), reference.get("year"));
    }
    
    public void testPagesIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getPages(), reference.get("pages"));
    }
    
    public void testPublisherIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getPublisher(), reference.get("publisher"));
    }
    
    public void testAddressIsAssignedCorrectly(){
        article = new Article(reference);
        assertEquals(article.getAddress(), reference.get("address"));
    }
    
    public void testMissingAuthorIsReplacedWithEmptyString(){
        reference.remove("author");
        article = new Article(reference);
        assertTrue(article.getAuthor().isEmpty());
    }
    
    public void testMissingTitleIsReplacedWithEmptyString(){
        reference.remove("title");
        article = new Article(reference);
        assertTrue(article.getTitle().isEmpty());
    }
    
    public void testMissingVolumeIsReplacedWithEmptyString(){
        reference.remove("journal");
        article = new Article(reference);
        assertTrue(article.getJournal().isEmpty());
    }
    
    public void testMissingNumberIsReplacedWithEmptyString(){
        reference.remove("number");
        article = new Article(reference);
        assertTrue(article.getNumber().isEmpty());
    }
    
    public void testMissingYearIsReplacedWithEmptyString(){
        reference.remove("year");
        article = new Article(reference);
        assertTrue(article.getPublishYear().isEmpty());
    }
    
    public void testMissingPagesIsReplacedWithEmptyString(){
        reference.remove("pages");
        article = new Article(reference);
        assertTrue(article.getPages().isEmpty());
    }
    
    public void testMissingPublisherIsReplacedWithEmptyString(){
        reference.remove("publisher");
        article = new Article(reference);
        assertTrue(article.getPublisher().isEmpty());
    }
    
    public void testMissingAddressIsReplacedWithEmptyString(){
        reference.remove("address");
        article = new Article(reference);
        assertTrue(article.getAddress().isEmpty());
    }
}
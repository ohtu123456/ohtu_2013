package com.ohtu123456.ohtu_2013.logic;

import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Heikki Kalliokoski
 */
public class LogicTest extends TestCase {
    
    private Logic logic;
    
    public LogicTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        logic = new Logic();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateNewReferenceReturnsCorrectFieldsForABook(){
        LinkedList<String> expectedFields = new LinkedList<String>();
        expectedFields.add("id");
        expectedFields.add("author");
        expectedFields.add("title");
        expectedFields.add("year");
        expectedFields.add("publisher");
        List<String> fields = logic.createNewReference("book");
        assertEquals(fields, expectedFields);
    }
    
    public void testCreateNewReferenceReturnsCorrectFieldsForAArticle(){
        LinkedList<String> expectedFields = new LinkedList<String>();
        expectedFields.add("id");
        expectedFields.add("author");
        expectedFields.add("title");
        expectedFields.add("year");
        expectedFields.add("publisher");
        expectedFields.add("journal");
        expectedFields.add("volume");
        expectedFields.add("number");
        expectedFields.add("pages");
        expectedFields.add("address");
        List<String> fields = logic.createNewReference("article");
        assertEquals(fields, expectedFields);
    }
    
    public void testCreateNewReferenceReturnsCorrectFieldsForAInproceedings(){
        LinkedList<String> expectedFields = new LinkedList<String>();
        expectedFields.add("id");
        expectedFields.add("author");
        expectedFields.add("title");
        expectedFields.add("year");
        expectedFields.add("publisher");
        expectedFields.add("booktitle");
        expectedFields.add("pages");
        List<String> fields = logic.createNewReference("inproceedings");
        assertEquals(fields, expectedFields);
    }
}

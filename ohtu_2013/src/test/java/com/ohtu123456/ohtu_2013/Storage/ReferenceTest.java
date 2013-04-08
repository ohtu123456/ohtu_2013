package com.ohtu123456.ohtu_2013.Storage;

import junit.framework.TestCase;

/**Storage
 *
 * @author Heikki Kalliokoski
 */
public class ReferenceTest extends TestCase {
    
    private Reference ref;
    
    public ReferenceTest(String testName) {
        super(testName);
        ref = new Reference();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testGetFieldReturnsEmptyStringIfFieldNotDefined(){
        assertTrue(ref.getField("Olematon").isEmpty());
    }
    
    public void testSetFieldCanInsertInformation(){
        ref.setField("Nimi", "Testi");
        assertEquals("Testi", ref.getField("Nimi"));
    }
    
}

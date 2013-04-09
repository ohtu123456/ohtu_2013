package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.ArrayList;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StubPrinter implements Printer{
    
    ArrayList<String> lines;
    
    public StubPrinter(){
        this.lines = new ArrayList<String>();
    }

    public void println(String message) {
        lines.add(message);
    }
    
    public ArrayList<String> getPrints(){
        return lines;
    }
    
}

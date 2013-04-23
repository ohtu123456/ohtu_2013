package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StubIO implements IO{
    
    private List<String> output;
    private List<String> input;
    private Iterator<String> outputIterator;
    
    public StubIO(String... args){
        output = new ArrayList<String>();
        input = new ArrayList<String>();
        for(String arg: args){
            output.add(arg);
        }
        outputIterator = output.iterator();
    }

    public void println(String line) {
        input.add(line);
    }
    
    public List<String> getInput()  {
        return input;
    }

    public String nextLine() {
        return outputIterator.next();
    }
    
}

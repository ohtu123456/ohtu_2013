package com.ohtu123456.ohtu_2013.UserInterface;

/**
 *
 * @author Heikki Kalliokoski
 */
public class StubReader implements Reader{
    
    private String[] lines;
    private int i;
    
    public StubReader(String... values){
        this.lines = values;
    }

    public String nextLine() {
        if (i < lines.length) {
            return lines[i++];
        }
        return "";}

    public int nextInt() {
        return Integer.parseInt(lines[i++]);
   }
    
}

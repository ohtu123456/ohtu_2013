package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.Scanner;

/**
 *
 * @author Heikki Kalliokoski
 */
public class ConsoleIO implements IO {
    
    private Scanner reader;
    
    public ConsoleIO(){
        reader = new Scanner(System.in);
    }

    public void println(String line) {
        System.out.println(line);
    }

    public String nextLine() {
        return reader.nextLine();
    }
    
}

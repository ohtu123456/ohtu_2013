package com.ohtu123456.ohtu_2013.UserInterface;

/**
 *
 * @author Heikki Kalliokoski
 */
public class PrinterSystemOut implements Printer{

    public void println(String message) {
        System.out.println(message);
    }
    
}

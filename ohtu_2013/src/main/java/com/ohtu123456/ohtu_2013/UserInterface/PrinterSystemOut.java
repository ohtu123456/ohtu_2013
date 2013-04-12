package com.ohtu123456.ohtu_2013.UserInterface;

import org.springframework.stereotype.Component;

/**
 *
 * @author Heikki Kalliokoski
 */
@Component
public class PrinterSystemOut implements Printer{

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }
    
}

package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Heikki Kalliokoski
 */
@Component
public class ReaderScanner implements Reader{
    
    private Scanner sc;
    
    public ReaderScanner(){
        sc = new Scanner(System.in);
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public int nextInt() {
        int nextInt = sc.nextInt();
        sc.nextLine();
        return nextInt;
    }
    
}

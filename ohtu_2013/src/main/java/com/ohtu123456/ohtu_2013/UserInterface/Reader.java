package com.ohtu123456.ohtu_2013.UserInterface;

import org.springframework.stereotype.Component;

/**
 *
 * @author Heikki Kalliokoski
 */
@Component
public interface Reader {
    public String nextLine();
    public int nextInt();
}

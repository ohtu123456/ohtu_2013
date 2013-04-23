package com.ohtu123456.ohtu_2013.logic;

import com.ohtu123456.ohtu_2013.UserInterface.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {


    public static void main(String[] args) throws Exception {

        UI ui = new UI();
        ui.initialize();
        ui.start();
    }
}

package com.ohtu123456.ohtu_2013.logic;

import com.ohtu123456.ohtu_2013.UserInterface.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    private static final ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

    public static void main(String[] args) throws Exception {

        UI ui = ctx.getBean(UI.class);
        ui.initialize();
        ui.start();
    }
}

package com.ohtu123456.ohtu_2013;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    private static final ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

    public static void main(String[] args) {

        UI ui = ctx.getBean(UI.class);
        ui.start();
    }
}

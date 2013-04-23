package com.ohtu123456.ohtu_2013.UserInterface;

/**
 * A single user chosen selection, might be a "boolean" value without an
 * argument, or wuth one.
 *
 * @author Leif
 */
public class Selection {

    private String name; //Option name ('add','print'...)
    private boolean hasArgument;
    private String argument;

    public Selection(String name) {
        this.name = name;
        hasArgument = false;
    }

    public Selection(String name, String argument) {
        this.name = name;
        this.argument = argument;
        hasArgument = true;
    }

    public String getName() {
        return name;
    }

    public boolean HasArgument() {
        return hasArgument;
    }

    public String getArgument() {
        return argument;
    }
}

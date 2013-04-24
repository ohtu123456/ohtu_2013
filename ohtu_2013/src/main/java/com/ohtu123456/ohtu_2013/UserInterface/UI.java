package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.directory.AttributeInUseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Preliminary UI using the apache commons CLI library, might get upgraded to a
 * graphical one
 *
 * @author Leif Setälä
 */
@Component
public class UI {

    @Autowired
    private LogicInterface logic;
    //--------------------------
    @Autowired
    private MenuHandler menuHandler;
    private Options mainMenu;
    private Options referenceMenu;
    private HelpFormatter help;
    private IO io;
    //--------------------------
    private ArrayList<String> possibleReferences;
    private boolean exit; //boolen for user input 'quit'

    public UI() {
        help = new HelpFormatter();
        io = new ConsoleIO();
    }

    public UI(IO io, LogicInterface logic) {
        this.io = io;
        this.logic = logic;
        menuHandler = new MenuHandler();
        help = new HelpFormatter();
    }

    public void initialize() {
        exit = false;
        possibleReferences = (ArrayList) logic.getReferenceTypes();
        menuHandler.populateMenuItems(possibleReferences);
        initializeDatabase();
        start();
    }

    public void start() {
        if (mainMenu == null) {
            mainMenu = menuHandler.getMainMenu();
        }
        if (referenceMenu == null) {
            referenceMenu = menuHandler.getReferenceTypesMenu();
        }
        ArrayList<Selection> userInput = getDialog(mainMenu);
        processMenuInput(userInput);
    }

    private void processMenuInput(ArrayList<Selection> userInput) {
        for (Selection selection : userInput) {
            if (selection.getName().equals("quit")) {
                exit = true;//Just break from execution loop, System.exit caused too weird testing errors
            } else if (selection.getName().equals("menu")) {
                start();
            } else if (selection.getName().equals("add")) {
                processMenuInput(getDialog(referenceMenu));
            } else if (selection.getName().equals("clearfilters")) {
                logic.clearFilters();
            } else if (selection.getName().equals("filter")) {
                logic.addFilter(selection.getArgument());
            } else if (selection.getName().equals("showfilters")) {
                printFilters();
            } else if (selection.getName().equals("print")) {
                if (selection.HasArgument()) {
                    printDetailed(selection.getArgument());
                } else {
                    printAllReferences();
                }
            } else {//Input was selectin a reference type
                addReference(selection.getName(), logic.createNewReference(selection.getName()));
            }
        }
        if (!exit) {
            start();
        }
    }

    /**
     * Asks user for input based on given options and sends them to MenuHandler
     * for processing
     *
     * @param opt possible user options
     * @return an ArrayList containing user selections
     */
    private ArrayList<Selection> getDialog(Options opt) {
        while (true) {
            try {
                help.printHelp(" ", opt, false);
                String input = io.nextLine();
                //No input, print help again
                if (input.equals("")) {
                    getDialog(opt);
                }
                return menuHandler.getUserInput(input, opt);
            } catch (ParseException ex) {
                io.println("Parse exception: " + ex.getMessage());
                getDialog(opt);
            }
        }
    }

    /**
     * Asks the user for reference fields, validates them, and sends it to logic
     * for storage
     *
     * @param type Reference type (article, book,...)
     * @param fields Required fields for this type of reference
     */
    private void addReference(String type, List<String> fields) {
        LinkedHashMap<String, String> newReference = new LinkedHashMap<String, String>();
        io.println("Please fill in the following fields.");
        for (int i = 0; i < fields.size();) {
            String input;
            io.println(fields.get(i) + ":");
            input = io.nextLine();
            if (logic.validateField(fields.get(i), input)) {
                newReference.put(fields.get(i), input);
                i++;
            } else {
                io.println("Invalid value.");
            }
        }
        try {
            logic.addReference(type, newReference);
            io.println("New reference added");
        } catch (AttributeInUseException e) {
            io.println("Couldn't add new reference: " + e.getMessage());
        }
    }

    private void printAllReferences() {
        List<Map<String, String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> ref : allReferences) {
            io.println("{ID: " + ref.get("id") + ", "
                    + " AUTHOR: " + ref.get("author") + ", "
                    + " TITLE: " + ref.get("title") + ", "
                    + " YEAR: " + ref.get("year") + "}.");
            io.println("-------------------");
        }
        start();
    }

    private void printDetailed(String id) {
        Map<String, String> reference = logic.giveReference(id);
        for (String s : reference.keySet()) {
            io.println(s + " - " + reference.get(s));
        }
        io.println("\nAs BibTex format: \n\n");
        io.println(reference.get("bibtex"));
    }

    private void printFilters() {
        io.println("Filters in use: ");
        for (String filter : logic.getFilters()) {
            io.println(filter);
        }
        start();
    }
    
        private boolean initializeDatabase() {
        if (logic.databaseExists()) {
            return true;
        }
        io.println("No open database connection. Give the name of the database: ");
        return logic.initializeDatabase(io.nextLine());
    }
}
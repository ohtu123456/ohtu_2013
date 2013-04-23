package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.Logic;
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
    private Logic logic;
    //--------------------------
    @Autowired
    private MenuHandler menuHandler;
    private Options mainMenu;
    private Options referenceMenu;
    private HelpFormatter help;
    private IO io;
    //--------------------------
    private ArrayList<String> possibleReferences;

    public UI() {
        help = new HelpFormatter();
    }

    public void initialize() {
        possibleReferences = (ArrayList) logic.getReferenceTypes();
        io = new ConsoleIO();
        menuHandler.populateMenuItems(possibleReferences);
        start();
    }

    public void start() {
        if (mainMenu == null) {
            mainMenu = menuHandler.getMainMenu();
        }
        if (referenceMenu == null)  {
            referenceMenu = menuHandler.getReferenceTypesMenu();
        }
        ArrayList<Selection> userInput = getDialog(mainMenu);
        processMainMenuInput(userInput);
    }

    private void processMainMenuInput(ArrayList<Selection> userInput) {
        if (!initializeDatabase())    {
            System.out.println("Could not create database");
            start();
        }
        for (Selection selection : userInput) {
            if (selection.getName().equals("add")) {
                processReferenceMenuInput(getDialog(referenceMenu));
            }
            if (selection.getName().equals("filter")) {
                logic.addFilter(selection.getArgument());
            }
            if (selection.getName().equals("print")) {
                printAllReferences();
            }
            if (selection.getName().equals("clearfilters")) {
                logic.clearFilters();
            }
            if (selection.getName().equals("showfilters"))  {
                printFilters();
            }
        }
    }

    private void printFilters() {
        System.out.println("Filters in use: ");
        for (String filter : logic.getFilters())    {
            System.out.println(filter);
        }
        start();
    }
    
    private void processReferenceMenuInput(ArrayList<Selection> userInput) {
        for (Selection selection : userInput) {
            addReference(selection.getName(), logic.createNewReference(selection.getName()));
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
                help.printHelp(" ", opt,false);
                String input = io.nextLine();
                //No input, print help again
                if (input.equals("")) {
                    getDialog(opt);
                }
                return menuHandler.getUserInput(input, opt);
            } catch (ParseException ex) {
                System.out.println("Parse exception: " + ex.getMessage());
                getDialog(opt);
            }
        }
    }

    private boolean initializeDatabase() {
        if (logic.databaseExists()) {
            return true;
        }
        System.out.println("No database exists. Give a new file name: ");
        return logic.initializeDatabase(io.nextLine());
    }

    private void printAllReferences() {
        List<Map<String, String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> ref : allReferences) {
            for (String s : ref.keySet()) {
                io.println(s + " - " + ref.get(s));
            }
            io.println("-------------------");
        }
        //String h=logic.printBibTex("");
        start();
    }

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
        start();
    }
}
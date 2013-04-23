package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.stereotype.Component;

/**
 * Handles menu generation and parsing of user input (in menus)
 *
 * @author Leif
 */

@Component
public class MenuHandler {
   
    private Options mainMenu;
    private Options referenceTypesMenu;
    private CommandLineParser parser;
    private ArrayList<String> referenceTypes;
    private ArrayList<Selection> userSelections;

    public MenuHandler() {
        parser = new BasicParser();
    }

    public void populateMenuItems(ArrayList<String> refTypes) {
        mainMenu = new Options();
        mainMenu.addOption("add", false, "Add Reference.");
        mainMenu.addOption("filter", true, "Add a search filter");
        mainMenu.addOption("print", false, "Print references according to given filters.");
        mainMenu.addOption("quit", false, "Quit program.");
        //--------------------------
        referenceTypesMenu = new Options();
        referenceTypes = refTypes;
        Iterator<String> it = referenceTypes.iterator();
        while (it.hasNext()) {
            String ref = it.next();
            referenceTypesMenu.addOption(ref, false, "Add reference of type: " + ref);
        }
        referenceTypesMenu.addOption("menu", false, "Return to main menu");
        referenceTypesMenu.addOption("quit", false, "Quit program");
    }

    public ArrayList<Selection> getUserInput(String input, Options options) throws ParseException {
        String[] args = input.split(" ");
        CommandLine cmd = parser.parse(options, args);
        ArrayList<Selection> userInput;

        String mainOptions = "addfilterprintquit";
        //Contains main menu selections
        userSelections = new ArrayList<Selection>();
        if (mainOptions.contains(cmd.getOptions()[0].getOpt())) {
            userInput = processMainMenuInput(cmd);
        } else {
            userInput = processReferenceTypesInput(cmd);
        }

        return userInput;
    }

    private ArrayList<Selection> processMainMenuInput(CommandLine cmd) {
        if (cmd.hasOption("quit")) {
            System.exit(0);
        }
        if (cmd.hasOption("add")) {
            userSelections.add(new Selection("add"));
            return userSelections;
        }
        if (cmd.hasOption("filter")) {
            userSelections.add(new Selection("filter", cmd.getOptionValue("filter")));
        }
        if (cmd.hasOption("print")) {
            userSelections.add(new Selection("print"));
        }
        return userSelections;
    }

    private ArrayList<Selection> processReferenceTypesInput(CommandLine cmd) {
        for (Option opt : cmd.getOptions()) {
            userSelections.add(new Selection(opt.getOpt()));
        }
        return userSelections;
    }

    public Options getMainMenu() {
        return mainMenu;
    }

    public Options getReferenceTypesMenu() {
        return referenceTypesMenu;
    }
}

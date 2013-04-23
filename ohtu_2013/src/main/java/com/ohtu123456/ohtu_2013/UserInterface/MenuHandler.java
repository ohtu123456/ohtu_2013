package com.ohtu123456.ohtu_2013.UserInterface;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
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
        buildMainMenu();
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
    
    private void buildMainMenu()    {
        mainMenu = new Options();
        Option add = new Option("add","Add Reference.");
        Option print = new Option("print", "Print references.");
        Option showfilters = new Option("showfilters", "Show set filters.");
        Option clearfilters = new Option("clearfilters", "Clear all filters.");
        Option quit = new Option("quit", "Quit program.");
        Option filter = OptionBuilder.withArgName("filter")
                                     .hasArg()
                                     .withDescription("add a new filter")
                                     .create("filter");
        mainMenu.addOption(add);
        mainMenu.addOption(print);
        mainMenu.addOption(showfilters);
        mainMenu.addOption(clearfilters);
        mainMenu.addOption(filter);
        mainMenu.addOption(quit);
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
        if (cmd.hasOption("showfilters")) {
            userSelections.add(new Selection("showfilters"));
            return userSelections;
        }
        if (cmd.hasOption("clearfilters")) {
            userSelections.add(new Selection("clearfilters"));
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

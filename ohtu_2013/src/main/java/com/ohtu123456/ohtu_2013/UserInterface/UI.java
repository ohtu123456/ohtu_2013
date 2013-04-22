package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.Logic;
import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.naming.directory.AttributeInUseException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
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
    private CommandLineParser parser;
    private Options menu;
    private Options referenceOptions;
    private HelpFormatter help;
    private IO io;
    //--------------------------
    private ArrayList<String> possibleReferences;

    public UI() {
        io = new ConsoleIO();
    }

    public UI(IO io, LogicInterface logic) {
        this.io = io;
        this.logic = logic;
        logic.initializeDatabase("testidb");
    }

    public void initialize() {
        menu = getMenuOptions();
        parser = new BasicParser();
        help = new HelpFormatter();
        start();
    }

    public void start() {
        if (menu == null) {
            menu = getMenuOptions();
        }
        CommandLine cmd = getDialog(menu);
        processMenuInput(cmd);
    }

    private Options getMenuOptions() {
        Options opt = new Options();
        opt.addOption("add", false, "Add Reference.");
        opt.addOption("print", false, "Print all references.");
        opt.addOption("save", false, "Save all references.");
        opt.addOption("quit", false, "Quit program.");
        return opt;
    }

    private Options getReferenceOptions() {
        if (referenceOptions == null) {
            possibleReferences = (ArrayList) logic.getReferenceTypes();
        }
        Options opt = new Options();
        Iterator<String> it = possibleReferences.iterator();
        while (it.hasNext()) {
            String ref = it.next();
            opt.addOption(ref, false, "Add reference of type: " + ref);
        }
        opt.addOption("menu", false, "Return to main menu");
        opt.addOption("quit", false, "Quit program");
        return opt;
    }

    /**
     * Asks user for input based on given options
     *
     * @param opt possible user options
     * @return the command line containing user set parameters
     */
    private CommandLine getDialog(Options opt) {
        while (true) {
            help.printHelp(" ", opt, true);
            String input = io.nextLine();
            if (input.equals("")) {
                getDialog(opt);
            }
            String[] args = input.split(" ");
            try {
                CommandLine cmd = parser.parse(opt, args);
                return cmd;
            } catch (ParseException e) {
                io.println("Parsing caused exception: " + e.getMessage());
            }
        }
    }

    private void processMenuInput(CommandLine cmd) {
        if (cmd.hasOption("quit")) {
            System.exit(0);
        } else if (cmd.hasOption("add")) {
            if (initializeDatabase()) {
                addReference();
            } else {
                System.out.println("Database could not be created.");
                start();
            }
        } else if (cmd.hasOption("print")) {
            printAllReferences();
        } else {
            start();
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
        if (!logic.databaseExists()) {
            initializeDatabase();
        }
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

    private void addReference() {
        LinkedList<String> requiredFields = new LinkedList<String>();
        String referenceType = "";
        referenceOptions = getReferenceOptions();
        CommandLine cmd = getDialog(referenceOptions);
        if (cmd.hasOption("quit")) {
            System.exit(0);
        } else if (cmd.hasOption("return")) {
            start();
        } else {
            for (String s : possibleReferences) {
                if (cmd.hasOption(s)) {
                    requiredFields = logic.createNewReference(s);
                    referenceType = s;
                    break;
                }
            }
            addReference(referenceType, requiredFields);
        }
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
            io.println("Couldn't add new reference");
        }
        start();
    }

    private void addReference(List<String> fields) {
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
            logic.addReference(newReference);
            io.println("New reference added");
        } catch (AttributeInUseException e) {
            io.println("Couldn't add new reference, reason: " + e.getMessage());
        }
        start();
    }
}
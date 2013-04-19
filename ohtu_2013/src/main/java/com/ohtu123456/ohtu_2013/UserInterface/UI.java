package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
<<<<<<< HEAD
import javax.naming.directory.AttributeInUseException;
=======
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> leqdevel
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
    private Scanner scanner;
    //--------------------------
    private boolean saved;
    private ArrayList<String> possibleReferences;
    private InputStream input;
    private PrintStream output;

    public UI() {
    }

<<<<<<< HEAD
    public void initialize(){
=======
    public UI(LogicInterface l) {
        this.logic = l;
    }

    public void initialize() {
        input = System.in;
        output = System.out;
>>>>>>> leqdevel
        saved = false;
        scanner = new Scanner(input);
        menu = getMenuOptions();
        parser = new BasicParser();
        help = new HelpFormatter();
    }

    /**
     * Testing method for easyb, enables hard-coding of user input
     *
     * @param input String containing user input, separated with \n
     */
    public void setInput(String userInput) {
        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
    }

    /**
     * Set program to output to a file, so that easyB can check for program
     * output
     */
    public void setOutput(String file) {
        try {
            output = new PrintStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start(){
        if (menu == null) {
            menu = getMenuOptions();
        }
        CommandLine cmd = getDialog(menu);
        processMenuInput(cmd);
    }

    private Options getMenuOptions() {
        //TODO: Lisättävä johonkin kohtin, ennen uusien viitteiden lisäämistä kysely, mihin tiedostoon tallennetaan
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

    private CommandLine getDialog(Options opt) {
        while (true) {
            help.printHelp(" ", opt, true);
            String uinput = scanner.nextLine();
            if (uinput.equals("")) {
                getDialog(opt);
            }
            String[] args = uinput.split(" ");
            try {
                CommandLine cmd = parser.parse(opt, args);
                return cmd;
            } catch (ParseException e) {
                output.println("Parsing caused exception: " + e.getMessage());
            }
        }
    }

    private void processMenuInput(CommandLine cmd){
        if (cmd.hasOption("quit")) {
            quit();
        } else if (cmd.hasOption("add")) {
            addReference();
        } else if (cmd.hasOption("print")) {
            printAllReferences();
        } else if (cmd.hasOption("save")) {
            saved = logic.saveAllReferences();
            if (saved) {
                output.println("All references saved.");
            } else {
                output.println("Save failed.");
            }
            start();
        } else {
            start();
        }
    }

    private void printAllReferences(){
        List<Map<String, String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> ref : allReferences) {
            for (String s : ref.keySet()) {
                output.println(s + " - " + ref.get(s));
            }
            output.println("-------------------");
        }
        String h=logic.printBibTex("");
        start();
    }

    private void addReference(){
        LinkedList<String> requiredFields = new LinkedList<String>();
        referenceOptions = getReferenceOptions();
        CommandLine cmd = getDialog(referenceOptions);
        if (cmd.hasOption("quit")) {
            quit();
        } else if (cmd.hasOption("return")) {
            start();
        } else {
            for (String s : possibleReferences) {
                if (cmd.hasOption(s)) {
                    requiredFields = logic.createNewReference(s);
                    break;
                }
            }
            addReference(requiredFields);
        }
    }

    private void addReference(List<String> fields){
        LinkedHashMap<String, String> newReference = new LinkedHashMap<String, String>();
        output.println("Please fill in the following fields.");
        for (int i = 0; i < fields.size();) {
            String uinput;
            output.println(fields.get(i) + ":");
            uinput = scanner.nextLine();
            if (logic.validateField(fields.get(i), uinput)) {
                newReference.put(fields.get(i), uinput);
                i++;
            } else {
                output.println("Invalid value.");
            }
        }
<<<<<<< HEAD
        try{
            logic.addReference(newReference);
            System.out.println("New reference added");
        } catch (AttributeInUseException e){
            System.out.println("Couldn't add new reference");
=======
        if (logic.addReference(newReference)) {
            output.println("New reference added");
        } else {
            output.println("Couldn't add new reference");
>>>>>>> leqdevel
        }
        start();
    }

    /**
     * Quits and ensures that all changes are saved before doing so.
     */
    private void quit(){
        if (!saved) {
            boolean success = logic.saveAllReferences();
            if (!success) {
                output.println("Could not save references.");
                output.println("quit anyway? (y/n)");
                try {
                    String answer = scanner.nextLine();
                    if (answer.equals("y")) {
                        System.exit(0);
                    } else if (answer.equals("n")) {
                        start();
                    } else {
                        throw new InputMismatchException("Invalid argument");
                    }
                } catch (InputMismatchException e) {
                    output.println(e.getMessage());
                    quit();
                }
            } else {
                output.println("All references saved");
            }
        }
        System.exit(0);
    }
}
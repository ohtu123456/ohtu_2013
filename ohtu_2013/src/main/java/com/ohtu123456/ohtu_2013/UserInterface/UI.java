package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
    private Scanner scanner;
    //--------------------------
    private boolean saved;
    private ArrayList<String> possibleReferences;

    public UI() {
    }

    public void initialize(){
        saved = false;
        scanner = new Scanner(System.in);
        menu = getMenuOptions();
        parser = new BasicParser();
        help = new HelpFormatter();
        start();
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

    public CommandLine getDialog(Options opt) {
        while (true) {
            help.printHelp(" ", opt, true);
            String input = scanner.nextLine();
            if (input.equals("")) {
                getDialog(opt);
            }
            String[] args = input.split(" ");
            try {
                CommandLine cmd = parser.parse(opt, args);
                return cmd;
            } catch (ParseException e) {
                System.out.println("Parsing caused exception: " + e.getMessage());
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
        } else {
            start();
        }
    }

    private void printAllReferences(){
        List<Map<String, String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> ref : allReferences) {
            for (String s : ref.keySet()) {
                System.out.println(s + " - " + ref.get(s));
            }
            System.out.println("-------------------");
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
        System.out.println("Please fill in the following fields.");
        for (int i = 0; i < fields.size();) {
            String input;
            System.out.println(fields.get(i) + ":");
            input = scanner.nextLine();
            if (logic.validateField(fields.get(i), input)) {
                newReference.put(fields.get(i), input);
                i++;
            } else {
                System.out.println("Invalid value.");
            }
        }
        try{
            logic.addReference(newReference);
            System.out.println("New reference added");
        } catch (AttributeInUseException e){
            System.out.println("Couldn't add new reference");
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
                System.out.println("Could not save references.");
                System.out.println("quit anyway? (y/n)");
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
                    System.out.println(e.getMessage());
                    quit();
                }
            } else {
                System.out.println("All references saved");
            }
        }
        System.exit(0);
    }
}
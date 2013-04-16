package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
    //private Validator validator;
    //--------------------------
    private CommandLineParser parser;
    private Options menu;
    private Options referenceOptions;
    private HelpFormatter help;
    private Scanner scanner;
    //--------------------------
    private boolean saved;

    public UI() {
        logic.saveAllReferences();
        initialize();
    }

    private void initialize() {
        saved = false;
        scanner = new Scanner(System.in);
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
        processInput(cmd);
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
        Options opt = new Options();
        List<String> availableReferenceTypes = logic.getReferenceTypes();
        Iterator<String> it = availableReferenceTypes.iterator();
        while (it.hasNext()) {
            String ref = it.next();
            opt.addOption(ref, false, "Add reference of type: " + ref);
        }
        return opt;
    }

    /*
     * Yksinkertainen valinta-ui. Käyttäjä tekee valinnat kokonaislukusyötteillä.
     */
    public CommandLine getDialog(Options opt) {
        help.printHelp(" ", opt, true);
        while (true) {
            String input = scanner.nextLine();
            String[] args = input.split(" ");
            try {
                CommandLine cmd = parser.parse(opt, args);
                return cmd;
            } catch (ParseException e) {
                System.out.println("Parsing caused exception: " + e.getMessage());
            }
        }
    }

    private void processInput(CommandLine cmd) {
        if (cmd.hasOption("quit")) {
            quit();
        } else {
            if (cmd.hasOption("add"));
            //addReference();
        }
    }

    private void quit() {
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
//    private void saveAsBibtext() {
//        String bib = "";
//        ArrayList<Map<String, String>> allReferences = logic.giveAllReferences();
//        if (allReferences.isEmpty()) {
//            System.out.println("No references!");
//        } else {
//            for (Map<String, String> singleReference : allReferences) {
//                for (String key : singleReference.keySet()) {
//                    bib += "," + key + "=" + singleReference.get(key);
//                }
//            }
//            String savedBibTex = logic.printBibTex(bib);
//            System.out.println("Saved the following BibTex references: \n" + savedBibTex);
//        }
//    }
//
//    private void printAll() {
//        ArrayList<Map<String, String>> allReferences = logic.giveAllReferences();
//        for (Map<String, String> singleReference : allReferences) {
//            printReference(singleReference);
//            System.out.println("---------------------------------");
//        }
//    }
//
//    private void printReference(Map<String, String> reference) {
//        for (String key : reference.keySet()) {
//            System.out.println(key + ": " + reference.get(key));
//        }
//    }
//
    /*
 * List all known reference types and prompt, which one will be used
 */
//
/*
 * Täytetään yhden viitteen tiedot, näiden tarkistus on nyt sitten toistaiseksi logiikan vastuulla
 */
//    private void addReference(int id) {
//        Map<String, String> newReference = logic.giveFields(id);
//        String givenValue;
//        System.out.println("Täytä seuraavat kentät: ");
//        for (Iterator<String> it = newReference.keySet().iterator(); it.hasNext();) {
//            String field = it.next();
//            System.out.println(field + ":");
//            givenValue = sc.nextLine();
//            newReference.put(field, givenValue);
//        }
//        for (String n : newReference.keySet()) {
//            System.out.println(n + " - " + newReference.get(n));
//        }
//        logic.addReference(newReference);
//        start();
//    }
//
//    /*
//     * Metodit syötteiden tarkistausta varten, tämä luokka lienee turha mikäli syötteet tarkistetaan logiikassa.
//     * 
//     */
//    class Validator {
//
//        /*
//         * Pyytää käyttäjältä validia kokonaislukua, kunnes se saadaan
//         */
//        public int promptInteger(int lowerbound, int upperbound) {
//            int selection;
//            while (true) {
//                try {
//                    selection = sc.nextInt();
//                    if (selection < lowerbound || selection > upperbound) {
//                        throw new InputMismatchException();
//                    }
//                    sc.nextLine();
//                    return selection;
//                } catch (InputMismatchException e) {
//                    System.out.println("Virheellinen syöte");
//                    sc.nextLine();
//                }
//            }
//        }
//    }
//}

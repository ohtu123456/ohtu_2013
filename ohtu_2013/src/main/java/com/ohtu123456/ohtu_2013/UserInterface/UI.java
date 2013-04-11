package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.Logic;
import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Alustava UI, kommunikoi logiican kanssa LogicInterfacen kautta
 *
 * @author Leif Setälä
 */
@Component
public class UI {

    private HashMap<String, Integer> referenceTypes;
    private ArrayList<String> fields; //Viitteen vaatimat kentät, kysytään käyttäjältä
    private Reader inputReader;
    private Printer outputPrinter;
    @Autowired
    private LogicInterface logic;
    private Validator validator;

    @Autowired
    public UI(Reader reader, Printer printer) {
        initialize(reader, printer);
    }

    private void initialize(Reader reader, Printer printer) {
        referenceTypes = new HashMap<String, Integer>();
        referenceTypes.put("Article", 1);
        inputReader = reader;
        outputPrinter = printer;
        validator = new Validator();
        logic = new Logic();
    }

    /*
     * Yksinkertainen valinta-ui. Käyttäjä tekee valinnat kokonaislukusyötteillä.
     */
    public void start() {
        outputPrinter.println("Valinnat: \n"
                + "1 - Lisää viite. \n"
                + "2 - Tulosta kaikki. \n"
                + "3 - Tulosta Bibtext.\n\n"
                + "0 - Sulje.");
        int selection = validator.promptInteger(0, 3);
        switch (selection) {
            case 0: {
                System.exit(0);
            }
            case 1: {
                addReference();
            }
            case 2: {
                printAll();
            }
                
            case 3: {
                printBibtext();
            }    
        }
    }

    
    private void printBibtext(){
        String bib="";
        ArrayList<Map<String,String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> singleReference : allReferences)   {
            for (String key : singleReference.keySet())   {
           bib+= ","+key + "=" + singleReference.get(key);
        }
        }
        
       logic.printBibTex(bib); 
    }
    private void printAll() {
        ArrayList<Map<String,String>> allReferences = logic.giveAllReferences();
        for (Map<String, String> singleReference : allReferences)   {
            printReference(singleReference);
            outputPrinter.println("---------------------------------");
        }
        
        
    }
    
    private void printReference(Map<String, String> reference)  {
        for (String key : reference.keySet())   {
            outputPrinter.println(key + ": " + reference.get(key));
        }
    }

    /*
     * Listataan tunnetut viitetyypit,joista käyttäjä valitsee haluamansa
     */
    private void addReference() {
        outputPrinter.println("Valitse viitteen tyyppi: \n");
        for (String type : referenceTypes.keySet()) {
            outputPrinter.println(referenceTypes.get(type) + " - " + type);
        }
        outputPrinter.println("\n0 - Palaa valikkoon.");
        int selection = validator.promptInteger(0, referenceTypes.size());
        if (selection == 0) {
            start();
        } else {
            addReference(selection);
        }
    }

    /*
     * Täytetään yhden viitteen tiedot, näiden tarkistus on nyt sitten toistaiseksi logiikan vastuulla
     */
    private void addReference(int id) {
        Map<String, String> newReference = logic.giveFields(id);
        String givenValue;
        outputPrinter.println("Täytä seuraavat kentät: ");
        for (Iterator<String> it = newReference.keySet().iterator(); it.hasNext();) {
            String field = it.next();
            outputPrinter.println(field + ":");
            givenValue = inputReader.nextLine();
            newReference.put(field, givenValue);
        }
        for (String n : newReference.keySet()) {
            outputPrinter.println(n + " - " + newReference.get(n));
        }
        if(logic.addReference(newReference))
            outputPrinter.println("Viite lisätty");
        start();
    }

    /*
     * Metodit syötteiden tarkistausta varten, tämä luokka lienee turha mikäli syötteet tarkistetaan logiikassa.
     * 
     */
    class Validator {

        /*
         * Pyytää käyttäjältä validia kokonaislukua, kunnes se saadaan
         */
        public int promptInteger(int lowerbound, int upperbound) {
            int selection;
            while (true) {
                try {
                    selection = inputReader.nextInt();
                    if (selection < lowerbound || selection > upperbound) {
                        throw new InputMismatchException();
                    }
                    return selection;
                } catch (InputMismatchException e) {
                    outputPrinter.println("Virheellinen syöte");
                }
            }
        }
    }
}

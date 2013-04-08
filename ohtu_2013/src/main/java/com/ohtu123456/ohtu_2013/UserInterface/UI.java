package com.ohtu123456.ohtu_2013.UserInterface;

import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
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
    private Scanner sc;
    @Autowired
    private LogicInterface logic;
    private Validator validator;

    public UI() {
        initialize();
    }

    private void initialize() {
        referenceTypes = new HashMap<String, Integer>();
        referenceTypes.put("Book", 1);
        sc = new Scanner(System.in);
        validator = new Validator();
    }

    /*
     * Yksinkertainen valinta-ui. Käyttäjä tekee valinnat kokonaislukusyötteillä.
     */
    public void start() {
        System.out.println("Valinnat: \n"
                + "1 - Lisää viite. \n"
                + "2 - Tulosta kaikki.\n\n"
                + "0 - Sulje.");
        int selection = validator.promptInteger(0, 2);
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
        }
    }

    private void printAll() {
        ArrayList<Map<String,String>> allReferences = logic.annaKaikkiViitteet();
        for (Map<String, String> singleReference : allReferences)   {
            printReference(singleReference);
            System.out.println("---------------------------------");
        }
    }
    
    private void printReference(Map<String, String> reference)  {
        for (String key : reference.keySet())   {
            System.out.println(key + ": " + reference.get(key));
        }
    }

    /*
     * Listataan tunnetut viitetyypit,joista käyttäjä valitsee haluamansa
     */
    private void addReference() {
        System.out.println("Valitse viitteen tyyppi: \n");
        for (String type : referenceTypes.keySet()) {
            System.out.println(referenceTypes.get(type) + " - " + type);
        }
        System.out.println("\n0 - Palaa valikkoon.");
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
        Map<String, String> newReference = logic.annaKentat(id);
        String givenValue;
        System.out.println("Täytä seuraavat kentät: ");
        for (Iterator<String> it = newReference.keySet().iterator(); it.hasNext();) {
            String field = it.next();
            System.out.println(field + ":");
            givenValue = sc.nextLine();
            newReference.put(field, givenValue);
        }
        for (String n : newReference.keySet()) {
            System.out.println(n + " - " + newReference.get(n));
        }
        logic.lisaaViite(newReference);
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
                    selection = sc.nextInt();
                    if (selection < lowerbound || selection > upperbound) {
                        throw new InputMismatchException();
                    }
                    sc.nextLine();
                    return selection;
                } catch (InputMismatchException e) {
                    System.out.println("Virheellinen syöte");
                    sc.nextLine();
                }
            }
        }
    }
}

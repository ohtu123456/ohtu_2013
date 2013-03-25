package com.ohtu123456.ohtu_2013;

import java.util.Map;

/**
 * Logiican rajapinta
 *
 * @author Leif Setälä
 */

public interface LogicInterface {

    public boolean lisaaViite(Map<String, Object> viite); //Lisää viitteen ja palauttaa tiedon siitä, onnistuiko lisäys

    public String tulostaBibTex(String id);

    public Map<String, Object> tulostaClear(String id);

    /*
     * Logiikka voisi palauttaa id:n perusteella mapin "Avain - tyhjä" -pareja, joihin käyttöliittymä päivittää epätyhjät arvot?
     * Syötteet pitää tarkistaa, joten mietin olisiko paras poistaa annaKentat-metodi ja lisätä tieto tarvittavista kentistä ja valideista 
     * inputeista suoraan käyttöliittymään. Muuten tarkistus pitänee tehdä logiikka-luokassa. Tällöin ei pystytä antamaan käyttäjälle samantien 
     * feedbackia virheellisestä inputista, koska kaikki tiedot lähetetään logiikalle vasta kun kaikki kentät on täytetty.
     * -Leif
     */
    public Map<String, Object> annaKentat(int id); 
}

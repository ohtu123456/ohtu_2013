package com.ohtu123456.ohtu_2013;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Pelkkä testilogiikka käyttöliittymää varten, korvataan oikealla
 * logiikkaluokalla. Pistän toistaiseksi tänne vain yhden testimetodin, jotta
 * saan testattua UI:ta
 *
 * @author Leif Setälä
 */
@Component
public class Logic implements LogicInterface {

    public boolean lisaaViite(Map<String, Object> viite) {
        System.out.println("Viite lisätty");
        return true;
    }

    public String tulostaBibTex(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, Object> tulostaClear(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, Object> annaKentat(int id) {
        Map<String, Object> fields = new LinkedHashMap<String, Object>(); //LinkedHashMap säilyttää alkioiden järjestyksen
        fields.put("Author", null);
        fields.put("Title", null);
        fields.put("Year", null);
        fields.put("Publisher", null);




        return fields;
    }
}

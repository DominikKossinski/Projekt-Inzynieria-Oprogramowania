package com.example.projectio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Translator {

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static String toUpperCase(String text) {return text.toUpperCase(); }

    public static String expandShortcuts(String text) {
        text = text.replace("Dr ", "Doktor ");
        text = text.replace("dr ", "doktor ");

        text = text.replace("Prof.", "Profesor");
        text = text.replace("prof.", "profesor");

        text = text.replace("mgr ", "magister ");
        text = text.replace("Mgr ", "Magister ");

        text = text.replace("inż.", "inżynier");
        text = text.replace("Inż.", "Inżynier");

        text = text.replace("hab.", "habilitowany");
        text = text.replace("Hab.", "Habilitowany");


        return text;
    }

    /**
     * Metoda służąca do rozwijania zdefiniowanych przez użytkownika skrótów
     * w tekście przekazywanym jako parametr.
     *
     * @param text - (String) tekst, w którym sktóty mają zostać rozwinięte
     * @return (String) tekst po rozwinięciu skrótów
     */
    public static String expandMyShortcuts(String text) {
        File file = new File("src\\main\\resources\\myShortcuts.json");
        try {
            FileReader reader = new FileReader(file);
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object object : array) {
                JSONObject shortcut = (JSONObject) object;
                text = text.replace(shortcut.get("shortcut").toString(), shortcut.get("expanded").toString());
            }
            return text;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}

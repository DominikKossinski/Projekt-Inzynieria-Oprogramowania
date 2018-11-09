package com.example.projectio;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController umożliwiający użytkownikowi składanie dostępnych translacji
 *
 * @author Dominik
 * @see com.example.projectio.Translator
 */
@RestController
public class MultiTranslationRestController {


    /**
     * Metoda klasy MultiTranslationRestController pozwalająca na obsługę żądania
     * składania wielu translacji na jednym podanym przez użytkownika tekscie
     * Przykładowy url:
     * http://localhost:8080/api/multiTranslation?text=Cos 100 prof.&translations=%5B%22capitalize%22%2C%20%22inverse%22%2C%20%22expandNumbers%22%2C%20%22expandShortcuts%22%5D
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @param jsonText - (JSONArray) lista zawierająca nazwy rządanych przez użytkownika translacji
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     */
    @RequestMapping(value = "/api/multiTranslation")
    public static String getMultiTranslation(@RequestParam(name = "text") String text, @RequestParam(name = "translations") String jsonText) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray translationsArray = (JSONArray) parser.parse(jsonText);
            for (Object aTranslationsArray : translationsArray) {
                String actTranslation = (String) aTranslationsArray;
                if (actTranslation.compareTo("lower") == 0) {
                    text = Translator.toLowerCase(text);
                } else if (actTranslation.compareTo("upper") == 0) {
                    text = Translator.toUpperCase(text);
                } else if (actTranslation.compareTo("capitalize") == 0) {
                    text = Translator.toCapitalize(text);
                } else if (actTranslation.compareTo("inverse") == 0) {
                    text = Translator.toInverse(text);
                } else if (actTranslation.compareTo("expandShortcuts") == 0) {
                    text = Translator.expandShortcuts(text);
                } else if (actTranslation.compareTo("expandNumbers") == 0) {
                    text = Translator.expandNumbers(text);
                } else if (actTranslation.compareTo("expandMyShortcuts") == 0) {
                    //TODO dodanie własnych skrótów
                }
            }
            return text;
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}

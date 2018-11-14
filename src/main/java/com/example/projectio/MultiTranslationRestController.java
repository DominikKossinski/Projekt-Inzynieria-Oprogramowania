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
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @param jsonText - (JSONArray) lista zawierająca nazwy żądanych przez użytkownika translacji
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
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
                    text = Translator.expandNumbers(text, "pl");
                } else if (actTranslation.compareTo("expandMyShortcuts") == 0) {
                    text = Translator.expandMyShortcuts(text);
                }
            }
            return text;
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}

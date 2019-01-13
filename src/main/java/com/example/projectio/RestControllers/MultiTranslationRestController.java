package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.MultiDecorator;
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
     * @param jsonText - (JSONArray) lista zawierająca nazwy żądanych przez użytkownika translacji
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     */
    @RequestMapping(value = "/api/multiTranslation")
    public String getMultiTranslation(@RequestParam(name = "text") String text, @RequestParam(name = "translations") String jsonText) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray translationsArray = (JSONArray) parser.parse(jsonText);
            return getTextFromDecorator(text,translationsArray);
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    /**
     * Metoda klasy MultiTranslationRestController pozwalająca na otrzymanie od dekoratora zmienionego tekstu

     * @param text - (String) tekst do transformacji
     * @param translationsArray - (JSONArray) - lista transformacji
     * @return (String) tekst po transformacji
     */

    public String getTextFromDecorator(String text, JSONArray translationsArray)
    {
        Decorator decorator = new MultiDecorator(text, translationsArray);
        return decorator.decore();
    }


}

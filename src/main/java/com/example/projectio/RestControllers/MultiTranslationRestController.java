package com.example.projectio.RestControllers;

import com.example.projectio.DecoratorInterface;
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
public class MultiTranslationRestController implements DecoratorInterface {

    /**
     * Pole klasy przechowujące tekst, który ma zostać poddany translacji.
     */
    private String text;

    /**
     * Pole klasy przechowujące listę translacjii do wykonania;
     */
    private JSONArray translationsArray;

    /**
     * Metoda klasy MultiTranslationRestController pozwalająca na obsługę żądania
     * składania wielu translacji na jednym podanym przez użytkownika tekscie
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
            this.text = text;
            this.translationsArray = translationsArray;
            String toReturn = decore();
            return toReturn;
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public String decore() {
        MultiDecorator decorator = new MultiDecorator(text, translationsArray);
        return decorator.decore();
    }
}

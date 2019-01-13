package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.AutoCorrectDecorator;
import com.example.projectio.Decorators.Decorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za funkcję autoortografii
 *
 * @author Mariusz
 */


@RestController
public class AutoCorrectRestController {

    private static final Logger logger = LoggerFactory.getLogger(AutoCorrectRestController.class);


    /**
     * Metoda klasy AutoCorrectRestController pozwalająca na obsługę żądania
     * poprawy ortografii w określonym tekście
     * Przykładowy url:
     * http://localhost:8080/api/autocorrect?text=przykladowy tekst
     *
     * @param text - (String) tekst do poprawy podany przez użytkownika
     * @return (String) tekst po poprawienie
     */

    @GetMapping("/api/autocorrect")
    public String getTextToCapitalize(@RequestParam(name = "text") String text) {
        logger.debug("Tekst przed zmianą: " + text);
        String toReturn = getTextFromDecorator(text);
        logger.debug("Tekst po zmianie: " + toReturn);

        return getTextFromDecorator(text);
    }

    /**
     * Metoda klasy AutoCorrectRestController pozwalająca na otrzymanie od dekoratora zmienionego tekstu

     * @param text - (String) tekst do transformacji
     * @return (String) tekst po transformacji
     */

    public String getTextFromDecorator(String text)
    {
        Decorator decorator = new AutoCorrectDecorator(text);
        return decorator.decore();
    }

}

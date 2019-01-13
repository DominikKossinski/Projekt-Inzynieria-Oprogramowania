package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.ExpandNumbersDecorator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController odpowiadający za tworzenie, usuwanie oraz rozwijanie
 * skrótów, które zdefiniuje użytkownik.
 *
 * @author Szymon
 */

@RestController
public class NumberRestController {



    /**
     * Metoda klasy NumberRestController pozwalająca na obsługę żądania
     * zamiany liczb zapisanych cyframi na ich reprezentację słowną
     * Przykładowy url:
     * http://localhost:8080/api/numbers?text=100 zlotych
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @param language - (String) wybrany język transformacji
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     * @see ExpandNumbersDecorator#decore()
     */

    @GetMapping("/api/numbers")
    public String getTextToNumbers(@RequestParam(name = "text") String text,
                                   @RequestParam(name = "lg", defaultValue = "pl", required = false) String language) {
        return getTextFromDecorator(text, language);
    }

    /**
     * Metoda klasy NumberRestController pozwalająca na otrzymanie od dekoratora zmienionego tekstu

     * @param text - (String) tekst do transformacji
     * @return (String) tekst po transformacji
     */

    public String getTextFromDecorator(String text, String lan)
    {
        Decorator decorator = new ExpandNumbersDecorator(text, lan);
        return decorator.decore();
    }

}
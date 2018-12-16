package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.CapitalizeDecorator;
import com.example.projectio.Decorators.Decorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za zmianę  pierwszych liter wyrazów na wielkie
 *
 * @author Krzysztof
 */


@RestController
public class CapitalizeRestController {

    private static final Logger logger = LoggerFactory.getLogger(CapitalizeRestController.class);

    /**
     * Metoda klasy CapitalizeRestController pozwalająca na obsługę żądania
     * zamiany pierwszych liter w każdym wyrazie w podanym tekscie na wielkie
     * Przykładowy url:
     * http://localhost:8080/api/capitalize?text=przykladowy tekst
     *
     * @param text - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     * @see CapitalizeDecorator#decore()
     */

    @GetMapping("/api/capitalize")
    public String getTextToCapitalize(@RequestParam(name = "text") String text) {

        logger.debug("Tekst przed zmianą: " + text);
        Decorator decorator = new CapitalizeDecorator(text);
        String toReturn = decorator.decore();
        logger.debug("Tekst po zmianie: " + toReturn);

        return toReturn;
    }

}

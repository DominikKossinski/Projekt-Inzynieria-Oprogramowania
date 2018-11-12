package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CapitalizeRestController {

    private static final Logger logger = LoggerFactory.getLogger(CapitalizeRestController.class);

    /**
     * Metoda klasy CapitalizeRestController pozwalająca na obsługę żądania
     * zamiany pierwszych liter w każdym wyrazie w podanym tekscie na wielkie
     * Przykładowy url:
     * http://localhost:8080/api/capitalize?text=przykladowy tekst
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     */

    @GetMapping("/api/capitalize")
    public String getTextToCapitalize(@RequestParam(name = "text") String text) {

        logger.debug("Tekst przed zmianą: " + text);
        logger.debug("Tekst po zmianie: " + Translator.toCapitalize(text));

        return Translator.toCapitalize(text);
    }
}

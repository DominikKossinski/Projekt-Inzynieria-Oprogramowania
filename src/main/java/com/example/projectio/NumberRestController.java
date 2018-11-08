package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Metoda klasy NumberRestController pozwalająca na obsługę rządania
 * zamiany liczb zapisanych cyframi na ich reprezentację słowną
 * Przykładowy url:
 * http://localhost:8080/api/numbers?text=100 zlotych
 *
 * @param text     - (String) tekst do translacji podany przez użytkownika
 * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
 */

@RestController
public class NumberRestController {
    @GetMapping("/api/numbers")
    public String getTextToCapitalize(@RequestParam(name = "text") String text) {
        return Translator.expandNumbers(text);
    }
}
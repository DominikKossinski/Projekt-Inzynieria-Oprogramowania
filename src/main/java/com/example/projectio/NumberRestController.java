package com.example.projectio;

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
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     * @see Translator#expandNumbers(String)
     */

    @GetMapping("/api/numbers")
    public String getTextToNumbers(@RequestParam(name = "text") String text) {
        return Translator.expandNumbers(text);
    }
}
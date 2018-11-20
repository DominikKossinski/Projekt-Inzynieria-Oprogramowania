package com.example.projectio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za rozwijanie skrótów
 *
 * @author Dominik
 */


@RestController
public class ExpandRestController {

    /**
     * Metoda klasy ExpandRestController pozwalająca na obsługę żądania
     * zamiany skrótów na ich pełne rozwinięcia
     * Przykładowy url:
     * http://localhost:8080/api/expandShortcuts?text=dr Krzysztof
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     * @see Translator#expandShortcuts(String)
     */

    @RequestMapping("/api/expandShortcuts")
    public String getExpandedShortcuts(@RequestParam(name = "text") String text) {
        return Translator.expandShortcuts(text);
    }
}

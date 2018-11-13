package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LowerRestController {

    /**
     * Metoda klasy LowerRestController pozwalająca na obsługę żądania
     * zamiany liter z wielkich na małe
     * Przykładowy url:
     * http://localhost:8080/api/lower?text=TEKST
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji rządanych przez użytkownika
     */

    @GetMapping("/api/lower")
    public String getTextToLowerCase(@RequestParam(name = "text") String text) {
        return Translator.toLowerCase(text);
    }
}

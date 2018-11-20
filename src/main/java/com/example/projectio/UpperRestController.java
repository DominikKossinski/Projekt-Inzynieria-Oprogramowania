package com.example.projectio;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za zmianę wielkości liter na małe
 * @author Krzysztof
 */

@RestController
public class UpperRestController {

    /**
     * Metoda klasy UpperRestController pozwalająca na obsługę żądania
     * zamiany liter z małych na wielkie
     * Przykładowy url:
     * http://localhost:8080/api/upper?text=tekst
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     * @see Translator#toUpperCase(String)
     */


    @GetMapping("/api/upper")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {
        return Translator.toUpperCase(text);
    }

}

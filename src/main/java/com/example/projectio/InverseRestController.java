package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za obracanie podanego tekstu z zachowaniem
 * wielkości liter na odpowiednich pozycjach
 *
 * @author Mariusz
 */
@RestController
public class InverseRestController {
    /**
     * Metoda klasy InverseRestController pozwalająca na obsługę żądania obrócenia
     * tekstu z zachowaniem wielkości liter na odpowiednich pozycjach
     *
     * Przykładowy URl:
     * http:\\localhost:8080/api/inverse?text=MariuSz
     *
     * @param text - (String) tekst podany przez użytkownika
     * @return (String) tekst po obróceniu oraz uporządkowaniu wielkości
     *                  liter
     * @see Translator#toInverse(String)
     */
    @GetMapping("/api/inverse")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {
        return Translator.toInverse(text);
    }
}

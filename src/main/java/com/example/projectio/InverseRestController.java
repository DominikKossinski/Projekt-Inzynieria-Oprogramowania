package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
  
    private static final Logger logger = LoggerFactory.getLogger(InverseRestController.class);
  
    @GetMapping("/api/inverse")
    public static String inverseText(@RequestParam(name = "text") String text) {
      
       logger.info("Rozpoczynam translację metodą inverse");
        
       return Translator.toInverse(text);
    }
  
}



package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.InverseDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * @see InverseDecorator#decore()
     */
  
    private static final Logger logger = LoggerFactory.getLogger(InverseRestController.class);

    /**
     * Pole klasy przechowujące tekst do odwócenia.
     */
    private String text;
  
    @GetMapping("/api/inverse")
    public String inverseText(@RequestParam(name = "text") String text) {
      
       logger.info("Rozpoczynam translację metodą inverse");
        this.text = text;
        return getTextFromDecorator(text);
    }

    /**
     * Metoda klasy InverseRestController pozwalająca na otrzymanie od dekoratora zmienionego tekstu

     * @param text - (String) tekst do transformacji
     * @return (String) tekst po transformacji
     */


    public String getTextFromDecorator(String text)
    {
        Decorator decorator = new InverseDecorator(text);
        return decorator.decore();
    }


}



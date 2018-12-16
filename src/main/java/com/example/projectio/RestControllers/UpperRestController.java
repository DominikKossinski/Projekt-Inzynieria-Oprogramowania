package com.example.projectio.RestControllers;


import com.example.projectio.DecoratorInterface;
import com.example.projectio.Decorators.UpperCaseDecorator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za zmianę wielkości liter na małe
 * @author Krzysztof
 */

@RestController
public class UpperRestController implements DecoratorInterface {

    /**
     * Pole klasy przechowujące tekst, na którym należy wykonać operację toUpperCase.
     */
    private String text;

    /**
     * Metoda klasy UpperRestController pozwalająca na obsługę żądania
     * zamiany liter z małych na wielkie
     * Przykładowy url:
     * http://localhost:8080/api/upper?text=tekst
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     * @see UpperCaseDecorator#decore()
     */


    @GetMapping("/api/upper")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {
        this.text = text;
        String toReturn = decore();
        return toReturn;
    }

    @Override
    public String decore() {
        UpperCaseDecorator decorator = new UpperCaseDecorator(text);
        return decorator.decore();
    }
}

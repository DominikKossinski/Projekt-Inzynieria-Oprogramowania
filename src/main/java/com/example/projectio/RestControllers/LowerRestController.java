package com.example.projectio.RestControllers;

import com.example.projectio.DecoratorInterface;
import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.LowerCaseDecorator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za zmianę wielkości liter na małe
 *
 * @author Dominik
 */

@RestController
public class LowerRestController implements DecoratorInterface {

    /**
     * Pole klasy przechowujące tekst, na którym ma zostać wykonana operacja 'toLowerCase'.
     */
    private String text;

    /**
     * Metoda klasy LowerRestController pozwalająca na obsługę żądania
     * zamiany liter z wielkich na małe
     * Przykładowy url:
     * http://localhost:8080/api/lower?text=TEKST
     *
     * @param text - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     * @see Decorator#decore()
     */

    @GetMapping("/api/lower")
    public String getTextToLowerCase(@RequestParam(name = "text") String text) {
        this.text = text;
        String toReturn = decore();
        return toReturn;
    }

    @Override
    public String decore() {
        LowerCaseDecorator decorator = new LowerCaseDecorator(text);
        return decorator.decore();
    }
}

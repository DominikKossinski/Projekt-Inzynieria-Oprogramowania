package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.DeleteRepeatWordsDecorator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za usuwanie powtórzeń w zdaniu
 *
 * @author Krzysztof
 */

@RestController
public class DelRepeatWordRestController {

    /**
     * Metoda klasy DelRepeatWordRestController pozwalająca na obsługę żądania
     * usuwania powtarzających się wyrazów w zdaniu
     * Przykładowy url:
     * http://localhost:8080/api/repeatDel?text=Idę do do sklepu
     *
     * @param text     - (String) tekst do translacji podany przez użytkownika
     * @return (String) tekst po zastosowaniu translacji żądanych przez użytkownika
     * @see DeleteRepeatWordsDecorator#decore()
     */
    @GetMapping("/api/repeatDel")
    public String getTextWithDelRepeats(@RequestParam(name = "text") String text) {
        Decorator decorator = new DeleteRepeatWordsDecorator(text);
        String toReturn = decorator.decore();
        return toReturn;
    }

}

package com.example.projectio.RestControllers;

import com.example.projectio.DecoratorInterface;
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
public class DelRepeatWordRestController implements DecoratorInterface {

    /**
     * Pole klasy przechowujące tekst, w którym mają zostać usunięte powtórzenia.
     */
    private String text;

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
        this.text = text;
        String toReturn = decore();
        return toReturn;
    }

    @Override
    public String decore() {
        DeleteRepeatWordsDecorator decorator = new DeleteRepeatWordsDecorator(text);
        return decorator.decore();
    }
}

package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na zamianę liter na małe
 *
 * @author Krzysztof
 */

public class LowerCaseDecorator extends Decorator {

    /**
     * Pole klasy przechowujące text do zamiany
     */

    private String text;

    public LowerCaseDecorator(String text) {
        this.text = text;
    }


    /**
     * Metoda klasy LowerCaseDecorator pozwalająca na zmianę wielkość liter na małe.
     *
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest mała
     */
    @Override
    public String decore() {
        return text.toLowerCase();
    }
}

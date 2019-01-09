package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na zamianę liter na małe
 *
 * @author Krzysztof
 */

public class LowerCaseDecorator extends Decorator {

    private Decorator decorator = null;

    public LowerCaseDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

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
        if (decorator != null) {
            text = decorator.decore();
        }
        return text.toLowerCase();
    }
}

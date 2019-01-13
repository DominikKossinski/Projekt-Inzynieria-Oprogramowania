package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na zamianę liter na małe
 *
 * @author Krzysztof
 */

public class LowerCaseDecorator extends Decorator {

    /**
     * Dekorator pole wykozystywane w implementacji wzorca dekorator
     */
    private Decorator decorator = null;

    /**
     * Publiczny konstruktor klasy, który przyjmuje inny dekorator.
     *
     * @param decorator
     */
    public LowerCaseDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * Publiczny konstruktor klasy, który przyjmuje jako parametr tekst wprowadzony przez użytkownika
     *
     * @param text
     */
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

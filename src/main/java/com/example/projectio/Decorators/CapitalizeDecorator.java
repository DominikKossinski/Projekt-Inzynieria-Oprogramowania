package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na zamianę pierwszej litery w zdaniu na wielką.
 *
 * @author Krzysztof
 */

public class CapitalizeDecorator extends Decorator {

    /**
     * Dekorator pole wykozystywane w implementacji wzorca dekorator
     */
    private Decorator decorator = null;

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr inny dekorator.
     *
     * @param decorator - dekoratoe
     */
    public CapitalizeDecorator(Decorator decorator) {
        this.decorator = decorator;
    }


    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr tekst podany przez użytkownika.
     * @param text - tekst podany przez użytkownika
     */
    public CapitalizeDecorator(String text) {
        this.text = text;
    }


    /**
     * Metoda klasy Translator pozwalająca na zmianę welkości pierwszej litery
     * każdego wyrazu w zdaniu na wielką.
     *
     * @return (String) tekst po zastosowaniu translacji w którym pierwsza litera każdego wyrazu jest wielka
     */
    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
        text = text.toLowerCase();

        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length; i++)
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        return result.toString().substring(0, result.length() - 1);
    }
}

package com.example.projectio.Decorators;

public class UpperCaseDecorator extends Decorator {

    private String text;

    public UpperCaseDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda klasy Translator pozwalająca na zmianę wielkość liter na wielkie.
     *
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest wielka
     * @see Decorator#decore()
     */


    /**
     * Metoda klasy UpperCaseDecorator pozwalająca na zamianę liter na wielkie
     *
     * @return (String) tekst po zastosowaniu zamiany liter na wielkie
     */

    @Override
    public String decore() {
        return this.text.toUpperCase();
    }
}

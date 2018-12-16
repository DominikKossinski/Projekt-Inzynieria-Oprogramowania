package com.example.projectio.Decorators;

public class UpperCaseDecorator extends Decorator {


    public UpperCaseDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda klasy UpperCaseDecorator pozwalająca na zmianę wielkość liter na wielkie.
     *
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest wielka
     * @see Decorator#decore()
     */

    @Override
    public String decore() {
        return this.text.toUpperCase();
    }
}

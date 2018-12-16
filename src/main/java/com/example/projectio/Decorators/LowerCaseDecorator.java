package com.example.projectio.Decorators;

public class LowerCaseDecorator extends Decorator {

    public LowerCaseDecorator(String text) {
        this.text = text;
    }


    /**
     * Metoda klasy Translator pozwalająca na zmianę wielkość liter na małe.
     *
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest mała
     */
    @Override
    public String decore() {
        return text.toLowerCase();
    }
}

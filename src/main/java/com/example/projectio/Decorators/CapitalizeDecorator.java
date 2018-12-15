package com.example.projectio.Decorators;

public class CapitalizeDecorator extends Decorator {

    private String text;

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
        text = text.toLowerCase();

        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length; i++)
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        return result.toString().substring(0, result.length() - 1);
    }
}

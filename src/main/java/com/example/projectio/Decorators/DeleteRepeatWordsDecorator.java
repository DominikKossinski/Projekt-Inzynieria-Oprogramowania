package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na usuwanie powtórzeń w zdaniach.
 *
 * @author Krzysztof
 */

public class DeleteRepeatWordsDecorator extends Decorator {

    /**
     * Dekorator pole wykozystywane w implementacji wzorca dekorator
     */
    private Decorator decorator = null;

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr inny dekorator.
     *
     * @param decorator - dekorator
     */
    public DeleteRepeatWordsDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr tekst wprowadzony przez użytkownika.
     * @param text- tekst
     */
    public DeleteRepeatWordsDecorator(String text) {
        this.text = text;
    }


    /**
     * Metoda służąca do usuwania powtórzeń w zdaniu
     *
     * @return (String) tekst po usunięciu powtórzeń
     */
    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].equals(words[i + 1])) {
                words[i + 1] = "del";
            }

        }

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("del"))
                continue;
            result.append(words[i]).append(" ");

        }

        return result.toString().substring(0, result.length() - 1);
    }

    public void setText(String text) {
        this.text = text;
    }
}

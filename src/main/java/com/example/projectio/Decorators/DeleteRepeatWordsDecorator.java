package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na usuwanie powtórzeń w zdaniach.
 *
 * @author Krzysztof
 */

public class DeleteRepeatWordsDecorator extends Decorator {

    private Decorator decorator = null;

    public DeleteRepeatWordsDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

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

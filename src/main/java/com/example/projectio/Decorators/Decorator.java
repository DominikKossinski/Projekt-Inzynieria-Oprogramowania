package com.example.projectio.Decorators;

/**
 * Klasa abstrakcyjna do obsługi dekoratora
 *
 * @author Dominik
 */

public abstract class Decorator implements TextInterface {

    /**
     * Pole klasy przechowujące tekst.
     */

    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

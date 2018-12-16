package com.example.projectio.Decorators;

/**
 * Klasa abstrakcyjna do obsługi dekoratora
 *
 * @author Dominik
 */

public abstract class Decorator {

    /**
     * Metoda abstrakcyjna pozwalająca do dodawanie dekoracji do tekstu.
     * @return (String) tekst po zastosowaniu translacji.
     */

    public abstract String decore();

}

package com.example.projectio.Data;

/**
 * Klasa wspomagająca rozwijanie skrótów
 *
 * @author Dominik
 */

public class Shortcut {

    /**
     * Pole klasy przechowujące skrót.
     */

    private String shortcut;


    /**
     * Pole klasy przechowujące rozwinięty skrót.
     */

    private String expandedShortcut;

    public Shortcut(String shortcut, String expandedShortcut) {
        this.shortcut = shortcut;
        this.expandedShortcut = expandedShortcut;
    }


    /**
     * Metoda klasy Shortcut pozwalająca na pobranie pola expandedShortcut
     * @return (String) wartość pola expandedShortcut
     */

    public String getExpandedShortcut() {
        return expandedShortcut;
    }

    /**
     * Metoda klasy Shortcut pozwalająca na pobranie pola shortcut
     * @return (String) wartość pola shortcut
     */

    public String getShortcut() {
        return shortcut;
    }
}

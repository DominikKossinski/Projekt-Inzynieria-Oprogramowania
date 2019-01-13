package com.example.projectio.Decorators;

/**
 * Klasa pozwalająca na rozwijanie skrótów w zdaniach
 *
 * @author Dominik
 */


public class ExpandShortcutsDecorator extends Decorator {

    /**
     * Dekorator pole wykozystywane w implementacji wzorca dekorator
     */
    private Decorator decorator = null;

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr inny dekorator (implementacja wzorca Dekorator)
     *
     * @param decorator - dekorator
     */
    public ExpandShortcutsDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr tekst wprowadzony przez użytkownika
     * @param text - tekst wprowadzony przezużytkownika
     */
    public ExpandShortcutsDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda klasy ExpandShortcutsDecorator pozwalająca na rozwijanie podstawowych skrótów
     *
     * @return (String) tekst po zastosowaniu translacji w którym podane poniżej skróty
     * zostają zastąpione przez ich pełne rozwinięcia
     */
    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
        text = text.replace("Dr ", "Doktor ");
        text = text.replace("dr ", "doktor ");

        text = text.replace("Prof.", "Profesor");
        text = text.replace("prof.", "profesor");

        text = text.replace("mgr ", "magister ");
        text = text.replace("Mgr ", "Magister ");

        text = text.replace("inż.", "inżynier");
        text = text.replace("Inż.", "Inżynier");

        text = text.replace("hab.", "habilitowany");
        text = text.replace("Hab.", "Habilitowany");

        text = text.replace("np.", "na przykład");
        text = text.replace("Np.", "Na przykład");

        text = text.replace("itp.", "i tym podobne");
        text = text.replace("Itp.", "I tym podobne");

        return text;
    }
}

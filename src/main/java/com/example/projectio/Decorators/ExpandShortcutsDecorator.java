package com.example.projectio.Decorators;

public class ExpandShortcutsDecorator extends Decorator {

    private String text;

    public ExpandShortcutsDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda klasy Translator pozwalająca na rozwijanie podstawowych skrótów
     *
     * @return (String) tekst po zastosowaniu translacji w którym podane poniżej skróty
     * zostają zastąpione przez ich pełne rozwinięcia
     */
    @Override
    public String decore() {
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

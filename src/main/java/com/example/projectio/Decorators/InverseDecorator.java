package com.example.projectio.Decorators;

import static java.lang.Character.isUpperCase;

/**
 * Klasa pozwalająca na odwracanie kolejności znaków
 *
 * @author Mariusz
 */

public class InverseDecorator extends Decorator {

    /**
     * Pole klasy przechowujące text do zamiany
     */

    private String text;

    public InverseDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda klasy InverseDecorator służąca do obracania tekstu przekazanego jako parametr
     * z zachowaniem wielkości znaków na odpowiednich pozycjach.
     *
     * @return (String) tekst po obróceniu
     */
    @Override
    public String decore() {
        int[] letterSize = new int[text.length()];
        String newText = "";
        String inversed = "";
        for (int i = 0; i < text.length(); i++) { // obracanie tekstu oraz zapisywanie wielkości liter
            if (isUpperCase(text.charAt(i))) {
                letterSize[i] = 1;
            } else
                letterSize[i] = 0;
            newText = text.charAt(i) + newText;
        }
        for (int i = 0; i < text.length(); i++) { // powiększanie oraz pomniejszanie właściwych liter
            if (letterSize[i] == 1)
                inversed += Character.toUpperCase(newText.charAt(i));
            else
                inversed += Character.toLowerCase(newText.charAt(i));
        }
        return inversed;
    }
}

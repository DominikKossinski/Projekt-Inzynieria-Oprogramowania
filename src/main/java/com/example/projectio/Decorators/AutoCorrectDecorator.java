package com.example.projectio.Decorators;

import com.example.projectio.ProjectioApplication;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

/**
 * Klasa pozwalająca na automatyczne poprawianie błędów w zdaniach.
 *
 * @author Mariusz
 */

public class AutoCorrectDecorator extends Decorator {

    private Decorator decorator = null;

    /**
     * tablica zawierająca litery oraz odpowiadające im możliwości
     */
    private static String letters[][];
    /**
     * zmienna zawierająca pierwsze litery słów znajdujących się w słowniku w odpowiedniej kolejności
     */
    private static String alphabet;
    /**
     * tablica zawierająca wszystkie słowa ze słownika
     */
    private static Vector<Vector<String>> dictionary;
    /**
     * zmienna służąca do iteracji po obiektach
     */
    private int index;
    /**
     * tablica zawierająca możliwe poprawne słowa
     */
    private Vector<String> possible;
    /**
     * tablica zawierająca słowo zamienione na pojedyncze litery
     */
    private Vector<String> splitted;
    /**
     * zmienna zawierajaca poprawiony tekst
     */
    private String correctedSentence;
    /**
     * tablica zawierająca rozdzielone wyrazy z podanego zdania
     */
    private String[] wordsArray;

    public AutoCorrectDecorator(String text) {
        this.text = text;
        wordsArray = new String[1];
        correctedSentence = "";
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na załadowanie pliku słownika
     *
     * @param path - (String) nazwa pliku ze słownikiem
     */

    public static void init(String path) {
        AutoCorrectDecorator.dictionary = new Vector<Vector<String>>();
        for (int i = 0; i < 31; i++) dictionary.add(new Vector<String>());
        char firstLetter = 'a';
        int index = 0;
        try {
            ClassLoader classLoader = ProjectioApplication.class.getClassLoader();
            File file = new File(classLoader.getResource(path).getFile());
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                String temporaryWord = in.next();
                if (temporaryWord.toCharArray()[0] != firstLetter) {
                    firstLetter = temporaryWord.toCharArray()[0];
                    index++;
                }
                dictionary.get(index).add(temporaryWord);
            }
            in.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        AutoCorrectDecorator.letters = new String[][]{{"ó", "u"}, {"o", "ó"}, {"rz", "ż"}, {"ch", "h"}, {"l", "ł"}, {"a", "ą"}, {"e", "ę"}};
        AutoCorrectDecorator.alphabet = "abcćdeęfghijklłmnoópqrsśtuwyzźż";
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na wypisanie słownika w konsoli
     */

    public static void printDict() {
        for (Vector<String> i : dictionary) {
            for (String k : i) {
                System.out.println(k);
            }
        }
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na poprawienie całego zdania
     *
     * @return (String) - poprawione zdanie
     */
    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
        correctedSentence = "";
        wordsArray = new String[1];
        if (text.contains(" ")) wordsArray = text.toLowerCase().split(" ");
        else wordsArray[0] = text.toLowerCase();
        for (String i : wordsArray) {
            if (i.length() != 0) {
                generateAllPossible(i);
                correctedSentence += checkPossible() + " ";
            }

        }
        return correctedSentence;
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na znalezienie słowa w słowniku
     *
     * @param word - (String) słowo do znalezienia
     * @return (boolean) true jeżeli słowo znajduje się w słowniku false w przeciwnym wypadku
     */

    private boolean findWord(String word) {
        index = 0;
        if(!alphabet.contains(word.substring(0,1))){
            return true;
        }
        for (char p : alphabet.toCharArray()) {
            if (word.toCharArray()[0] == p) {
                break;
            }
            index++;
        }
        return dictionary.get(index).contains(word);
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na złączeniu pojedynczych liter w głoski w rozdzielonym na litery słowie
     *
     * @param word    - (Vector(String)) rozdzielone na litery słowo
     * @param letterA - (char) pierwsza litera do znalezienia
     * @param letterB - (char) druga litera do znalezienia
     * @return (Vector ( String)) słowo z połączonymi literami
     */

    private Vector<String> mergeLetters(Vector<String> word, char letterA, char letterB) {
        int tempSize = word.size();
        String tempString = "" + letterA + letterB;
        Vector<String> result = word;
        for (int j = 0; j < tempSize; j++) {
            if (result.get(j).toCharArray()[0] == letterA && result.get(j + 1).toCharArray()[0] == letterB) {
                result.set(j, tempString);
                result.remove(j + 1);
                tempSize--;
            }
        }
        return result;
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na rozdzielenia słowa na głoski
     *
     * @param word - (String) słowo do rozdzielenia
     */

    private void splitWord(String word) {
        splitted = new Vector<String>();
        for (String i : word.split("")) {
            splitted.add(i);
        }
        splitted = mergeLetters(splitted, 'c', 'h');
        splitted = mergeLetters(splitted, 'r', 'z');
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na znalezienie możliwych słów
     *
     * @param letterA - (String) pierwsza głoska
     * @param letterB - (String) druga głoska
     */

    private void generatePossible(String letterA, String letterB) {
        String temp = "";
        for (int j = 0; j < splitted.size(); j++) {
            temp = "";
            if (splitted.get(j).contains(letterA)) {
                for (int z = 0; z < j; z++)
                    temp += splitted.get(z);
                temp += letterB;
                for (int z = j + 1; z < splitted.size(); z++)
                    temp += splitted.get(z);
                if (!possible.contains(temp)) {
                    possible.add(temp);
                }
            }
        }
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na znalezienie wszystkich możliwych słów
     *
     * @param word - (String) słowo początkowe
     */

    private void generateAllPossible(String word) {
        possible = new Vector<String>();
        possible.add(word);
        int TempCount = 1;
        if (!findWord(word)) {
            for (int i = 0; i < TempCount; i++) {
                splitWord(possible.get(i));
                for (int k = 0; k < letters.length; k++) {
                    generatePossible(letters[k][0], letters[k][1]);
                    generatePossible(letters[k][1], letters[k][0]);
                }
                TempCount = possible.size();
                if (TempCount > 2048) {
                    break;
                }
            }
        }
    }

    /**
     * Metoda klasy AutoCorrectDecorator pozwalająca na poprawienie pojedynczego słowa
     *
     * @return (String) - pierwsze możliwe słowo znalezione w słowniku
     */

    private String checkPossible() {

        for (String i : possible) {
            if (findWord(i)) {
                return i;
            }
        }
        return possible.get(0);
    }

    public void setText(String text) {
        this.text = text;
    }
}

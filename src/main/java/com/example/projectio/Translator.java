package com.example.projectio;

import com.example.projectio.Data.Shortcut;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static java.lang.Character.isUpperCase;


/**
 * Klasa Translator udostępniająca metody do przetwarzania tekstów
 *
 * @author Dominik, Krzysztof, Mariusz, Szymon
 */

public class Translator {

    private static final String[] FIRST = new String[]{"zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
    private static final String[] SECOND = new String[]{"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście",
            "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
    private static final String[] first_float = {"", "jedna", "dwie", "trzy", "cztery", "pięć", "sześć",
            "siedem", "osiem", "dziewieć"};
    private static final String[] second_float = {"dziesięć", "jedynaście", "dwanaście", "trzynaście", "czternaście",
            "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};

    /**
     * Metoda klasy Translator pozwalająca na zmianę wielkość liter na małe
     *
     * @param text - (String) tekst do translacji
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest mała
     */

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    /**
     * Metoda klasy Translator pozwalająca na zmianę wielkość liter na wielkie
     *
     * @param text - (String) tekst do translacji
     * @return (String) tekst po zastosowaniu translacji w którym każda litera jest wielka
     */

    public static String toUpperCase(String text) {
        return text.toUpperCase();
    }

    /**
     * Metoda służąca do obracania tekstu przekazanego jako parametr
     * z zachowaniem wielkości znaków na odpowiednich pozycjach
     *
     * @param text - (String) tekst do obrócenia
     * @return (String) tekst po obróceniu
     */

    public static String toInverse(String text) {
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

    /**
     * Metoda klasy Translator pozwalająca na zmianę welkości pierwszej litery
     * każdego wyrazu w zdaniu na wielką
     *
     * @param text - (String) tekst do translacji
     * @return (String) tekst po zastosowaniu translacji w którym pierwsza litera każdego wyrazu jest wielka
     */

    public static String toCapitalize(String text) {
        text = text.toLowerCase();

        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length; i++)
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        return result.toString().substring(0, result.length() - 1);
    }

    private static final String[] OTHERS = {"dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
            "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
    private static final String[] HUNDRETS = {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
            "osiemset", "dziewięćset"};

    /**
     * Metoda klasy Translator pozwalająca na rozwijanie podstawowych skrótów
     *
     * @param text - (String) tekst do translacji
     * @return (String) tekst po zastosowaniu translacji w którym podane poniżej skróty
     * zostają zastąpione przez ich pełne rozwinięcia
     */

    public static String expandShortcuts(String text) {
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

    /**
     * Metoda klasy Translator pozwalająca na zamianę liczb pisanych cyframi na ich słowną reprezentacje
     *
     * @param zdanie - (String) tekst do translacji
     * @return (String) tekst po zastosowaniu translacji w którym wszystkie liczby pisane cyframi zostają
     * zostają zastąpione przez ich słowne reprezentacje np. 100 - sto
     */


    public static String expandNumbers(String zdanie) {
        String[] arr = zdanie.split(" ", 0);

        String x = "";
        int i = 0;
        for (String w : arr) {

            try {
                if (Integer.valueOf(w) >= 0 && Integer.valueOf(w) < 1000) {
                    x += " " + change_on_word_pol(w);
                } else x += " " + w;
            } catch (NumberFormatException e) {
                if (w.contains(".")) {
                    try {
                        float liczba = Float.valueOf(w);
                        int l = (int) Math.floor(liczba);
                        int przecinek = Math.round((liczba - (float) l) * 100.f);
                        if (i == 0) {
                            x += change_on_word_pol(String.valueOf(l));
                            if (przecinek > 0) {
                                x += " i " + change_float_pol(przecinek);
                            }
                        } else {
                            x += " " + change_on_word_pol(String.valueOf(l));
                            if (przecinek > 0) {
                                x += " i " + change_float_pol(przecinek);
                            }
                        }
                    } catch (NumberFormatException exception) {
                        if (i == 0) {
                            x += w;
                        } else {
                            x += " " + w;
                        }
                    }
                } else if (i == 0) {
                    x += w;
                } else {
                    x += " " + w;
                }
            }

            i++;
        }
        return x;
    }

    private static String change_on_word_pol(String w) {

        String x = "";
        int number = Integer.valueOf(w);

        if (number < 10) {
            x += FIRST[number];
            return x;
        } else if (number < 20) {

            x += SECOND[number - 10];
            return x;
        } else if (number % 10 == 0 && (String.valueOf(number).length()) < 3) {
            x += OTHERS[Integer.valueOf((number / 10) - 2)];
            return x;
        } else if (number % 10 != 0 && (String.valueOf(number).length()) < 3) {
            x += OTHERS[Integer.valueOf((number / 10) - 2)];
            x += " " + FIRST[Integer.valueOf((number % 10))];
            return x;
        } else if (number % 10 == 0 && String.valueOf(number).length() == 3 && number % 100 == 0) {

            x += HUNDRETS[Integer.valueOf((number / 100) - 1)];   //pięćset itp.
            return x;
        } else {
            x += HUNDRETS[Integer.valueOf((number / 100) - 1)];

            if (number % 10 == 0) {
                x += " " + OTHERS[Integer.valueOf((number % 100) / 10 - 2)];
                return x;
            } else if (Integer.valueOf(number / 10) % 10 == 0) {
                x += " " + change_on_word_pol(String.valueOf(number % 10));
                return x;
            } else if (Integer.valueOf(number / 10) % 10 == 1) {
                x += " " + change_on_word_pol(String.valueOf(number % 100));
                return x;
            } else {
                x += " " + OTHERS[(number / 10) % 10 - 2];
                x += " " + change_on_word_pol(String.valueOf(number % 10));
                return x;
            }
        }
    }

    /**
     * Metoda klasy translator służąca do zamiany części przecinkowych liczby na słowa
     *
     * @param number - (int) części setne liczby
     * @return (String) odpowiadający podanym częsciom setnym słowny opis
     */
    private static String change_float_pol(int number) {
        int jednosci = number % 10;
        int dziesietki = number / 10;
        if (dziesietki < 1 && jednosci == 1) {
            return first_float[jednosci] + " setna";
        } else if (dziesietki < 1 && jednosci < 5) {
            return first_float[jednosci] + " setne";
        } else if (dziesietki < 1) {
            return first_float[jednosci] + " setnych";
        } else if (dziesietki < 2) {
            return second_float[jednosci] + " setnych";
        } else {
            if (jednosci == 0) {
                return OTHERS[dziesietki - 2] + " setnych";
            } else if (jednosci == 1) {
                return OTHERS[dziesietki - 2] + " jeden setnych";
            } else {
                return OTHERS[dziesietki - 2] + " " + first_float[jednosci] + " setnych";
            }
        }
    }


    /**
     * Metoda służąca do rozwijania zdefiniowanych przez użytkownika skrótów
     * w tekście przekazywanym jako parametr.
     *
     * @param text - (String) tekst, w którym sktóty mają zostać rozwinięte
     * @return (String) tekst po rozwinięciu skrótów
     */
    public String expandMyShortcuts(String text) {
        JdbcTemplate jdbcTemplate = ProjectioApplication.getJdbcTemplate();
        List<Shortcut> shortcuts = jdbcTemplate.query("SELECT * FROM SKROTY",
                (rs, arg1) -> {
                    return new Shortcut(rs.getString("SKROT"), rs.getString("ROZWINIECIE"));
                });
        if (shortcuts.size() > 0) {
            for (Shortcut shortcut : shortcuts) {
                text = text.replace(shortcut.getShortcut(), shortcut.getExpandedShortcut());
            }
        }
        return text;

    }


    /**
     * Metoda służąca do usuwania powtórzeń w zdaniu
     *
     * @param text - (String) tekst w którym mają zostać usunięte powtórzenia
     * @return (String) tekst po usunięciu powtórzeń
     */

    public static String delRepeatWords(String text)
    {

        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length - 1; i++)
        {
            if(words[i].equals(words[i+1]))
            {
                words[i+1] = "del";
            }

        }

        for(int i = 0; i < words.length; i++) {
            if(words[i].equals("del"))
                continue;
            result.append(words[i]).append(" ");

        }

            return result.toString().substring(0, result.length() - 1);
    }

}

package com.example.projectio.Decorators;

public class ExpandNumbersDecorator extends Decorator {

    private static final String[] FIRST = new String[]{"zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
    private static final String[] SECOND = new String[]{"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście",
            "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
    private static final String[] OTHERS = {"dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
            "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
    private static final String[] HUNDRETS = {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
            "osiemset", "dziewięćset"};

    private static final String[] first_float = {"", "jedna", "dwie", "trzy", "cztery", "pięć", "sześć",
            "siedem", "osiem", "dziewieć"};
    private static final String[] second_float = {"dziesięć", "jedynaście", "dwanaście", "trzynaście", "czternaście",
            "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};

    private static final String[] FIRST_TEN = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] SECOND_TEN = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] OTHER_TENS = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"};
    private static final String HUNDRED = "hundred";

    private String text;

    private String language;

    public ExpandNumbersDecorator(String text, String language) {
        this.text = text;
        this.language = language;
    }

    /**
     * Metoda klasy translator służąca do zamiany danej liczby na słowo
     *
     * @param w - (String) dana liczba
     * @return (String) słowny opis liczby w języku angielskim
     */

    private static String change_on_word_eng(String w) {

        String x = "";
        int number = Integer.valueOf(w);

        if (number < 10) {

            x += FIRST_TEN[number];
            return x;
        } else if (number < 20) {

            x += SECOND_TEN[number - 10];
            return x;
        } else if (number % 10 == 0 && (String.valueOf(number).length()) < 3) {
            x += OTHER_TENS[Integer.valueOf((number / 10) - 2)];
            return x;
        } else if (number % 10 != 0 && (String.valueOf(number).length()) < 3) {
            x += OTHER_TENS[Integer.valueOf((number / 10) - 2)];
            x += " " + FIRST_TEN[Integer.valueOf((number % 10))];
            return x;
        } else if (number % 10 == 0 && String.valueOf(number).length() == 3 && number % 100 == 0) {

            x += change_on_word_eng(String.valueOf(number / 100));
            x += " " + HUNDRED;
            return x;
        } else {
            x += change_on_word_eng(String.valueOf(number / 100));
            x += " " + HUNDRED;

            if (number % 10 == 0) {
                x += " " + OTHER_TENS[Integer.valueOf((number % 100) / 10 - 2)];
                return x;
            } else if (Integer.valueOf(number / 10) % 10 == 0) {
                x += " " + change_on_word_eng(String.valueOf(number % 10));
                return x;
            } else if (Integer.valueOf(number / 10) % 10 == 1) {
                x += " " + change_on_word_eng(String.valueOf(number % 100));
                return x;
            } else {
                x += " " + OTHER_TENS[(number / 10) % 10 - 2];
                x += " " + change_on_word_eng(String.valueOf(number % 10));
                return x;
            }
        }
    }

    /**
     * Metoda klasy translator służąca do zamiany danej liczby na słowo
     *
     * @param w - (String) dana liczba
     * @return (String) słowny opis liczby w języku polskim
     */

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
     * Metoda klasy Translator pozwalająca na zamianę liczb pisanych cyframi na ich słowną reprezentacje
     *
     * @return (String) tekst po zastosowaniu translacji w którym wszystkie liczby pisane cyframi zostają
     * zostają zastąpione przez ich słowne reprezentacje np. 100 - sto
     */
    @Override
    public String decore() {
        String[] arr = text.split(" ", 0);

        String x = "";
        int i = 0;
        for (String w : arr) {

            try {
                if (Integer.valueOf(w) >= 0 && Integer.valueOf(w) < 1000) {
                    if (language.compareTo("pl") == 0) {
                        if (i == 0) {
                            x += change_on_word_pol(w);
                        } else {
                            x += " " + change_on_word_pol(w);
                        }
                    } else if (language.compareTo("eng") == 0) {
                        if (i == 0) {
                            x += change_on_word_eng(w);
                        } else {
                            x += " " + change_on_word_eng(w);
                        }
                    }
                } else x += " " + w;
            } catch (NumberFormatException e) {
                if (w.contains(".")) try {
                    float liczba = Float.valueOf(w);

                    if (language.compareTo("pl") == 0) {

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
                    } else if (language.compareTo("eng") == 0) {

                        int num = (int) liczba;
                        if (i == 0) {
                            x += change_on_word_eng(String.valueOf(num));  //Zamiana liczby przed przecinkiem
                        } else {

                            x += " " + change_on_word_eng(String.valueOf(num));  //Zamiana liczby przed przecinkiem
                        }

                        liczba = 100 * (liczba - (int) liczba);
                        liczba = Math.round(liczba);
                        if ((int) (liczba / 10) % 10 != 0) {
                            x += " point";
                            x += " " + change_on_word_eng(String.valueOf((int) (liczba / 10) % 10));
                        }
                        if ((int) liczba % 10 != 0) {
                            x += " " + change_on_word_eng(String.valueOf((int) liczba % 10));
                        }
                    }

                } catch (NumberFormatException exception) {
                    if (i == 0) {
                        x += w;
                    } else {
                        x += " " + w;
                    }
                }
                else if (i == 0) {
                    x += w;
                } else {
                    x += " " + w;
                }
            }

            i++;
        }
        return x;

    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParameters(String text, String language) {
        this.text = text;
        this.language = language;
    }
}

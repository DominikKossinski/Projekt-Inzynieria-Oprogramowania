package com.example.projectio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * RestController odpowiadający za tworzenie, usuwanie oraz rozwijanie
 * skrótów, które zdefiniuje użytkownik.
 *
 * @author Dominik
 */
@RestController
public class MyShortcutsRestController {

    /**
     * Metoda klasy MyShortcutsRestController pozwalająca na obsługę rządania
     * rozwinięcia w podanym przez użytkownika tekscie skróty, które wcześniej
     * zdefiniował.
     * Przykładowy URL:
     * http:\\localhost:8080/api/expandMyShortcuts?text=DK mDKab
     *
     * @param text - (String) text podany przez użytkownika
     * @return (String) tekst po rozwinięciu skrótów
     * @see Translator#expandMyShortcuts(String)
     */
    @RequestMapping(value = "/api/expandMyShortcuts", method = RequestMethod.GET)
    public String expandMyShortcuts(@RequestParam(name = "text") String text) {
        return Translator.expandMyShortcuts(text);
    }

    /**
     * Metoda klasy MyShortcutsRestController pozwalająca na obsługę rządania
     * dodania (lub zamiany) skrótu definiowanego przez użytkownika.
     * Przykładowe rządanie:
     * metoda: POST
     * url: http\\localhost:8080/api/createMyShortcut
     * requestBody rodzaj: text/plain
     * requestBody zawartość: DK;Dominik
     *
     * @param replace - (boolean) zmienna potwierdzająca chęć zamiany istniejącego skrótu
     * @param text    - (String) skrót i jego rozwinięcie rozdzielone znakiem ';'
     * @return (String) 'True' w przypadku poprawnego dodania (lub potwierdzonej zamiany) skrótu
     * 'False' w przypadku błędu podczas dodawania skrótu
     * 'Waring REPLACE!' w przypadku wykrycja zamiany bez potwierdzenia
     */
    @RequestMapping(value = "/api/createMyShortcut", method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String createMyShortCut(@RequestParam(name = "replace", defaultValue = "false") boolean replace,
                                   @RequestBody String text) {
        File file = new File("src/main/resources/myShortcuts.json");
        try {
            FileReader reader = new FileReader(file);
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(reader);
            String[] newShortcut = text.split(";");
            for (int i = 0; i < array.size(); i++) {
                JSONObject shortcut = (JSONObject) array.get(i);
                if (shortcut.get("shortcut").toString().compareTo(newShortcut[0]) == 0 && !replace) {
                    return "Waring REPLACE!";
                } else if (shortcut.get("shortcut").toString().compareTo(newShortcut[0]) == 0 && replace) {
                    array.remove(i);
                    shortcut.put("expanded", newShortcut[1]);
                    array.add(shortcut);
                    reader.close();
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter writer = new BufferedWriter(fileWriter);
                    writer.write(array.toJSONString());
                    writer.flush();
                    writer.close();
                    return "True replace";
                }
            }
            JSONObject shortCut = new JSONObject();
            shortCut.put("shortcut", newShortcut[0]);
            shortCut.put("expanded", newShortcut[1]);
            array.add(shortCut);
            reader.close();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(array.toJSONString());
            writer.flush();
            writer.close();

            fileWriter.close();
            return "True";
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "False";
        }
    }

    /**
     * Metoda klasy MyShortcutRestController pozwalająca na usunięcie
     * wcześniej zdefiniowanego przez użytkownika skrótu.
     * Przykładowe rządanie:
     * metoda: DELETE
     * url: http:\\localhost:8080/api/deleteMyShortcut/DK
     *
     * @param shortcut - (String) skrót, który użytkownik chce usunąć
     * @return (String) - 'True'  w przypadku poprawnego usunięcia skrótu
     * 'False' w przypadku błędu podczas usuwania skrótu
     * 'NO SHORTCUT <code>shortcut</code> FOUND' w przypadku, gdy podany skrót nie istnieje
     */
    @RequestMapping(value = "/api/deleteMyShortcut/{shortcut}", method = RequestMethod.DELETE)
    public String deleteMyShortcut(@PathVariable("shortcut") String shortcut) {
        File file = new File("src/main/resources/myShortcuts.json");
        try {
            FileReader reader = new FileReader(file);
            JSONParser parser = new JSONParser();
            JSONArray shortCuts = (JSONArray) parser.parse(reader);
            reader.close();
            boolean found = false;
            int i;
            for (i = 0; i < shortCuts.size(); i++) {
                JSONObject jsonObject = (JSONObject) shortCuts.get(i);
                if (jsonObject.get("shortcut").toString().compareTo(shortcut) == 0) {
                    found = true;
                    break;
                }
            }
            if (found) {
                shortCuts.remove(i);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write(shortCuts.toJSONString());
                writer.flush();
                writer.close();
                fileWriter.close();
                return "True";
            } else {
                return "NO SHORTCUT " + shortcut + " FOUND";
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "False";
        }
    }
}

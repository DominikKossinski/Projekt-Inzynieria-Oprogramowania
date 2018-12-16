package com.example.projectio.RestControllers;

import com.example.projectio.Decorators.Decorator;
import com.example.projectio.Decorators.ExpandMyShortCutsDecorator;
import com.example.projectio.ProjectioApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * RestController odpowiadający za tworzenie, usuwanie oraz rozwijanie
 * skrótów, które zdefiniuje użytkownik.
 *
 * @author Dominik
 */
@RestController
public class MyShortcutsRestController {

    /**
     * Logger używany do informowania o wykonujących się czynnościach.
     */
    private static final Logger logger = LoggerFactory.getLogger(MyShortcutsRestController.class);

    /**
     * Zmienna używana do wysyłania żądań do bazy danych.
     */
    private JdbcTemplate jdbcTemplate = ProjectioApplication.getJdbcTemplate();

    /**
     * Metoda klasy MyShortcutsRestController pozwalająca na obsługę żądania
     * rozwinięcia w podanym przez użytkownika tekscie skróty, które wcześniej
     * zdefiniował.
     * Przykładowy URL:
     * http:\\localhost:8080/api/expandMyShortcuts?text=DK mDKab
     *
     * @param text - (String) text podany przez użytkownika
     * @return (String) tekst po rozwinięciu skrótów
     * @see ExpandMyShortCutsDecorator#decore()
     */
    @RequestMapping(value = "/api/expandMyShortcuts", method = RequestMethod.GET)
    public String expandMyShortcuts(@RequestParam(name = "text") String text) {
        Decorator decorator = new ExpandMyShortCutsDecorator(text);
        String toReturn = decorator.decore();
        return toReturn;
    }

    /**
     * Metoda klasy MyShortcutsRestController pozwalająca na obsługę żądania
     * dodania (lub zamiany) skrótu definiowanego przez użytkownika.
     * Przykładowe żądanie:
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
        String[] newShortcut = text.split(";");
        int sq = jdbcTemplate.queryForObject("SELECT COUNT(ROZWINIECIE) FROM SKROTY WHERE SKROT LIKE '"
                + newShortcut[0] + "'", Integer.class);
        if (!replace && sq == 1) {
            logger.warn("Create MyShortcut: Warn Replace by " + newShortcut[0]);
            return "Waring REPLACE!";
        } else if (replace && sq == 1) {
            int modified = jdbcTemplate.update("UPDATE SKROTY SET ROZWINIECIE = '" + newShortcut[1] +
                    "' WHERE SKROT = '" + newShortcut[0] + "'");
            if (modified == 1) {
                return "True replace";
            } else {
                logger.error("Create MyShortcut: Error by " + newShortcut[0]);
                return "False";
            }
        } else if (sq == 0) {
            int modified = jdbcTemplate.update("INSERT INTO SKROTY(SKROT, ROZWINIECIE) VALUES ('"
                    + newShortcut[0] + "', '" + newShortcut[1] + "')");
            if (modified == 1) {
                return "True";
            } else {
                logger.error("Create MyShortcut: Error by " + newShortcut[0]);
                return "False";
            }
        } else {
            logger.error("Create MyShortcut: Error by " + newShortcut[0]);
            return "False";
        }
    }

    /**
     * Metoda klasy MyShortcutRestController pozwalająca na usunięcie
     * wcześniej zdefiniowanego przez użytkownika skrótu.
     * Przykładowe żądanie:
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
        int modified = jdbcTemplate.update("DELETE FROM SKROTY WHERE SKROT LIKE '" + shortcut + "'");
        if (modified == 0) {
            logger.debug("Delete MyShortcut: NO SHORTCUT " + shortcut + " FOUND");
            return "NO SHORTCUT " + shortcut + " FOUND";
        } else if (modified == 1) {
            logger.debug("Delete MyShortcut: DELETED " + shortcut);
            return "True";
        } else {
            logger.warn("Delete MyShortcut: Error by " + shortcut);
            return "False";
        }
    }

}

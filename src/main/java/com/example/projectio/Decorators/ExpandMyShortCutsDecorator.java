package com.example.projectio.Decorators;

import com.example.projectio.Data.Shortcut;
import com.example.projectio.ProjectioApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Klasa pozwalająca na używanie własnych skrótów
 *
 * @author Dominik
 */

public class ExpandMyShortCutsDecorator extends Decorator {

    /**
     * Dekorator pole wykozystywane w implementacji wzorca dekorator
     */
    private Decorator decorator = null;

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr inny dekorator (implementacja wzorca Dekorator)
     *
     * @param decorator - dekorator
     */
    public ExpandMyShortCutsDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * Publiczny konstruktor klasy przyjmujący jako parametr tekst wprowadzony przez użytkownika
     * @param text - tekst wprowadzony przez użytkownika
     */
    public ExpandMyShortCutsDecorator(String text) {
        this.text = text;
    }

    /**
     * Metoda służąca do rozwijania zdefiniowanych przez użytkownika skrótów
     * w tekście przekazywanym jako parametr.
     *
     * @return (String) tekst po rozwinięciu skrótów
     */
    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
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
}

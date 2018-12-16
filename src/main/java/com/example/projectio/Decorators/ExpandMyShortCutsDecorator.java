package com.example.projectio.Decorators;

import com.example.projectio.Data.Shortcut;
import com.example.projectio.ProjectioApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ExpandMyShortCutsDecorator extends Decorator {

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

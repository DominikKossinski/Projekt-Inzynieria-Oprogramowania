package com.example.projectio.Data;

public class Shortcut {
    private String shortcut;
    private String expandedShortcut;

    public Shortcut(String shortcut, String expandedShortcut) {
        this.shortcut = shortcut;
        this.expandedShortcut = expandedShortcut;
    }

    public String getExpandedShortcut() {
        return expandedShortcut;
    }

    public String getShortcut() {
        return shortcut;
    }
}

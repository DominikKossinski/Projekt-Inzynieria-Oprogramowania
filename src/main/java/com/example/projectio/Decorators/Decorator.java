package com.example.projectio.Decorators;

public abstract class Decorator implements TextInterface {

    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.example.projectio;

import com.example.projectio.Decorators.AutoCorrectDecorator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AutocorrectTest {

    public AutoCorrectDecorator autoDecorator;

    @BeforeClass
    public static void loadDict() {
        AutoCorrectDecorator.init("slowa.txt");
    }

    @Before
    public void setUp() {
        autoDecorator = new AutoCorrectDecorator("");
    }

    @Test
    public void emptyStringTest() {
        autoDecorator.setText("");
        assertEquals("", autoDecorator.decore());
    }

    @Test
    public void correctWordTest() {
        autoDecorator.setText("gżegżółka");
        assertEquals("gżegżółka ", autoDecorator.decore());
    }

    @Test
    public void incorrectWordTest() {
        autoDecorator.setText("grzegrzulka");
        assertEquals("gżegżółka ", autoDecorator.decore());
    }

    @Test
    public void incorrectSenetenceTest() {
        autoDecorator.setText("grzegrzulka jest ruda");
        assertEquals("gżegżółka jest ruda ", autoDecorator.decore());
    }

    @Test
    public void correctSenetenceTest() {
        autoDecorator.setText("kaczka nosi okulary");
        assertEquals("kaczka nosi okulary ", autoDecorator.decore());
    }

    @Test
    public void nonExistingWordTest() {
        autoDecorator.setText("gkdsfkgjsdfhglk");
        assertEquals("gkdsfkgjsdfhglk ", autoDecorator.decore());
    }
}
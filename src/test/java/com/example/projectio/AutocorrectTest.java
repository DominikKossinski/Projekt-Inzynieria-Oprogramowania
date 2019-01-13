package com.example.projectio;

import com.example.projectio.Decorators.AutoCorrectDecorator;
import com.example.projectio.Decorators.DeleteRepeatWordsDecorator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AutocorrectTest {

    public AutoCorrectDecorator autoDecorator;
    private DeleteRepeatWordsDecorator deleteRepeatWordsDecorator;


    @BeforeClass
    public static void loadDict() {
        AutoCorrectDecorator.init("slowa.txt");
    }

    @Before
    public void setUp() {
        deleteRepeatWordsDecorator = new DeleteRepeatWordsDecorator("muj muj");
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
    public void multiIncorrectTest() {
        autoDecorator.setText("muj wójek byl rzolnieżem");
        assertEquals("mój wujek był żołnierzem ", autoDecorator.decore());
    }

    @Test
    public void delAndCorrectTest() {
        assertEquals("muj", deleteRepeatWordsDecorator.decore());
        autoDecorator.setText(deleteRepeatWordsDecorator.decore());
        assertEquals("mój ", autoDecorator.decore());
    }

    @Test
    public void correctAndDelTesy() {
        autoDecorator.setText("muj mój");
        assertEquals("mój mój ", autoDecorator.decore());
        deleteRepeatWordsDecorator.setText(autoDecorator.decore());
        assertEquals("mój", deleteRepeatWordsDecorator.decore());
    }

    @Test
    public void nonExistingWordTest() {
        autoDecorator.setText("gkdsfkgjsdfhglk");
        assertEquals("gkdsfkgjsdfhglk ", autoDecorator.decore());
    }
}
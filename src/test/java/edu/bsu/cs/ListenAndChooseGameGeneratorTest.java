package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListenAndChooseGameGeneratorTest {
    @Test
    void testSpokenWordChosen() {
        ListenAndChooseGameGenerator gameGen = new ListenAndChooseGameGenerator();
        Assertions.assertNotEquals(null, gameGen.getSpokenWord());
    }

    @Test
    void testResponseOptionsChosen() {
        ListenAndChooseGameGenerator gameGen = new ListenAndChooseGameGenerator();
        Assertions.assertEquals(4, gameGen.getResponseOptions().length);
    }

    @Test
    void testSpokenWordInOptions() {
        ListenAndChooseGameGenerator gameGen = new ListenAndChooseGameGenerator();
        Assertions.assertTrue(gameGen.isWordInArray(gameGen.getSpokenWord(), gameGen.getResponseOptions()));
    }
}

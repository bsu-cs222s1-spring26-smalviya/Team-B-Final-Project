package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class RhymingWordsGameGeneratorTest {

    @Test
    void testResponseOptionsLength() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        assertEquals(3, game.getResponseOptions().length);
    }

    @Test
    void testCorrectIndexRange() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        int index = game.getCorrectIndex();
        assertTrue(index >= 0 && index < 3);
    }

    @Test
    void testNoNullOptions() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        String[] options = game.getResponseOptions();

        for (String option : options) {
            assertNotNull(option);
        }
    }

    @Test
    void testNoDuplicateOptions() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        String[] options = game.getResponseOptions();

        Set<String> unique = new HashSet<>();
        for (String option : options) {
            unique.add(option);
        }

        assertEquals(3, unique.size());
    }

    @Test
    void testCorrectAnswerExistsInOptions() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        String[] options = game.getResponseOptions();
        int correctIndex = game.getCorrectIndex();

        assertTrue(correctIndex >= 0 && correctIndex < options.length);
        assertNotNull(options[correctIndex]);
    }

    @Test
    void testTargetWordNotNull() {
        RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
        assertNotNull(game.getTargetWord());
    }

    @Test
    void testMultipleGameGenerations() {
        for (int i = 0; i < 50; i++) {
            RhymingWordsGameGenerator game = new RhymingWordsGameGenerator();
            assertEquals(3, game.getResponseOptions().length);
            assertTrue(game.getCorrectIndex() >= 0 && game.getCorrectIndex() < 3);
        }
    }
}
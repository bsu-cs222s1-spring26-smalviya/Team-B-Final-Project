package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RhymingWordsTest {

    @Test
    void testInitialScoreIsZero() {
        RhymingWords game = new RhymingWords();
        assertEquals(0, game.score);
    }

    @Test
    void testRoundsPlayedInitiallyZero() {
        RhymingWords game = new RhymingWords();
        assertEquals(0, game.roundsPlayed);
    }

    @Test
    void testGameObjectCreation() {
        RhymingWords game = new RhymingWords();
        assertNotNull(game);
    }

    @Test
    void testMultipleGameInstances() {
        for (int i = 0; i < 10; i++) {
            RhymingWords game = new RhymingWords();
            assertEquals(0, game.score);
            assertEquals(0, game.roundsPlayed);
        }
    }
}
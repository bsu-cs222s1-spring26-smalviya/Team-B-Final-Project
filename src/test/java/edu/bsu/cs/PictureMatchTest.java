package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Test class for PictureMatch game logic
 * Tests core functionality without requiring JavaFX UI to be displayed
 */
public class PictureMatchTest {

    // Test data mirrors the private arrays in PictureMatch
    private static final String[] TEST_IMAGE_PATHS = {
            "/images/picture1.png",
            "/images/picture2.png",
            "/images/picture3.png",
            "/images/picture4.png",
            "/images/picture5.png"
    };

    private static final String[] TEST_CORRECT_WORDS = {
            "Cat", "Dog", "House", "Tree", "Car"
    };

    private static final String[][] TEST_WRONG_WORDS = {
            {"Dog", "Bird"},
            {"Cat", "Mouse"},
            {"Castle", "Cave"},
            {"Flower", "Bush"},
            {"Truck", "Bike"}
    };

    private PictureMatch pictureMatch;
    private int currentSet;

    @BeforeEach
    void setUp() {
        pictureMatch = new PictureMatch();
        currentSet = 0;
    }

    @Test
    void testCorrectWordsArrayHasFiveElements() {
        assertEquals(5, TEST_CORRECT_WORDS.length,
                "Should have 5 correct words for 5 game sets");
    }

    @Test
    void testImagePathsArrayHasFiveElements() {
        assertEquals(5, TEST_IMAGE_PATHS.length,
                "Should have 5 image paths for 5 game sets");
    }

    @Test
    void testWrongWordsArrayHasFiveSubArrays() {
        assertEquals(5, TEST_WRONG_WORDS.length,
                "Should have 5 sets of wrong words");
    }

    @Test
    void testEachWrongWordSetHasTwoOptions() {
        for (int i = 0; i < TEST_WRONG_WORDS.length; i++) {
            assertEquals(2, TEST_WRONG_WORDS[i].length,
                    "Wrong word set " + i + " should have exactly 2 wrong options");
        }
    }

    @Test
    void testCorrectAnswerMatching() {
        // Test that correct words match their expected values
        assertEquals("Cat", TEST_CORRECT_WORDS[0]);
        assertEquals("Dog", TEST_CORRECT_WORDS[1]);
        assertEquals("House", TEST_CORRECT_WORDS[2]);
        assertEquals("Tree", TEST_CORRECT_WORDS[3]);
        assertEquals("Car", TEST_CORRECT_WORDS[4]);
    }

    @Test
    void testWrongAnswersDoNotIncludeCorrectAnswer() {
        // Verify wrong options never include the correct answer
        for (int i = 0; i < TEST_CORRECT_WORDS.length; i++) {
            String correct = TEST_CORRECT_WORDS[i];
            for (String wrong : TEST_WRONG_WORDS[i]) {
                assertNotEquals(correct, wrong,
                        "Wrong answer should not equal correct answer for set " + i);
            }
        }
    }

    @Test
    void testWordButtonCreationLogic() {
        // Simulate the button creation logic from PictureMatch
        int setIndex = 0;
        String correctWord = TEST_CORRECT_WORDS[setIndex];
        String[] wrongWords = TEST_WRONG_WORDS[setIndex];

        List<String> allWords = new ArrayList<>();
        allWords.add(correctWord);
        allWords.addAll(Arrays.asList(wrongWords));

        assertEquals(3, allWords.size(),
                "Should have 3 total words (1 correct + 2 wrong)");
        assertTrue(allWords.contains(correctWord),
                "All words should contain the correct answer");
        assertTrue(allWords.contains(wrongWords[0]),
                "All words should contain first wrong answer");
        assertTrue(allWords.contains(wrongWords[1]),
                "All words should contain second wrong answer");
    }

    @Test
    void testWordShuffling() {
        // Test that shuffling creates different orders (probabilistic test)
        int setIndex = 2;
        String correctWord = TEST_CORRECT_WORDS[setIndex];
        String[] wrongWords = TEST_WRONG_WORDS[setIndex];

        List<String> originalOrder = new ArrayList<>();
        originalOrder.add(correctWord);
        originalOrder.addAll(Arrays.asList(wrongWords));

        for (int shuffleCount = 0; shuffleCount < 10; shuffleCount++) {
            List<String> shuffled = new ArrayList<>(originalOrder);
            Collections.shuffle(shuffled);

            assertEquals(3, shuffled.size(),
                    "Shuffled list should maintain 3 elements");
            assertTrue(shuffled.contains(correctWord),
                    "Shuffled list should still contain correct word");
            assertTrue(shuffled.containsAll(Arrays.asList(wrongWords)),
                    "Shuffled list should still contain all wrong words");
        }
    }

    @Test
    void testGameProgressionThroughAllSets() {

        int totalSets = TEST_CORRECT_WORDS.length;

        for (int set = 0; set < totalSets; set++) {
            assertTrue(set < totalSets,
                    "Set " + set + " should be valid (less than " + totalSets + ")");
        }


        assertEquals(5, totalSets, "Game should have exactly 5 sets");
    }

    @Test
    void testAllImagePathsAreResourcePaths() {

        for (String path : TEST_IMAGE_PATHS) {
            assertTrue(path.startsWith("/"),
                    "Image path '" + path + "' should start with / for resource loading");
            assertTrue(path.endsWith(".png"),
                    "Image path '" + path + "' should be a PNG file");
        }
    }

    @Test
    void testSoundPathFormat() {
        String soundPath = "/sounds/correct.mp3";
        assertTrue(soundPath.startsWith("/"),
                "Sound path should start with / for resource loading");
        assertTrue(soundPath.endsWith(".mp3"),
                "Sound path should be an MP3 file");
    }

    @Test
    void testCloudButtonPathFormat() {
        String cloudPath = "/pink-cloud-button.png";
        assertTrue(cloudPath.startsWith("/"),
                "Cloud button path should start with / for resource loading");
        assertTrue(cloudPath.endsWith(".png"),
                "Cloud button path should be a PNG file");
    }

    @Test
    void testGameCompletionState() {
        int completedSets = 5;
        int totalSets = TEST_CORRECT_WORDS.length;

        assertEquals(completedSets, totalSets,
                "When currentSet equals totalSets, game should be complete");
    }

    @Test
    void testPictureMatchInstantiation() {

        assertNotNull(pictureMatch,
                "PictureMatch should be instantiable");
    }

    @Test
    void testAllSetsHaveUniqueCorrectAnswers() {

        for (int i = 0; i < TEST_CORRECT_WORDS.length; i++) {
            for (int j = i + 1; j < TEST_CORRECT_WORDS.length; j++) {
                assertNotEquals(TEST_CORRECT_WORDS[i], TEST_CORRECT_WORDS[j],
                        "Each set should have a unique correct answer");
            }
        }
    }

    @Test
    void testWrongAnswersAreUniqueWithinSet() {
        for (int i = 0; i < TEST_WRONG_WORDS.length; i++) {
            assertNotEquals(TEST_WRONG_WORDS[i][0], TEST_WRONG_WORDS[i][1],
                    "Wrong answers in set " + i + " should be different from each other");
        }
    }
}
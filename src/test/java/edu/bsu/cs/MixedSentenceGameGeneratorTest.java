package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MixedSentenceGameGeneratorTest {
    @Test
    void testIsShuffledSentenceFourWordsLong(){
        MixedSentenceGameGenerator gameGen = new MixedSentenceGameGenerator();
        Assertions.assertEquals(4, gameGen.getShuffledSentence().length);
    }

    @Test
    void testIsReturningSentenceAsString() {
        MixedSentenceGameGenerator gameGen = new MixedSentenceGameGenerator();
        Assertions.assertNotEquals(null, gameGen.getOriginalSentenceAsString());
    }
}

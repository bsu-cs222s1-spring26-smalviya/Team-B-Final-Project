package edu.bsu.cs;

import java.util.Arrays;

public class MixedSentenceGameGenerator {
    final private String[][] FULL_SENTENCES = {
            {"The", "brown", "dog", "barked"},
            {"The", "yellow", "bird", "flew"},
            {"I", "found", "a", "frog"},
            {"The", "pillow", "was", "soft"},
            {"I", "went", "to", "school"},
            {"I", "am", "learning", "English"},
            {"The", "brown", "bat", "flew"},
            {"The", "big", "bear", "slept"},
            {"The", "mop", "was", "wet"},
    };

    private String[] originalSentence;
    private String[] shuffledSentence;

    public MixedSentenceGameGenerator() {
        originalSentence = FULL_SENTENCES[(int) (Math.random() * FULL_SENTENCES.length)];
        shuffledSentence = shuffleSentence(originalSentence);
    }

    private String[] shuffleSentence(String[] sentence) {
        String[] sentenceToReturn = {null, null, null, null};

        for (int i=0; i<4; i++) {
            String randomWord = sentence[(int) (Math.random() * sentence.length)];
            if (isWordInArray(randomWord, sentenceToReturn)) {
                i -= 1;
            }
            else {
                sentenceToReturn[i] = randomWord;
            }
        }

        if (Arrays.equals(shuffledSentence, sentence)) {
            return shuffleSentence(sentence);
        }
        else {
            return sentenceToReturn;
        }
    }

    public boolean isWordInArray(String wordToCheck, String[] array) {
        for (String word : array) {
            if (word != null){
                if (word.equals(wordToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getOriginalSentenceAsString() {
        String strToReturn = "";

        for (int i=0; i<4; i++) {
            strToReturn += originalSentence[i];

            if (i != 3) {
                strToReturn += " ";
            }
        }

        return strToReturn;
    }

    public String[] getShuffledSentence() {
        return shuffledSentence;
    }
}

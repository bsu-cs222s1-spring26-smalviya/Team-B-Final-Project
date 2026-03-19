package edu.bsu.cs;

public class ListenAndChooseGameGenerator {
    final private String[] WORD_LIST = {
            "CAT", "BAT", "RAT", "SAT",
            "LOG", "DOG", "BOG", "FROG",
            "FAR", "CAR", "STAR", "TAR",
            "ME", "SHE", "HE", "TEA",
            "TAB", "DRAB", "FAB", "LAB"
    };
    private final String spokenWord;
    private final String[] responseOptions = new String[4];

    public ListenAndChooseGameGenerator() {
        spokenWord = WORD_LIST[(int)(Math.random() * WORD_LIST.length)];
        selectResponseOptions();
    }

    private void selectResponseOptions() {
        for (int i=0; i<4; i++) {
            responseOptions[i] = getAvailableWord();
        }

        responseOptions[(int)(Math.random() * 4)] = spokenWord;
    }

    private String getAvailableWord() {
        String wordContender = WORD_LIST[(int)(Math.random() * WORD_LIST.length)];

        if (wordContender.equals(spokenWord)) {
            return getAvailableWord();
        }

        if (isWordInArray(wordContender, responseOptions)) {
            return getAvailableWord();
        }

        return wordContender;
    }

    private boolean isWordInArray(String wordToCheck, String[] array) {
        for (String word : array) {
            if (word != null){
                if (word.equals(wordToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getSpokenWord() {
        return spokenWord;
    }

    public String[] getResponseOptions() {
        return responseOptions;
    }
}

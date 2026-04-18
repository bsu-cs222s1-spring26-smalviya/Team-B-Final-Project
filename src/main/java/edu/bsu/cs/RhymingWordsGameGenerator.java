package edu.bsu.cs;

public class RhymingWordsGameGenerator {

    private final String[][] RHYME_GROUPS = {
            {"CAT", "BAT", "HAT", "MAT"},
            {"DOG", "LOG", "FOG", "HOG"},
            {"BALL", "WALL", "TALL", "FALL"},
            {"SUN", "RUN", "FUN", "BUN"},
            {"BEE", "TREE", "FREE", "SEE"},
            {"CAKE", "BAKE", "LAKE", "MAKE"},
            {"MOON", "SPOON", "TUNE", "SOON"},
            {"STAR", "CAR", "FAR", "JAR"},
            {"BLUE", "SHOE", "FLEW", "GLUE"},
            {"BED", "RED", "FED", "SLED"},
            {"HOUSE", "MOUSE", "LOUSE", "BLOUSE"},
            {"PLAY", "DAY", "SAY", "WAY"}
    };

    private final String targetWord;
    private final String[] responseOptions = new String[3];
    private final int correctIndex;

    public RhymingWordsGameGenerator() {
        String[] group = RHYME_GROUPS[(int)(Math.random() * RHYME_GROUPS.length)];
        targetWord = group[0];

        String correctAnswer = group[1 + (int)(Math.random() * (group.length - 1))];
        correctIndex = (int)(Math.random() * 3);

        for (int i = 0; i < 3; i++) {
            if (i == correctIndex) {
                responseOptions[i] = correctAnswer;
            } else {
                responseOptions[i] = getNonRhymingWord(group);
            }
        }
    }

    private String getNonRhymingWord(String[] excludeGroup) {
        String[] candidateGroup = RHYME_GROUPS[(int)(Math.random() * RHYME_GROUPS.length)];
        String candidate = candidateGroup[(int)(Math.random() * candidateGroup.length)];

        for (String word : excludeGroup) {
            if (word.equals(candidate)) return getNonRhymingWord(excludeGroup);
        }
        for (String word : responseOptions) {
            if (word != null && word.equals(candidate)) return getNonRhymingWord(excludeGroup);
        }
        return candidate;
    }

    public String getTargetWord() { return targetWord; }
    public String[] getResponseOptions() { return responseOptions; }
    public int getCorrectIndex() { return correctIndex; }
}
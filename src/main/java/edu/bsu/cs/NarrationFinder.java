package edu.bsu.cs;

import java.net.URL;

public class NarrationFinder {
    final private String[] STRINGS_TO_REMOVE = {
            ".", ",", "?", "!", ":", "-",
    };

    public String getAudioPathFromString(String string) {
        String pathToSend = "/audio/narration/";

        string = string.toLowerCase();

        for (String stringToRemove : STRINGS_TO_REMOVE) {
            string = string.replace(stringToRemove, "");
        }
        string = string.replace(" ", "-");
        string = string.replace("_____", "blank");

        pathToSend += string + ".mp3";

        try {
            URL soundUrl = getClass().getResource(pathToSend);
            assert soundUrl != null;
            return soundUrl.toString();
        } catch (Exception e) {
            System.out.printf("Audio file \"%s\" could not be found.%n", pathToSend);
            return "";
        }
    }
}

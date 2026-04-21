package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;

public class ListenAndChoose {
    private final NarrationFinder NARRATION_FINDER = new NarrationFinder();
    private final String spokenWord;
    private final String[] responseOptions;
    private String userInput = "";

    Button returnButton = new Button();
    Button listenButton = new Button();
    Button option1Button = new Button();
    Button option2Button = new Button();
    Button option3Button = new Button();
    Button option4Button = new Button();
    Button replayButton = new Button();
    Button instructionListenButton = new Button();
    Label instructionLabel = new Label();
    Label winLabel = new Label();
    Label incorrectLabel = new Label();

    public ListenAndChoose() {
        ListenAndChooseGameGenerator gameGen = new ListenAndChooseGameGenerator();
        spokenWord = gameGen.getSpokenWord();
        responseOptions = gameGen.getResponseOptions();
    }

    public void show(Stage primaryStage) throws Exception{
        constructUIElements(primaryStage);
        constructStage(primaryStage);
        primaryStage.show();
    }

    private void constructUIElements(Stage stage) {
        returnButton.setText("🏠 Main Menu");
        setButtonLayoutScale(returnButton, 70, 690, 1.5, 1.5);
        returnButton.setOnAction(e -> {
            GUI mainMenu = new GUI();
            try {
                mainMenu.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        listenButton.setText("LISTEN");
        setButtonLayoutScale(listenButton, 660, 250, 3, 3);
        listenButton.setOnAction(e -> {
            playSpokenWord();
        });

        option1Button.setText(responseOptions[0]);
        setButtonLayoutScale(option1Button, 330, 500, 2, 2);
        option1Button.setOnAction(e -> {
            userInput = responseOptions[0];
            processUserInput();
            option1Button.setDisable(true);
        });

        option2Button.setText(responseOptions[1]);
        setButtonLayoutScale(option2Button, 530, 500, 2, 2);
        option2Button.setOnAction(e -> {
            userInput = responseOptions[1];
            processUserInput();
            option2Button.setDisable(true);
        });

        option3Button.setText(responseOptions[2]);
        setButtonLayoutScale(option3Button, 730, 500, 2, 2);
        option3Button.setOnAction(e -> {
            userInput = responseOptions[2];
            processUserInput();
            option3Button.setDisable(true);
        });

        option4Button.setText(responseOptions[3]);
        setButtonLayoutScale(option4Button, 930, 500, 2, 2);
        option4Button.setOnAction(e -> {
            userInput = responseOptions[3];
            processUserInput();
            option4Button.setDisable(true);
        });

        replayButton.setText("PLAY AGAIN");
        setButtonLayoutScale(replayButton, 650, 390, 2.5, 2.5);
        replayButton.setOnAction(e -> {
            restartGame(stage);
        });
        replayButton.setVisible(false);

        instructionLabel.setText("Click the big button to listen to the word, " +
                "and click the word that you heard!");
        instructionLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        instructionLabel.setLayoutX(150);
        instructionLabel.setLayoutY(50);

        instructionListenButton.setText("👂");
        setButtonLayoutScale(instructionListenButton, 1100, 50, 2.0, 2.0);
        instructionListenButton.setOnAction(e -> {
            playSound(NARRATION_FINDER.getAudioPathFromString("click the button"), 1.0);
        });

        winLabel.setText("That's correct!");
        winLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        winLabel.setTextFill(Color.DARKGREEN);
        winLabel.setLayoutX(590.0);
        winLabel.setLayoutY(300.0);
        winLabel.setVisible(false);

        incorrectLabel.setText("That's not quite right... try again!");
        incorrectLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        incorrectLabel.setTextFill(Color.DARKRED);
        incorrectLabel.setLayoutX(460.0);
        incorrectLabel.setLayoutY(400.0);
        incorrectLabel.setVisible(false);
    }

    private void setButtonLayoutScale(
            Button button, double layoutX, double layoutY, double scaleX, double scaleY) {
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setScaleX(scaleX);
        button.setScaleY(scaleY);
        button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, button.getFont().getSize()));
        button.setStyle("-fx-background-color: #fcf6c5;");
    }

    private void constructStage(Stage stage) {
        stage.setTitle("Dreamleaf Learning - Listen And Choose");
        Pane layout = new Pane();
        GUI.setPaneBackground(layout, "/backgrounds/dreamleaf-background.png");
        layout.getChildren().add(returnButton);
        layout.getChildren().add(listenButton);
        layout.getChildren().add(instructionLabel);
        layout.getChildren().add(option1Button);
        layout.getChildren().add(option2Button);
        layout.getChildren().add(option3Button);
        layout.getChildren().add(option4Button);
        layout.getChildren().add(replayButton);
        layout.getChildren().add(winLabel);
        layout.getChildren().add(incorrectLabel);
        layout.getChildren().add(instructionListenButton);
        Scene scene = new Scene(layout, 1400, 750);
        stage.setScene(scene);
    }

    private void processUserInput() {
        if (userInput.equals(spokenWord)) {
            displayWin();
        }
        else {
            incorrectLabel.setVisible(true);
        }
    }

    private void displayWin() {
        incorrectLabel.setVisible(false);
        listenButton.setVisible(false);
        option1Button.setVisible(false);
        option2Button.setVisible(false);
        option3Button.setVisible(false);
        option4Button.setVisible(false);
        winLabel.setVisible(true);
        replayButton.setVisible(true);
        URL soundUrl = getClass().getResource("/audio/sound_effects/Yay.mp3");
        playSound(soundUrl.toString(), 0.5);
    }

    private void playSound(String path, double volume) {
        if (path.isEmpty()) {
            return;
        }

        Media sound = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    private void playSpokenWord() {
        playSound(NARRATION_FINDER.getAudioPathFromString(spokenWord), 1.0);
    }

    private void restartGame(Stage stage) {
        ListenAndChoose game = new ListenAndChoose();
        try {
            game.show(stage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

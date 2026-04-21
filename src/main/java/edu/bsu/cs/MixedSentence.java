package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.net.URL;

public class MixedSentence{
    final private NarrationFinder NARRATION_FINDER = new NarrationFinder();
    final private MixedSentenceGameGenerator gameGenerator = new MixedSentenceGameGenerator();
    private String playerSolution = "";
    private int wordsSelectedCount = 0;

    private Label instructionLabel = new Label();
    private Rectangle backgroundRect = new Rectangle();
    private Label playerSolutionLabel = new Label();
    private Label incorrectLabel = new Label();
    private Label correctLabel = new Label();

    private Button returnButton = new Button();
    private Button instructionListenButton = new Button();
    private Button sentenceListenButton = new Button();
    private Button resetButton = new Button();
    private Button word1Button = new Button();
    private Button word2Button = new Button();
    private Button word3Button = new Button();
    private Button word4Button = new Button();
    private Button replayButton = new Button();

    public void show(Stage primaryStage) throws Exception{
        constructUIElements(primaryStage);
        constructStage(primaryStage);
        primaryStage.show();
    }

    private void constructUIElements(Stage stage) {
        returnButton.setText("🏠 Main Menu");
        setButtonLayoutScale(returnButton, 70, 690, 1.5, 1.5);
        returnButton.setOnAction(e -> {
            GUI home = new GUI();
            try {
                home.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        instructionLabel.setText("Rearrange the words correctly!");
        instructionLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        instructionLabel.setLayoutX(440);
        instructionLabel.setLayoutY(50);
        
        instructionListenButton.setText("👂");
        setButtonLayoutScale(instructionListenButton, 980, 60, 2.0, 2.0);
        instructionListenButton.setOnAction(e -> {
            playSound(NARRATION_FINDER.getAudioPathFromString(instructionLabel.getText()), 1.0);
        });

        backgroundRect.setFill(Color.rgb(220, 255, 220));
        backgroundRect.setLayoutX(300);
        backgroundRect.setLayoutY(300);
        backgroundRect.setWidth(800);
        backgroundRect.setHeight(160);

        playerSolutionLabel.setText("");
        playerSolutionLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 38));
        playerSolutionLabel.setLayoutX(320);
        playerSolutionLabel.setLayoutY(350);

        sentenceListenButton.setText("👂");
        setButtonLayoutScale(sentenceListenButton, 680, 190, 4.0, 4.0);
        sentenceListenButton.setOnAction(e -> {
            String nameAsString = gameGenerator.getOriginalSentenceAsString();
            playSound(NARRATION_FINDER.getAudioPathFromString(nameAsString), 1.0);
        });

        word1Button.setText(gameGenerator.getShuffledSentence()[0]);
        setButtonLayoutScale(word1Button, 320, 550, 2, 2);
        word1Button.setOnAction(e -> {
            applyWordSelection(0);
            word1Button.setDisable(true);
        });

        word2Button.setText(gameGenerator.getShuffledSentence()[1]);
        setButtonLayoutScale(word2Button, 500, 550, 2, 2);
        word2Button.setOnAction(e -> {
            applyWordSelection(1);
            word2Button.setDisable(true);
        });

        word3Button.setText(gameGenerator.getShuffledSentence()[2]);
        setButtonLayoutScale(word3Button, 680, 550, 2, 2);
        word3Button.setOnAction(e -> {
            applyWordSelection(2);
            word3Button.setDisable(true);
        });

        word4Button.setText(gameGenerator.getShuffledSentence()[3]);
        setButtonLayoutScale(word4Button, 870, 550, 2, 2);
        word4Button.setOnAction(e -> {
            applyWordSelection(3);
            word4Button.setDisable(true);
        });

        resetButton.setText("↻");
        setButtonLayoutScale(resetButton, 1020, 550, 2, 2);
        resetButton.setOnAction(e -> {
            resetAnswer();
        });

        correctLabel.setText("That's correct!");
        correctLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        correctLabel.setTextFill(Color.DARKGREEN);
        correctLabel.setLayoutX(590.0);
        correctLabel.setLayoutY(530.0);
        correctLabel.setVisible(false);

        incorrectLabel.setText("That's not quite right... try again!");
        incorrectLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        incorrectLabel.setTextFill(Color.DARKRED);
        incorrectLabel.setLayoutX(460.0);
        incorrectLabel.setLayoutY(600.0);
        incorrectLabel.setVisible(false);

        replayButton.setText("PLAY AGAIN");
        setButtonLayoutScale(replayButton, 650, 650, 2.5, 2.5);
        replayButton.setOnAction(e -> {
            restartGame(stage);
        });
        replayButton.setOnMouseEntered(e -> {
            playSound(NARRATION_FINDER.getAudioPathFromString("play again"), 1.0);
        });
        replayButton.setVisible(false);
    }

    private void setButtonLayoutScale(
            Button button, double layoutX, double layoutY, double scaleX, double scaleY) {
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setScaleX(scaleX);
        button.setScaleY(scaleY);
        button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, button.getFont().getSize()));
        button.setStyle("-fx-background-color: #ffcccc;");
    }

    private void constructStage(Stage stage) {
        stage.setTitle("Dreamleaf Learning - Mixed Sentences");
        Pane layout = new Pane();
        GUI.setPaneBackground(layout, "/backgrounds/dreamleaf-background.png");

        layout.getChildren().add(returnButton);
        layout.getChildren().add(instructionLabel);
        layout.getChildren().add(instructionListenButton);
        layout.getChildren().add(backgroundRect);
        layout.getChildren().add(playerSolutionLabel);
        layout.getChildren().add(sentenceListenButton);
        layout.getChildren().add(word1Button);
        layout.getChildren().add(word2Button);
        layout.getChildren().add(word3Button);
        layout.getChildren().add(word4Button);
        layout.getChildren().add(resetButton);
        layout.getChildren().add(correctLabel);
        layout.getChildren().add(incorrectLabel);
        layout.getChildren().add(replayButton);

        Scene scene = new Scene(layout, 1400, 750);
        stage.setScene(scene);
        stage.show();
    }

    private void applyWordSelection(int index) {
        playerSolution += gameGenerator.getShuffledSentence()[index] + " ";
        playerSolutionLabel.setText(playerSolution);
        wordsSelectedCount += 1;

        if (wordsSelectedCount == 4) {
            checkAnswer();
        }
    }

    private void checkAnswer() {
        if (playerSolution.trim().equals(gameGenerator.getOriginalSentenceAsString())) {
            correctLabel.setVisible(true);
            replayButton.setVisible(true);
            word1Button.setVisible(false);
            word2Button.setVisible(false);
            word3Button.setVisible(false);
            word4Button.setVisible(false);
            resetButton.setVisible(false);
            URL soundUrl = getClass().getResource("/audio/sound_effects/Yay.mp3");
            playSound(soundUrl.toString(), 0.5);
        }
        else {
            incorrectLabel.setVisible(true);
        }
    }

    private void resetAnswer() {
        playerSolution = "";
        wordsSelectedCount = 0;
        playerSolutionLabel.setText(playerSolution);
        word1Button.setDisable(false);
        word2Button.setDisable(false);
        word3Button.setDisable(false);
        word4Button.setDisable(false);
        incorrectLabel.setVisible(false);
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

    private void restartGame(Stage stage) {
        MixedSentence game = new MixedSentence();
        try {
            game.show(stage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;

public class RhymingWords {

    private final String targetWord;
    private final String[] responseOptions;
    private final int correctIndex;

    Button returnButton = new Button();
    Button option1Button = new Button();
    Button option2Button = new Button();
    Button option3Button = new Button();
    Button replayButton = new Button();
    Label instructionLabel = new Label();
    Label targetWordLabel = new Label();
    Label winLabel = new Label();
    Label incorrectLabel = new Label();
    Label scoreLabel = new Label();

    AudioClip correctSound;
    AudioClip wrongSound;

    int score = 0;
    int roundsPlayed = 0;

    public RhymingWords() {
        RhymingWordsGameGenerator gameGen = new RhymingWordsGameGenerator();
        targetWord = gameGen.getTargetWord();
        responseOptions = gameGen.getResponseOptions();
        correctIndex = gameGen.getCorrectIndex();
    }

    // Constructor to carry score across rounds
    private RhymingWords(int score, int roundsPlayed) {
        RhymingWordsGameGenerator gameGen = new RhymingWordsGameGenerator();
        targetWord = gameGen.getTargetWord();
        responseOptions = gameGen.getResponseOptions();
        correctIndex = gameGen.getCorrectIndex();
        this.score = score;
        this.roundsPlayed = roundsPlayed;
    }

    public void show(Stage primaryStage) throws Exception {
        URL correctURL = getClass().getResource("/audio/Yay.mp3");
        URL wrongURL = getClass().getResource("/audio/wrong.mp3");

        if (correctURL != null) correctSound = new AudioClip(correctURL.toExternalForm());
        if (wrongURL != null) wrongSound = new AudioClip(wrongURL.toExternalForm());

        constructUIElements(primaryStage);
        constructStage(primaryStage);
        primaryStage.show();
    }

    private void constructUIElements(Stage stage) {

        // Return button
        returnButton.setText("🏠 Main Menu");
        returnButton.setLayoutX(60.0);
        returnButton.setLayoutY(685.0);
        returnButton.setScaleX(1.5);
        returnButton.setScaleY(1.5);
        returnButton.setStyle(
                "-fx-background-color: #ff9eb5; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px;"
        );
        returnButton.setOnAction(e -> {
            GUI mainMenu = new GUI();
            try {
                mainMenu.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Instruction
        instructionLabel.setText("🎵 Find the word that RHYMES! 🎵");
        instructionLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28.0));
        instructionLabel.setTextFill(Color.web("#7b2ff7"));
        instructionLabel.setLayoutX(380);
        instructionLabel.setLayoutY(50);

        // Target word display
        targetWordLabel.setText("✨ " + targetWord + " ✨");
        targetWordLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 64.0));
        targetWordLabel.setTextFill(Color.web("#ff6b35"));
        targetWordLabel.setLayoutX(530.0);
        targetWordLabel.setLayoutY(180.0);
        targetWordLabel.setStyle(
                "-fx-background-color: #fff9c4; " +
                        "-fx-padding: 10px 30px; " +
                        "-fx-background-radius: 20px;"
        );

        // Answer buttons
        String[] buttonColors = {"#a8e6cf", "#ffd3b6", "#d4a5f5"};
        String[] emojis = {"🌸", "🌈", "⭐"};
        Button[] optionButtons = {option1Button, option2Button, option3Button};
        double[] xPositions = {330.0, 580.0, 830.0};

        for (int i = 0; i < 3; i++) {
            optionButtons[i].setText(emojis[i] + " " + responseOptions[i]);
            optionButtons[i].setLayoutX(xPositions[i]);
            optionButtons[i].setLayoutY(480.0);
            optionButtons[i].setScaleX(2.0);
            optionButtons[i].setScaleY(2.0);
            String color = buttonColors[i];
            optionButtons[i].setStyle(
                    "-fx-background-color: " + color + "; " +
                            "-fx-font-size: 16px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-background-radius: 15px; " +
                            "-fx-text-fill: #333333;"
            );
            final int index = i;
            optionButtons[i].setOnAction(e -> {
                processUserInput(index);
                optionButtons[index].setDisable(true);
            });
        }

        // Play again button
        replayButton.setText("🔄 Play Again!");
        replayButton.setLayoutX(620.0);
        replayButton.setLayoutY(420.0);
        replayButton.setScaleX(2.5);
        replayButton.setScaleY(2.5);
        replayButton.setStyle(
                "-fx-background-color: #69d2e7; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px;"
        );
        replayButton.setOnAction(e -> restartGame(stage));
        replayButton.setVisible(false);

        //  Win label
        winLabel.setText("🎉 Woohoo! That's right! 🎉");
        winLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36.0));
        winLabel.setTextFill(Color.web("#2e7d32"));
        winLabel.setLayoutX(430.0);
        winLabel.setLayoutY(310.0);
        winLabel.setVisible(false);

        //  Incorrect label
        incorrectLabel.setText("🙈 Oops! Try again!");
        incorrectLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36.0));
        incorrectLabel.setTextFill(Color.web("#c62828"));
        incorrectLabel.setLayoutX(510.0);
        incorrectLabel.setLayoutY(390.0);
        incorrectLabel.setVisible(false);

        // Score label
        scoreLabel.setText("⭐ Score: " + score);
        scoreLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22.0));
        scoreLabel.setTextFill(Color.web("#ff6b35"));
        scoreLabel.setLayoutX(1200.0);
        scoreLabel.setLayoutY(30.0);
    }

    private void constructStage(Stage stage) {
        stage.setTitle("Dreamleaf Learning - Rhyming Words 🎵");
        Pane layout = new Pane();
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffe4f0, #d4f0ff, #fffde0);");
        layout.getChildren().addAll(
                returnButton,
                instructionLabel,
                targetWordLabel,
                option1Button,
                option2Button,
                option3Button,
                replayButton,
                winLabel,
                incorrectLabel,
                scoreLabel
        );
        Scene scene = new Scene(layout, 1400, 750);
        stage.setScene(scene);
    }

    private void processUserInput(int selectedIndex) {
        if (selectedIndex == correctIndex) {
            score++;
            roundsPlayed++;
            scoreLabel.setText("⭐ Score: " + score);
            displayWin();
        } else {
            incorrectLabel.setVisible(true);
            if (wrongSound != null) wrongSound.play();
        }
    }

    private void displayWin() {
        incorrectLabel.setVisible(false);
        targetWordLabel.setVisible(false);
        option1Button.setVisible(false);
        option2Button.setVisible(false);
        option3Button.setVisible(false);
        winLabel.setVisible(true);
        replayButton.setVisible(true);
        if (correctSound != null) correctSound.play();
    }

    private void restartGame(Stage stage) {
        RhymingWords game = new RhymingWords(score, roundsPlayed);
        try {
            game.show(stage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
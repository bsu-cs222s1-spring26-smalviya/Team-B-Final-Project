package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

public class ListenAndChoose {
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
    Label instructionLabel = new Label();
    Label placeholderSpeachLabel = new Label();
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
        returnButton.setText("Return to Main Menu");
        returnButton.setLayoutX(70.0);
        returnButton.setLayoutY(690.0);
        returnButton.setScaleX(1.5);
        returnButton.setScaleY(1.5);

        returnButton.setOnAction(e -> {
            GUI mainMenu = new GUI();
            try {
                mainMenu.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        listenButton.setText("LISTEN");
        listenButton.setLayoutX(660.0);
        listenButton.setLayoutY(250.0);
        listenButton.setScaleX(3.0);
        listenButton.setScaleY(3.0);
        listenButton.setOnAction(e -> {
            playSpokenWord();
        });

        option1Button.setText(responseOptions[0]);
        option1Button.setLayoutX(330.0);
        option1Button.setLayoutY(500.0);
        option1Button.setScaleX(2.0);
        option1Button.setScaleY(2.0);
        option1Button.setOnAction(e -> {
            userInput = responseOptions[0];
            processUserInput();
            option1Button.setDisable(true);
        });

        option2Button.setText(responseOptions[1]);
        option2Button.setLayoutX(530.0);
        option2Button.setLayoutY(500.0);
        option2Button.setScaleX(2.0);
        option2Button.setScaleY(2.0);
        option2Button.setOnAction(e -> {
            userInput = responseOptions[1];
            processUserInput();
            option2Button.setDisable(true);
        });

        option3Button.setText(responseOptions[2]);
        option3Button.setLayoutX(730.0);
        option3Button.setLayoutY(500.0);
        option3Button.setScaleX(2.0);
        option3Button.setScaleY(2.0);
        option3Button.setOnAction(e -> {
            userInput = responseOptions[2];
            processUserInput();
            option3Button.setDisable(true);
        });

        option4Button.setText(responseOptions[3]);
        option4Button.setLayoutX(930.0);
        option4Button.setLayoutY(500.0);
        option4Button.setScaleX(2.0);
        option4Button.setScaleY(2.0);
        option4Button.setOnAction(e -> {
            userInput = responseOptions[3];
            processUserInput();
            option4Button.setDisable(true);
        });

        replayButton.setText("PLAY AGAIN");
        replayButton.setLayoutX(650.0);
        replayButton.setLayoutY(390.0);
        replayButton.setScaleX(2.5);
        replayButton.setScaleY(2.5);
        replayButton.setOnAction(e -> {
            restartGame(stage);
        });
        replayButton.setVisible(false);

        instructionLabel.setText("Click the big button to listen to the word, " +
                "and click the word that you heard!");
        instructionLabel.setFont(new Font(24.0));
        instructionLabel.setLayoutX(300);
        instructionLabel.setLayoutY(50);

        placeholderSpeachLabel.setText(String.format("\"%s\"", spokenWord));
        placeholderSpeachLabel.setFont(new Font(16.0));
        placeholderSpeachLabel.setLayoutX(665.0);
        placeholderSpeachLabel.setLayoutY(320.0);
        placeholderSpeachLabel.setVisible(false);

        winLabel.setText("That's correct!");
        winLabel.setFont(new Font(32.0));
        winLabel.setTextFill(Color.DARKGREEN);
        winLabel.setLayoutX(590.0);
        winLabel.setLayoutY(300.0);
        winLabel.setVisible(false);

        incorrectLabel.setText("That's not quite right... try again!");
        incorrectLabel.setFont(new Font(32.0));
        incorrectLabel.setTextFill(Color.DARKRED);
        incorrectLabel.setLayoutX(460.0);
        incorrectLabel.setLayoutY(400.0);
        incorrectLabel.setVisible(false);
    }

    private void constructStage(Stage stage) {
        stage.setTitle("Listen And Choose");
        Pane layout = new Pane();
        layout.getChildren().add(returnButton);
        layout.getChildren().add(listenButton);
        layout.getChildren().add(instructionLabel);
        layout.getChildren().add(placeholderSpeachLabel);
        layout.getChildren().add(option1Button);
        layout.getChildren().add(option2Button);
        layout.getChildren().add(option3Button);
        layout.getChildren().add(option4Button);
        layout.getChildren().add(replayButton);
        layout.getChildren().add(winLabel);
        layout.getChildren().add(incorrectLabel);
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
        placeholderSpeachLabel.setVisible(false);
        option1Button.setVisible(false);
        option2Button.setVisible(false);
        option3Button.setVisible(false);
        option4Button.setVisible(false);
        winLabel.setVisible(true);
        replayButton.setVisible(true);
    }

    private void playSpokenWord() {
        // Temporary. Will play the appropriate word as audio in a future iteration.
        placeholderSpeachLabel.setVisible(true);
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

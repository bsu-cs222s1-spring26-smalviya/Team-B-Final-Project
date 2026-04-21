package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static edu.bsu.cs.GUI.setPaneBackground;

public class FillInTheSentence {

    private int round = 0;

    private String[] sentences = {
            "The cat _____ over the moon.",
            "She _____ to the store.",
            "I _____ to school every day.",
            "She _____ a book before bed.",
            "We _____ pizza for dinner.",
            "The dog _____ in the yard.",
            "She _____ under the moonlight.",
            "The stars _____ softly in the sky.",
            "He _____ quickly to avoid the rain.",
            "Ball State Football team is _____."
    };

    private String[] answers = {
            "jumped",
            "ran",
            "went",
            "read",
            "ate",
            "pooped",
            "shimmered",
            "held",
            "ran",
            "bad"
    };

    private String[][] choices = {
            {"jumped", "slept", "red"},
            {"ran", "walked", "jumped"},
            {"went", "past", "dog"},
            {"read", "write", "eat"},
            {"ate", "bought", "cooked"},
            {"pooped", "played", "danced"},
            {"shimmered", "broke", "fell"},
            {"held", "screamed", "dropped"},
            {"ran", "walked", "crawled"},
            {"bad", "amazing", "alright"}
    };

    private Label sentenceLabel = new Label();
    private Label blank = new Label("_____");
    private Label result = new Label();
    private VBox wordBox = new VBox(15);

    private Button returnButton = new Button("🏠 Main Menu");

    public void show(Stage stage) {

        Pane layout = new Pane();


        setPaneBackground(layout, "/backgrounds/dreamleaf-background.png");

        sentenceLabel.setText(sentences[round]);


        sentenceLabel.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-text-fill: #3E3A33;" +
                        "-fx-font-weight: bold;"
        );


        blank.setStyle(
                "-fx-border-color: #588157;" +
                        "-fx-border-width: 3px;" +
                        "-fx-padding: 14;" +
                        "-fx-font-size: 22px;" +
                        "-fx-background-color: rgba(245, 241, 230, 0.8);" +
                        "-fx-text-fill: #3E3A33;"
        );


        blank.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        blank.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                blank.setText(db.getString());
            }
            e.setDropCompleted(true);
            e.consume();
        });


        Button check = new Button("Check Answer");
        check.setStyle(
                "-fx-background-color: #588157;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-font-weight: bold;"
        );

        check.setOnAction(e -> checkAnswer(stage));


        returnButton.setLayoutX(60);
        returnButton.setLayoutY(685);

        returnButton.setStyle(
                "-fx-background-color: #3E3A33;" +
                        "-fx-text-fill: #F5F1E6;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-padding: 10 16;"
        );

        returnButton.setOnAction(e -> {
            try {
                new GUI().start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        loadWords();


        VBox centerBox = new VBox(20, sentenceLabel, blank, wordBox, check, result);
        centerBox.setLayoutX(250);
        centerBox.setLayoutY(100);

        layout.getChildren().addAll(centerBox, returnButton);

        stage.setScene(new Scene(layout, 1400, 750));
        stage.setTitle("Fill In The Sentence");
        stage.show();
    }

    private void loadWords() {
        wordBox.getChildren().clear();

        for (String word : choices[round]) {
            Label l = new Label(word);


            l.setStyle(
                    "-fx-background-color: #588157;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 18px;" +
                            "-fx-padding: 12 18;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-font-weight: bold;"
            );

            l.setOnDragDetected(e -> {
                Dragboard db = l.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(l.getText());
                db.setContent(content);
                e.consume();
            });

            wordBox.getChildren().add(l);
        }
    }

    private void checkAnswer(Stage stage) {
        if (blank.getText().equals(answers[round])) {
            result.setText("Correct!");
            round++;

            if (round < sentences.length) {
                show(stage);
            } else {
                result.setText("Game finished!");
            }
        } else {
            result.setText("Try again!");
        }
    }
}
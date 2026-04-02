package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;

public class RhymingWords {

    int currentQuestion = 0;
    int score = 0;

    AudioClip correctSound;
    AudioClip wrongSound;

    String[] questions = {
            "Which word rhymes with CAT?",
            "Which word rhymes with DOG?",
            "Which word rhymes with BALL?",
            "Which word rhymes with SUN?",
            "Which word rhymes with BEE?",
            "Which word rhymes with CAKE?",
            "Which word rhymes with MOON?",
            "Which word rhymes with STAR?",
            "Which word rhymes with BLUE?",
            "Which word rhymes with BED?",
            "Which word rhymes with HOUSE?",
            "Which word rhymes with PLAY"
    };

    String[][] options = {
            {"Dog","Hat","Sun"},
            {"Log","Tree","Book"},
            {"Wall","Cup","Fish"},
            {"Run","Tree","Cat"},
            {"Tree","Ball","Hat"},
            {"Bake","Ball","Dog"},
            {"Spoon","Cup","Pen"},
            {"Fish","Ball","Car"},
            {"Shoe","Hat","Tree"},
            {"Red","Ball","Cup"},
            {"Mouse","Tree","Sun"},
            {"Day","Dog","Book"}
    };

    int[] correctAnswers = {1,0,0,0,0,0,0,3,0,0,0,0};

    // ✅ THIS METHOD IS CALLED FROM GUI
    public void show(Stage primaryStage) {

        // 🔊 UPDATED PATH (NOW INSIDE /audio/)
        URL correctURL = getClass().getResource("/audio/Yay.mp3");
        URL wrongURL = getClass().getResource("/audio/wrong.mp3");

        if (correctURL != null) {
            correctSound = new AudioClip(correctURL.toExternalForm());
        } else {
            System.out.println("❌ audio/Yay.mp3 not found!");
        }

        if (wrongURL != null) {
            wrongSound = new AudioClip(wrongURL.toExternalForm());
        } else {
            System.out.println("❌ audio/wrong.mp3 not found!");
        }

        currentQuestion = 0;
        score = 0;

        Pane layout = new Pane();
        layout.setStyle("-fx-background-color: lightyellow;");

        Button startButton = new Button("Start Game 🎮");
        startButton.setLayoutX(600);
        startButton.setLayoutY(300);
        startButton.setStyle("-fx-font-size: 24px; -fx-background-color: yellow;");

        layout.getChildren().add(startButton);

        startButton.setOnAction(e -> {
            layout.getChildren().clear();
            loadQuestion(layout);
        });

        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rhyming Words");
        primaryStage.show();
    }

    public void loadQuestion(Pane layout) {

        Button questionBox = new Button(getQuestion());
        questionBox.setLayoutX(450);
        questionBox.setLayoutY(150);
        questionBox.setPrefWidth(500);
        questionBox.setStyle("-fx-font-size: 20px; -fx-background-color: lightblue;");

        String[] opts = getOptions();

        Button o1 = new Button(opts[0]);
        Button o2 = new Button(opts[1]);
        Button o3 = new Button(opts[2]);

        double centerX = 700;
        double buttonWidth = 200;

        o1.setPrefWidth(buttonWidth);
        o2.setPrefWidth(buttonWidth);
        o3.setPrefWidth(buttonWidth);

        o1.setLayoutX(centerX - buttonWidth / 2);
        o2.setLayoutX(centerX - buttonWidth / 2);
        o3.setLayoutX(centerX - buttonWidth / 2);

        o1.setLayoutY(300);
        o2.setLayoutY(380);
        o3.setLayoutY(460);

        layout.getChildren().addAll(questionBox, o1, o2, o3);

        o1.setOnAction(e -> handleAnswer(0, questionBox, layout, o1, o2, o3));
        o2.setOnAction(e -> handleAnswer(1, questionBox, layout, o1, o2, o3));
        o3.setOnAction(e -> handleAnswer(2, questionBox, layout, o1, o2, o3));
    }

    public void handleAnswer(int selected, Button questionBox,
                             Pane layout, Button o1, Button o2, Button o3) {

        boolean correct = checkAnswer(selected);

        if (correct) {
            score++;
            questionBox.setText("🎉 Correct!");
            if (correctSound != null) correctSound.play();
        } else {
            questionBox.setText("❌ Wrong!");
            if (wrongSound != null) wrongSound.play();
        }

        o1.setDisable(true);
        o2.setDisable(true);
        o3.setDisable(true);

        Button next = new Button("Next ➡");
        next.setLayoutX(650);
        next.setLayoutY(520);

        layout.getChildren().add(next);

        next.setOnAction(e -> {
            layout.getChildren().clear();

            if (!isFinished()) {
                loadQuestion(layout);
            } else {
                Button end = new Button("🎉 Game Finished!\nScore: " + score);
                end.setLayoutX(500);
                end.setLayoutY(300);
                end.setStyle("-fx-font-size: 22px;");

                Button reset = new Button("Play Again 🔄");
                reset.setLayoutX(600);
                reset.setLayoutY(400);

                layout.getChildren().addAll(end, reset);

                reset.setOnAction(ev -> {
                    currentQuestion = 0;
                    score = 0;
                    layout.getChildren().clear();
                    loadQuestion(layout);
                });
            }
        });
    }

    public String getQuestion() {
        return questions[currentQuestion];
    }

    public String[] getOptions() {
        return options[currentQuestion];
    }

    public boolean checkAnswer(int selectedOption) {
        boolean correct = selectedOption == correctAnswers[currentQuestion];
        currentQuestion++;
        return correct;
    }

    public boolean isFinished() {
        return currentQuestion >= questions.length;
    }
}
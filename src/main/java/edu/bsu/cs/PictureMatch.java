package edu.bsu.cs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.net.URL;
import java.util.*;

public class PictureMatch {

    private static final String[] IMAGE_PATHS = {
            "/picture_match/Cat.png",
            "/picture_match/Dog.png",
            "/picture_match/House.png",
            "/picture_match/Tree.png",
            "/picture_match/Car.png"
    };

    private static final String[] CORRECT_WORDS = {
            "Cat", "Dog", "House", "Tree", "Car"
    };

    private static final String[][] WRONG_WORDS = {
            {"Dog", "Bird"},
            {"Cat", "Mouse"},
            {"Castle", "Cave"},
            {"Flower", "Bush"},
            {"Truck", "Bike"}
    };

    private static final String CORRECT_SOUND_PATH = "/audio/sound_effects/Yay.mp3";
    private static final String BACKGROUND_PATH = "/backgrounds/dreamleaf-background.png";
    private static final String NARRATION_PATH = "/audio/picture_match_instructions.mp3";

    private int currentSet = 0;
    private ImageView imageView;
    private HBox wordButtonsBox;
    private Label messageLabel;
    private Label progressLabel;
    private StackPane mainLayout;
    private MediaPlayer mediaPlayer;
    private Stage primaryStage;

    public void show(Stage stage) throws Exception {
        this.primaryStage = stage;
        primaryStage.setTitle("Dreamleaf Learning - Picture Match");

        mainLayout = new StackPane();

        Image bgImage = loadImage(BACKGROUND_PATH);
        if (bgImage != null) {
            ImageView bgView = new ImageView(bgImage);
            bgView.setFitWidth(1400);
            bgView.setFitHeight(750);
            bgView.setPreserveRatio(false);
            mainLayout.getChildren().add(bgView);
        }

        VBox gameContent = new VBox(25);
        gameContent.setAlignment(Pos.CENTER);
        gameContent.setPadding(new javafx.geometry.Insets(20));

        Label titleLabel = new Label("Picture Match!");
        titleLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 52));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 4, 0, 0, 3);");

        Button replayBtn = createReplayButton();

        progressLabel = new Label("Picture 1 of 5");
        progressLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28));
        progressLabel.setTextFill(Color.WHITE);
        progressLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 2);");

        imageView = new ImageView();
        imageView.setFitWidth(350);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);

        StackPane imageContainer = new StackPane(imageView);
        imageContainer.setStyle(
                "-fx-background-color: rgba(255,255,255,0.85);" +
                        "-fx-background-radius: 25;" +
                        "-fx-padding: 15;" +
                        "-fx-border-color: #FF69B4;" +
                        "-fx-border-width: 4;" +
                        "-fx-border-radius: 25;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 12, 0, 0, 6);"
        );

        messageLabel = new Label("");
        messageLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36));
        messageLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 2);");

        wordButtonsBox = new HBox(35);
        wordButtonsBox.setAlignment(Pos.CENTER);

        Button homeButton = createHomeButton();
        homeButton.setOnAction(e -> returnToMainMenu());

        gameContent.getChildren().addAll(
                titleLabel,
                replayBtn,
                progressLabel,
                imageContainer,
                wordButtonsBox,
                messageLabel,
                homeButton
        );

        mainLayout.getChildren().add(gameContent);

        Scene scene = new Scene(mainLayout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();

        playSound(NARRATION_PATH);
        loadSet(currentSet);
    }

    private Button createReplayButton() {
        Button btn = new Button("🔊 Hear Instructions");
        btn.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        btn.setTextFill(Color.WHITE);
        btn.setStyle(
                "-fx-background-color: #4CAF50;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 12 25;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 0, 3);"
        );

        btn.setOnMouseEntered(e -> btn.setScaleX(1.08));
        btn.setOnMouseExited(e -> btn.setScaleX(1.0));
        btn.setOnAction(e -> playSound(NARRATION_PATH));

        return btn;
    }

    private Button createHomeButton() {
        Button button = new Button("🏠 Main Menu");
        button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
        button.setTextFill(Color.WHITE);
        button.setStyle(
                "-fx-background-color: #ff9eb5; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px;"
        );
        return button;
    }

    private Image loadImage(String path) {
        try {
            URL url = getClass().getResource(path);
            if (url != null) {
                return new Image(url.toString());
            }
        } catch (Exception e) {
            System.out.println("Could not load image: " + path);
        }
        return null;
    }

    private void playSound(String path) {
        try {
            stopSound();
            URL soundUrl = getClass().getResource(path);
            Media sound;
            if (soundUrl != null) {
                sound = new Media(soundUrl.toString());
            } else {
                sound = new Media("file:" + path);
            }
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.7);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Sound not found: " + path);
        }
    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void loadSet(int setIndex) {
        if (setIndex >= IMAGE_PATHS.length) {
            showGameComplete();
            return;
        }

        progressLabel.setText("Picture " + (setIndex + 1) + " of " + IMAGE_PATHS.length);
        messageLabel.setText("");

        Image image = loadImage(IMAGE_PATHS[setIndex]);
        if (image != null && !image.isError()) {
            imageView.setImage(image);
        } else {
            createPlaceholderImage(setIndex);
        }

        createWordButtons(setIndex);
    }

    private void createPlaceholderImage(int setIndex) {
        VBox placeholder = new VBox(10);
        placeholder.setAlignment(Pos.CENTER);
        placeholder.setStyle(
                "-fx-background-color: rgba(255,228,181,0.9);" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 20;"
        );

        Label emojiLabel = new Label(getEmojiForIndex(setIndex));
        emojiLabel.setFont(Font.font("Arial", FontWeight.BOLD, 100));

        Label textLabel = new Label(CORRECT_WORDS[setIndex]);
        textLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 32));
        textLabel.setTextFill(Color.DARKORANGE);

        placeholder.getChildren().addAll(emojiLabel, textLabel);

        javafx.scene.image.WritableImage snapshot = placeholder.snapshot(null, null);
        imageView.setImage(snapshot);
    }

    private String getEmojiForIndex(int index) {
        String[] emojis = {"🐱", "🐶", "🏠", "🌳", "🚗"};
        return emojis[index % emojis.length];
    }

    private void createWordButtons(int setIndex) {
        wordButtonsBox.getChildren().clear();

        String correctWord = CORRECT_WORDS[setIndex];
        String[] wrongWords = WRONG_WORDS[setIndex];

        List<String> allWords = new ArrayList<>();
        allWords.add(correctWord);
        allWords.addAll(Arrays.asList(wrongWords));
        Collections.shuffle(allWords);

        for (String word : allWords) {
            Button wordButton = createWordButton(word);
            wordButton.setOnAction(e -> handleWordChoice(word, correctWord));
            wordButtonsBox.getChildren().add(wordButton);
        }
    }

    private Button createWordButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 26));
        button.setTextFill(Color.WHITE);
        button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FF6B9D, #C44569);" +
                        "-fx-background-radius: 45;" +
                        "-fx-padding: 18 38;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 4);"
        );

        button.setOnMouseEntered(e -> {
            button.setScaleX(1.12);
            button.setScaleY(1.12);
        });
        button.setOnMouseExited(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
        button.setOnMousePressed(e -> {
            button.setScaleX(0.95);
            button.setScaleY(0.95);
        });

        return button;
    }

    private void handleWordChoice(String chosenWord, String correctWord) {
        if (chosenWord.equals(correctWord)) {
            handleCorrectAnswer();
        } else {
            handleWrongAnswer();
        }
    }

    private void handleCorrectAnswer() {
        playSound(CORRECT_SOUND_PATH);

        messageLabel.setText("Correct! Amazing!");
        messageLabel.setTextFill(Color.GREEN);

        wordButtonsBox.setDisable(true);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), messageLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(e -> {
            currentSet++;
            wordButtonsBox.setDisable(false);
            loadSet(currentSet);
        });
        pause.play();
    }

    private void handleWrongAnswer() {
        messageLabel.setText("Try Again!");
        messageLabel.setTextFill(Color.RED);

        javafx.animation.TranslateTransition shake =
                new javafx.animation.TranslateTransition(Duration.millis(100), messageLabel);
        shake.setByX(-10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }

    private void showGameComplete() {
        stopSound();
        mainLayout.getChildren().clear();

        Image bgImage = loadImage(BACKGROUND_PATH);
        if (bgImage != null) {
            ImageView bgView = new ImageView(bgImage);
            bgView.setFitWidth(1400);
            bgView.setFitHeight(750);
            mainLayout.getChildren().add(bgView);
        }

        VBox victoryBox = new VBox(30);
        victoryBox.setAlignment(Pos.CENTER);

        Label trophyLabel = new Label("🏆 🎊 🏆");
        trophyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 80));

        Label completeLabel = new Label("Game Complete!");
        completeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 56));
        completeLabel.setTextFill(Color.WHITE);
        completeLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 5, 0, 0, 3);");

        Label greatJobLabel = new Label("You're a Picture Matching Star!");
        greatJobLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36));
        greatJobLabel.setTextFill(Color.WHITE);
        greatJobLabel.setStyle("-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 2);");

        Button playAgainButton = createWordButton("Play Again");
        playAgainButton.setOnAction(e -> {
            currentSet = 0;
            mainLayout.getChildren().clear();
            try {
                show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button homeButton = createHomeButton();
        homeButton.setOnAction(e -> returnToMainMenu());

        victoryBox.getChildren().addAll(trophyLabel, completeLabel, greatJobLabel, playAgainButton, homeButton);
        mainLayout.getChildren().add(victoryBox);
    }

    private void returnToMainMenu() {
        stopSound();
        GUI gui = new GUI();
        try {
            gui.start(primaryStage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

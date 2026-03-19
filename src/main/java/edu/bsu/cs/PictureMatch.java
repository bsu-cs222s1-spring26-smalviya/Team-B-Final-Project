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
            "/Cat.png",
            "/Dog.png",
            "/House.png",
            "/Tree.png",
            "/Car.png"
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

    private static final String CORRECT_SOUND_PATH = "/Yay.mp3";
    private static final String CLOUD_BUTTON_PATH = "/pink-cloud-button.png";

    private int currentSet = 0;
    private ImageView imageView;
    private HBox wordButtonsBox;
    private Label messageLabel;
    private Label progressLabel;
    private VBox mainLayout;
    private MediaPlayer mediaPlayer;
    private Stage primaryStage;

    public void show(Stage stage) throws Exception {
        this.primaryStage = stage;
        primaryStage.setTitle("Picture Match - Kids Game");


        mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FFB6C1, #87CEEB, #98FB98);" +
                        "-fx-background-radius: 0;"
        );
        mainLayout.setPadding(new javafx.geometry.Insets(20));


        Label titleLabel = new Label("🎨 Picture Match! 🎨");
        titleLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 52));
        titleLabel.setTextFill(Color.DARKMAGENTA);
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.8), 10, 0, 0, 2);");


        progressLabel = new Label("Picture 1 of 5");
        progressLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28));
        progressLabel.setTextFill(Color.DARKBLUE);


        imageView = new ImageView();
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);


        StackPane imageContainer = new StackPane(imageView);
        imageContainer.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 20;" +
                        "-fx-border-color: #FF69B4;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-radius: 30;" +
                        "-fx-effect: dropshadow(gaussian, rgba(255,105,180,0.4), 15, 0, 0, 5);"
        );
        imageContainer.setPrefSize(440, 440);

        messageLabel = new Label("");
        messageLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));


        wordButtonsBox = new HBox(40);
        wordButtonsBox.setAlignment(Pos.CENTER);

        // Home button with cloud image (like GUI)
        Button homeButton = createCloudButton("Home");
        homeButton.setOnAction(e -> returnToMainMenu());

        // Add all to main layout
        mainLayout.getChildren().addAll(
                titleLabel,
                progressLabel,
                imageContainer,
                wordButtonsBox,
                messageLabel,
                homeButton
        );

        Scene scene = new Scene(mainLayout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();


        loadSet(currentSet);
    }

    private void loadSet(int setIndex) {
        if (setIndex >= IMAGE_PATHS.length) {
            showGameComplete();
            return;
        }


        progressLabel.setText("Picture " + (setIndex + 1) + " of " + IMAGE_PATHS.length);
        messageLabel.setText("");


        try {
            URL imageUrl = getClass().getResource(IMAGE_PATHS[setIndex]);
            Image image;

            if (imageUrl != null) {
                image = new Image(imageUrl.toString());
            } else {
                image = new Image("file:" + IMAGE_PATHS[setIndex]);
            }

            if (image.isError() || image.getWidth() == 0) {
                createPlaceholderImage(setIndex);
            } else {
                imageView.setImage(image);
            }
        } catch (Exception e) {
            createPlaceholderImage(setIndex);
        }


        createWordButtons(setIndex);
    }

    private void createPlaceholderImage(int setIndex) {
        VBox placeholder = new VBox(10);
        placeholder.setAlignment(Pos.CENTER);
        placeholder.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FFE4B5, #FFA07A);" +
                        "-fx-background-radius: 20;"
        );
        placeholder.setPrefSize(400, 400);

        Label emojiLabel = new Label(getEmojiForIndex(setIndex));
        emojiLabel.setFont(Font.font("Arial", FontWeight.BOLD, 120));

        Label textLabel = new Label("Image " + (setIndex + 1));
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
            Button wordButton = createCloudWordButton(word);
            wordButton.setOnAction(e -> handleWordChoice(word, correctWord));
            wordButtonsBox.getChildren().add(wordButton);
        }
    }

    private Button createCloudButton(String text) {
        Button button = new Button();

        try {
            Image cloudImage = new Image(getClass().getResource(CLOUD_BUTTON_PATH).toExternalForm());
            ImageView imageView = new ImageView(cloudImage);
            imageView.setFitWidth(150);
            imageView.setFitHeight(75);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);
        } catch (Exception e) {
            // Fallback to styled button if image not found
            button.setText(text);
            button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
            button.setStyle(
                    "-fx-background-color: #FF69B4;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 25;" +
                            "-fx-padding: 15 30;"
            );
        }

        button.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        return button;
    }

    private Button createCloudWordButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28));
        button.setTextFill(Color.WHITE);

        // Fun cloud-like styling
        button.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FF6B9D, #C44569);" +
                        "-fx-background-radius: 50;" +
                        "-fx-padding: 20 40;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(196,69,105,0.4), 10, 0, 0, 5);"
        );


        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #FF8FAB, #E84A5F);" +
                            "-fx-background-radius: 50;" +
                            "-fx-padding: 20 40;" +
                            "-fx-cursor: hand;" +
                            "-fx-scale-x: 1.15;" +
                            "-fx-scale-y: 1.15;" +
                            "-fx-effect: dropshadow(gaussian, rgba(232,74,95,0.6), 15, 0, 0, 8);"
            );
        });

        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #FF6B9D, #C44569);" +
                            "-fx-background-radius: 50;" +
                            "-fx-padding: 20 40;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(196,69,105,0.4), 10, 0, 0, 5);"
            );
        });


        button.setOnMousePressed(e -> {
            button.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #C44569, #FF6B9D);" +
                            "-fx-background-radius: 50;" +
                            "-fx-padding: 20 40;" +
                            "-fx-cursor: hand;" +
                            "-fx-scale-x: 0.95;" +
                            "-fx-scale-y: 0.95;"
            );
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
        playCorrectSound();


        messageLabel.setText("🎉 Correct! Amazing! 🌟");
        messageLabel.setTextFill(Color.FORESTGREEN);
        messageLabel.setStyle("-fx-effect: dropshadow(gaussian, gold, 10, 0, 0, 2);");


        wordButtonsBox.setDisable(true);


        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), messageLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();


        javafx.animation.ScaleTransition scale = new javafx.animation.ScaleTransition(Duration.millis(500), messageLabel);
        scale.setByX(0.2);
        scale.setByY(0.2);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();


        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(e -> {
            currentSet++;
            wordButtonsBox.setDisable(false);
            loadSet(currentSet);
        });
        pause.play();
    }

    private void handleWrongAnswer() {
        messageLabel.setText("❌ Oops! Try Again! 💪");
        messageLabel.setTextFill(Color.CRIMSON);


        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(Duration.millis(100), messageLabel);
        shake.setByX(-10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }

    private void playCorrectSound() {
        try {
            URL soundUrl = getClass().getResource(CORRECT_SOUND_PATH);
            Media sound;

            if (soundUrl != null) {
                sound = new Media(soundUrl.toString());
            } else {
                sound = new Media("file:" + CORRECT_SOUND_PATH);
            }

            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.7);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Sound not found, continuing without audio");
        }
    }

    private void showGameComplete() {
        mainLayout.getChildren().clear();


        VBox victoryBox = new VBox(30);
        victoryBox.setAlignment(Pos.CENTER);
        victoryBox.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FFD700, #FFA500, #FF69B4);" +
                        "-fx-padding: 40;"
        );

        // Trophy emoji and celebration
        Label trophyLabel = new Label("🏆 🎊 🏆");
        trophyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 80));

        Label completeLabel = new Label("Game Complete!");
        completeLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 56));
        completeLabel.setTextFill(Color.WHITE);
        completeLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        Label greatJobLabel = new Label("You're a Picture Matching Star! ⭐");
        greatJobLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36));
        greatJobLabel.setTextFill(Color.DARKBLUE);


        Label starsLabel = new Label("⭐ ⭐ ⭐ ⭐ ⭐");
        starsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 60));


        Button playAgainButton = createCloudWordButton("Play Again! 🎮");
        playAgainButton.setOnAction(e -> {
            currentSet = 0;
            mainLayout.getChildren().clear();
            loadSet(currentSet);
        });


        Button homeButton = createCloudButton("Home 🏠");
        homeButton.setOnAction(e -> returnToMainMenu());

        victoryBox.getChildren().addAll(
                trophyLabel,
                completeLabel,
                greatJobLabel,
                starsLabel,
                playAgainButton,
                homeButton
        );

        mainLayout.getChildren().add(victoryBox);
    }

    private void returnToMainMenu() {
        GUI gui = new GUI();
        try {
            gui.start(primaryStage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Main Menu");

        Image image10 = new Image("/icons/dreamleaf-logo.png");
        ImageView imageView10 = new ImageView(image10);
        imageView10.setFitWidth(600);
        imageView10.setFitHeight(150);
        imageView10.setX(50);
        imageView10.setY(50);

        Image image = new Image("/icons/pink-cloud-button.png");
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(150);
        imageView1.setFitHeight(75);
        Button FillInTheSentenceButton = new Button();
        FillInTheSentenceButton.setGraphic(imageView1);
        FillInTheSentenceButton.setLayoutX(200);
        FillInTheSentenceButton.setLayoutY(200);

        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(75);
        imageView2.setFitWidth(150);
        Button RhymingWordsButton = new Button();
        RhymingWordsButton.setGraphic(imageView2);
        RhymingWordsButton.setLayoutX(600);
        RhymingWordsButton.setLayoutY(100);

        ImageView imageView3 = new ImageView(image);
        imageView3.setFitHeight(75);
        imageView3.setFitWidth(150);
        Button MixedSentenceButton = new Button();
        MixedSentenceButton.setGraphic(imageView3);
        MixedSentenceButton.setLayoutX(200);
        MixedSentenceButton.setLayoutY(450);

        ImageView imageView4 = new ImageView(image);
        imageView4.setFitHeight(75);
        imageView4.setFitWidth(150);
        Button PictureMatchButton = new Button();
        PictureMatchButton.setGraphic(imageView4);
        PictureMatchButton.setLayoutX(1000);
        PictureMatchButton.setLayoutY(200);

        ImageView imageView5 = new ImageView(image);
        imageView5.setFitHeight(75);
        imageView5.setFitWidth(150);
        Button ListenButton = new Button();
        ListenButton.setGraphic(imageView5);
        ListenButton.setLayoutX(1000);
        ListenButton.setLayoutY(450);

        Pane layout = new Pane();
        setPaneBackground(layout, "/backgrounds/dreamleaf-menu.png");
        layout.getChildren().add(FillInTheSentenceButton);
        layout.getChildren().add(RhymingWordsButton);
        layout.getChildren().add(MixedSentenceButton);
        layout.getChildren().add(PictureMatchButton);
        layout.getChildren().add(ListenButton);
        layout.getChildren().add(imageView10);

        FillInTheSentenceButton.setOnAction(e -> {
            FillInTheSentence game = new FillInTheSentence();
            try {
                game.show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        RhymingWordsButton.setOnAction(e -> {
            RhymingWords game = new RhymingWords();
            try {
                game.show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        MixedSentenceButton.setOnAction(e -> {
            MixedSentence game = new MixedSentence();
            try {
                game.show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        ListenButton.setOnAction(e -> {
            ListenAndChoose game = new ListenAndChoose();
            try {
                game.show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        PictureMatchButton.setOnAction(e -> {
            PictureMatch game = new PictureMatch();
            try {
                game.show(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setPaneBackground(Pane layout, String filePath) {
        Image img = new Image(filePath);
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false)
        );
        layout.setBackground(new Background(backgroundImage));
    }
}

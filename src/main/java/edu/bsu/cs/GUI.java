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
        primaryStage.setTitle("Dreamleaf Learning - Main Menu");

        Image logoImg = new Image("/icons/dreamleaf-logo.png");
        ImageView logoImgView = new ImageView(logoImg);
        logoImgView.setFitWidth(600);
        logoImgView.setFitHeight(150);
        logoImgView.setX(50);
        logoImgView.setY(50);

        Image fillSentenceIconImg = new Image("/icons/fill-sentence-icon.png");
        ImageView imageView1 = new ImageView(fillSentenceIconImg);
        imageView1.setFitWidth(400);
        imageView1.setFitHeight(90);
        Button FillInTheSentenceButton = new Button();
        FillInTheSentenceButton.setGraphic(imageView1);
        FillInTheSentenceButton.setLayoutX(800);
        FillInTheSentenceButton.setLayoutY(160);

        Image rhymingWordsImg = new Image("/icons/rhyming-icon.png");
        ImageView imageView2 = new ImageView(rhymingWordsImg);
        imageView2.setFitWidth(400);
        imageView2.setFitHeight(90);
        Button RhymingWordsButton = new Button();
        RhymingWordsButton.setGraphic(imageView2);
        RhymingWordsButton.setLayoutX(800);
        RhymingWordsButton.setLayoutY(270);

        Image mixedSentenceImg = new Image("/icons/mixed-icon.png");
        ImageView imageView3 = new ImageView(mixedSentenceImg);
        imageView3.setFitWidth(400);
        imageView3.setFitHeight(90);
        Button MixedSentenceButton = new Button();
        MixedSentenceButton.setGraphic(imageView3);
        MixedSentenceButton.setLayoutX(800);
        MixedSentenceButton.setLayoutY(380);

        Image pictureMatchImg = new Image("/icons/picture-match-icon.png");
        ImageView imageView4 = new ImageView(pictureMatchImg);
        imageView4.setFitWidth(400);
        imageView4.setFitHeight(90);
        Button PictureMatchButton = new Button();
        PictureMatchButton.setGraphic(imageView4);
        PictureMatchButton.setLayoutX(800);
        PictureMatchButton.setLayoutY(490);

        Image listenImg = new Image("/icons/listen-choose-icon.png");
        ImageView imageView5 = new ImageView(listenImg);
        imageView5.setFitWidth(400);
        imageView5.setFitHeight(90);
        Button ListenButton = new Button();
        ListenButton.setGraphic(imageView5);
        ListenButton.setLayoutX(800);
        ListenButton.setLayoutY(600);

        Pane layout = new Pane();
        setPaneBackground(layout, "/backgrounds/dreamleaf-menu.png");
        layout.getChildren().add(logoImgView);
        layout.getChildren().add(FillInTheSentenceButton);
        layout.getChildren().add(RhymingWordsButton);
        layout.getChildren().add(MixedSentenceButton);
        layout.getChildren().add(PictureMatchButton);
        layout.getChildren().add(ListenButton);

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

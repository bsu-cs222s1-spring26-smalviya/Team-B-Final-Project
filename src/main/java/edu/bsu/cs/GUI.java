package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class GUI extends Application {
    FillInTheSentence fillInTheSentence = new FillInTheSentence();


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Main Menu");



        //BUTTONS // Fill in the sentence Button
        Image image = new Image(getClass().getResource("/pink-cloud-button.png").toExternalForm());
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(150);   // adjust size
        imageView1.setFitHeight(75);   // adjust size
        Button FillInTheSentenceButton = new Button();
        FillInTheSentenceButton.setGraphic(imageView1);
        FillInTheSentenceButton.setLayoutX(200);
        FillInTheSentenceButton.setLayoutY(200);


        //Rhyming Words Button
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(75);
        imageView2.setFitWidth(150);
        Button RhymingWordsButton = new Button();
        RhymingWordsButton.setGraphic(imageView2);
        RhymingWordsButton.setLayoutX(600);
        RhymingWordsButton.setLayoutY(100);

        //Fix the mixed up Sentence
        ImageView imageView3 = new ImageView(image);
        imageView3.setFitHeight(75);
        imageView3.setFitWidth(150);
        Button MixedSentenceButton = new Button();
        MixedSentenceButton.setGraphic(imageView3);
        MixedSentenceButton.setLayoutX(200);
        MixedSentenceButton.setLayoutY(450);

        //Word to Picture Match
        ImageView imageView4 = new ImageView(image);
        imageView4.setFitHeight(75);
        imageView4.setFitWidth(150);
        Button PictureMatchButton = new Button();
        PictureMatchButton.setGraphic(imageView4);
        PictureMatchButton.setLayoutX(1000);
        PictureMatchButton.setLayoutY(200);

        //Listen And Choose
        ImageView imageView5 = new ImageView(image);
        imageView5.setFitHeight(75);
        imageView5.setFitWidth(150);
        Button ListenButton = new Button();
        ListenButton.setGraphic(imageView5);
        ListenButton.setLayoutX(1000);
        ListenButton.setLayoutY(450);




        Pane layout = new Pane();
       // layout.getChildren().add(button);
        layout.getChildren().add(FillInTheSentenceButton);
        layout.getChildren().add(RhymingWordsButton);
        layout.getChildren().add(MixedSentenceButton);
        layout.getChildren().add(PictureMatchButton);
        layout.getChildren().add(ListenButton);







        // Handle click
        FillInTheSentenceButton.setOnAction(e -> {
            FillInTheSentence game = new FillInTheSentence();
            try {
                game.show(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        RhymingWordsButton.setOnAction(e -> {
            RhymingWords game = new RhymingWords();
            try {
                game.show(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        MixedSentenceButton.setOnAction(e -> {
            MixedSentence game = new MixedSentence();
            try {
                game.show(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        ListenButton.setOnAction(e -> {
            ListenAndChoose game = new ListenAndChoose();
            try {
                game.show(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        PictureMatchButton.setOnAction(e -> {
            PictureMatch game = new PictureMatch();
            try {
                game.show(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //MUST GO LAST
        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}

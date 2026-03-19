package edu.bsu.cs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;

public class FillInTheSentence{
    Button button = new Button();

    public void show(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Fill In The Sentence");
        button.setText("Click me");
        Pane layout = new Pane();

        //MUST GO LAST
        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

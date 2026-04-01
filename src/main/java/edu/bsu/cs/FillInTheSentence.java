package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class FillInTheSentence{
    Button button = new Button();

    public void show(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dreamleaf Learning - Fill In The Sentence");
        button.setText("Click me");
        Pane layout = new Pane();

        //MUST GO LAST
        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

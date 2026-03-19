package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenAndChoose {
    Button button = new Button();

    public void show(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Listen And Choose");
        button.setText("Click me");
        Pane layout = new Pane();

        //MUST GO LAST
        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

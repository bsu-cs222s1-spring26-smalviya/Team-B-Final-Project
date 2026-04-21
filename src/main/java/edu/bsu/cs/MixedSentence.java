package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class MixedSentence{
    Button returnButton = new Button();

    public void show(Stage primaryStage) throws Exception{
        constructUIElements(primaryStage);
        constructStage(primaryStage);
        primaryStage.show();
    }

    private void constructUIElements(Stage stage) {
        returnButton.setText("🏠 Main Menu");
        returnButton.setLayoutX(70.0);
        returnButton.setLayoutY(690.0);
        returnButton.setScaleX(1.5);
        returnButton.setScaleY(1.5);

        returnButton.setOnAction(e -> {
            GUI home = new GUI();
            try {
                home.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void constructStage(Stage stage) {
        stage.setTitle("Dreamleaf Learning - Mixed Sentences");
        Pane layout = new Pane();
        GUI.setPaneBackground(layout, "/backgrounds/dreamleaf-background.png");

        layout.getChildren().add(returnButton);

        Scene scene = new Scene(layout, 1400, 750);
        stage.setScene(scene);
        stage.show();
    }
}

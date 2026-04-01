package edu.bsu.cs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class MixedSentence{
    Button button = new Button();

    public void show(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Mixed Sentences");
        button.setText("Click me");
        Pane layout = new Pane();

        Image image = new Image(getClass().getResource("/icons/pink-cloud-button.png").toExternalForm());
        ImageView imageView3 = new ImageView(image);
        imageView3.setFitHeight(75);
        imageView3.setFitWidth(150);
        Button HomeButton = new Button();
        HomeButton.setGraphic(imageView3);
        HomeButton.setLayoutX(200);
        HomeButton.setLayoutY(450);

        HomeButton.setOnAction(e -> {
            GUI home = new GUI();
            try {
                home.start(primaryStage);  // safe, non-static
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        layout.getChildren().add(HomeButton);
        //MUST GO LAST
        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

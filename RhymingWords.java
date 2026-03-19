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

public class RhymingWords extends Application{

    // Game variables
    int currentQuestion = 0;
    int score = 0;
    String[] questions = {
            "Which word rhymes with CAT?",
            "Which word rhymes with DOG?",
            "Which word rhymes with BALL?",
            "Which word rhymes with SUN?",
            "Which word rhymes with BEE?",
            "Which word rhymes with CAKE?",
            "Which word rhymes with MOON?",
            "Which word rhymes with STAR?",
            "Which word rhymes with BLUE?",
            "Which word rhymes with BED?",
            "Which word rhymes with HOUSE?",
            "Which word rhymes with PLAY?"
    };

    String[][] options = {
            {"Dog","Hat","Sun"},
            {"Log","Tree","Book"},
            {"Wall","Cup","Fish"},
            {"Run","Tree","Cat"},
            {"Tree","Ball","Hat"},
            {"Bake","Ball","Dog"},
            {"Spoon","Cup","Pen"},
            {"Car","Ball","Fish"},
            {"Shoe","Hat","Tree"},
            {"Red","Ball","Cup"},
            {"Mouse","Tree","Sun"},
            {"Day","Dog","Book"}
    };

    int[] correctAnswers = {1,0,0,0,0,0,0,0,0,0,0,0};

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        show(primaryStage);
    }

    public void show(Stage primaryStage){
        Pane layout = new Pane();
        //start button
        Button startButton = new Button ("Start Game");

        startButton.setStyle("-fx-font-size: 20px; -fx-background-color: pink;");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(80);
        startButton.setLayoutX(600);
        startButton.setLayoutY(300);

        layout.getChildren().add(startButton);

        startButton.setOnAction(e ->{
            layout.getChildren().remove(startButton);

            //Question
            Button questionBox = new Button(getQuestion());
            questionBox.setLayoutX(500);
            questionBox.setLayoutY(150);
            questionBox.setPrefWidth(400);

            layout.getChildren().add(questionBox);

            Button scoreBox = new Button("Score: 0");
            scoreBox.setLayoutX(670);
            scoreBox.setLayoutY(50);
            layout.getChildren().add(scoreBox);

            //Options
            String[] opts = getOptions();

            Button option1 = new Button(opts[0]);
            Button option2 = new Button(opts[1]);
            Button option3 = new Button(opts[2]);

            option1.setLayoutX(550);
            option1.setLayoutY(300);

            option2.setLayoutX(550);
            option2.setLayoutY(370);

            option3.setLayoutX(550);
            option3.setLayoutY(440);

            layout.getChildren().addAll(option1, option2, option3);

            //Click handlers
            option1.setOnAction(ev -> handleAnswer(0, questionBox, scoreBox, option1, option2, option3, layout));
            option2.setOnAction(ev -> handleAnswer(1, questionBox, scoreBox, option1, option2, option3, layout));
            option3.setOnAction(ev -> handleAnswer(2, questionBox, scoreBox, option1, option2, option3, layout));
        });

        //MUST GO LAST

        Scene scene = new Scene(layout, 1400, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rhyming Words");
        primaryStage.show();
    }
    //Handle answer
    public void handleAnswer(int selected, Button questionBox, Button scoreBox, Button o1, Button o2, Button o3, Pane layout){
        boolean correct = checkAnswer(selected);

        if(correct){
            score++;
            questionBox.setText("Correct!");
        }
        else{
            questionBox.setText("Wrong!");
        }

        scoreBox.setText("Score: "+score);

        //Next question
        if(!isFinished()){

            o1.setOnAction(e -> nextQuestion(questionBox, scoreBox, o1, o2, o3, layout));
            o2.setOnAction(e -> nextQuestion(questionBox, scoreBox, o1, o2, o3, layout));
            o3.setOnAction(e -> nextQuestion(questionBox, scoreBox, o1, o2, o3, layout));

        }
        else{
            questionBox.setText("Game Finished! Final Score: "+score);
            o1.setVisible(false);
            o2.setVisible(false);
            o3.setVisible(false);

            //Reset Button
            Button resetButton = new Button("Reset Game");
            resetButton.setLayoutX(660);
            resetButton.setLayoutY(400);

            layout.getChildren().add(resetButton);

            resetButton.setOnAction(e ->{
                currentQuestion = 0;
                score = 0;
                show((Stage) layout.getScene().getWindow());
            });
        }
    }

    //next question
    public void nextQuestion(Button questionBox, Button scoreBox, Button o1, Button o2, Button o3, Pane layout){
        String[] opts = getOptions();

        questionBox.setText(getQuestion());

        o1.setText(opts[0]);
        o2.setText(opts[1]);
        o3.setText(opts[2]);

        o1.setOnAction(e -> handleAnswer(0, questionBox, scoreBox, o1, o2, o3, layout));
        o2.setOnAction(e -> handleAnswer(1, questionBox, scoreBox, o1, o2, o3, layout));
        o3.setOnAction(e -> handleAnswer(2, questionBox, scoreBox, o1, o2, o3, layout));
    }

    // Get current question
    public String getQuestion(){
        return questions[currentQuestion];
    }

    // Get options
    public String[] getOptions(){
        return options[currentQuestion];
    }

    // Check answer
    public boolean checkAnswer(int selectedOption){
        boolean correct = selectedOption == correctAnswers[currentQuestion];
        currentQuestion++;
        return correct;
    }

    // Check if game finished
    public boolean isFinished(){
        return currentQuestion >= questions.length;
    }
}

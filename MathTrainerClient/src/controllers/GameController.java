package controllers;

import entity.ScenesEnum;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.Random;
import java.util.Scanner;


//Denna är kopplad till GameScene

/**
 * Class GameController that extends SceneControllerParent that handles the game(gamesceens) which is a mathgame that runs on a time schedule
 *
 * @author Johanna Dahlborn
 * @version 1.0
 */
public class GameController extends SceneControllerParent implements InitializeSceneInterface {
    public Label countdownLabel = new Label();
    public Label plusLeftLabel;
    public Label plusRightLabel;
    public Label minusLeftLabel;
    public Label minusRightLabel;
    public Label devidedLeftLabel;
    public Label devidedRightLabel;
    public Button startQuiz = new Button();
    public Label additionRightLabel;
    public Label additionLeftLabel;
    public Button answerBtn;
    public TextField sumPlus;
    public TextField sumMinus;
    public TextField sumMulti;
    public TextField sumDiv;

    private int numb1 = (int) (40 * Math.random()) + 1;
    private int numb2 = (int) (40 * Math.random()) + 1;
    private int numb3 = (int) (40 * Math.random()) + 1;
    private int numb4 = (int) (40 * Math.random()) + 1;
    private int numb5 = (int) (10 * Math.random()) + 1;
    private int numb6 = (int) (10 * Math.random()) + 1;
    private int numb8 = (int) (10 * Math.random()) + 1;
    private int numb7 = (int) (numb8 * Math.random()) + 1;
    private int sum, sum1, sum2, sum3;

    private static final Integer STARTTIME = 60;
    private Timeline timeline = new Timeline();
    private Integer timeSeconds = STARTTIME;


    public GameController() {

    }

    /**
     * This method is if you want to quit the game. Ends the quiz and ends the clock.
     */
    public void quitGame(ActionEvent actionEvent) {
        boolean answer = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?", "Är du säker på att du vill avsluta, dina svar sparas inte");
        if (answer) {
            mainController.setScene(ScenesEnum.Exercises);
            startQuiz.setDisable(false);
            timeline.stop();
        }
    }

    @Override
    public void setInitialValues(Object object) {

    }

    /**
     * This method starts the game, and adds all the random values and starts the timer.
     */
    public void startQuiz() {
        plusLeftLabel.setText(String.valueOf(numb1));
        plusRightLabel.setText(String.valueOf(numb2));
        minusLeftLabel.setText(String.valueOf(numb3));
        minusRightLabel.setText(String.valueOf(numb4));
        additionLeftLabel.setText(String.valueOf(numb5));
        additionRightLabel.setText(String.valueOf(numb6));
        devidedLeftLabel.setText(String.valueOf(numb7));
        devidedRightLabel.setText(String.valueOf(numb8));

        startQuiz.setDisable(true);
        timer();


    }

    /**
     * Method that adds a timer to the game
     */
    public void timer() {
        countdownLabel.setText(timeSeconds.toString());
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds = STARTTIME;
        countdownLabel.setTextFill(Color.RED);
        countdownLabel.setText(timeSeconds.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {

                            @Override
                            public void handle(Event event) {
                                timeSeconds--;
                                countdownLabel.setText(timeSeconds.toString());
                                if (timeSeconds <= 0) {
                                    timeline.stop();
                                    CheckAnswer();

                                }
                            }
                        }));
        timeline.playFromStart();
    }

    /**
     * If time is up, game ends
     */
    public void showAlert() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gameover");
                alert.setHeaderText("Tiden är ute, men försök en gång till!");
                alert.showAndWait();
            }
        });
    }

    /**
     *check if answer is correct
     */

    public void CheckAnswer() {
        sum = numb1 + numb2;
        sum1 = numb3 - numb4;
        sum2 = numb5 * numb6;
        sum3 = numb7 / numb8;
        int answer = Integer.parseInt(sumPlus.getText());
        int answer1 = Integer.parseInt(sumMinus.getText());
        int answer2 = Integer.parseInt(sumMulti.getText());
        int answer3 = Integer.parseInt(sumDiv.getText());
        int correctAnswer = 0;
        if (answer == sum) {
            correctAnswer++;
            //sumPlus.setStyle("-fx-control-inner-background: #b2ff59");
            sumPlus.setStyle("-fx-background-color: LIGHTGREEN;");
        } else {
            sumPlus.setStyle("-fx-background-color: PALEVIOLETRED;");

        }
        if (answer1 == sum1){
            sumMinus.setStyle("-fx-background-color: LIGHTGREEN;");
            correctAnswer++;
        }
        else{
            sumMinus.setStyle("-fx-background-color: PALEVIOLETRED;");

        }
        if (answer2 == sum2){
            sumMulti.setStyle("-fx-background-color: LIGHTGREEN;");
            correctAnswer++;

        }
        else{
            sumMulti.setStyle("-fx-background-color: PALEVIOLETRED;");

        }
        if (answer3 == sum3){
            sumDiv.setStyle("-fx-background-color: LIGHTGREEN;");
            correctAnswer++;

        }
        else{
            sumDiv.setStyle("-fx-background-color: PALEVIOLETRED;");
        }
        timeline.stop();
        answerBtn.setDisable(true);
        startQuiz.setDisable(false);
    }
}






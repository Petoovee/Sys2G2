package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sharedEntities.Questions;

import java.awt.*;


/** Class QuizCompletedController that extends SceneControllerParent that handles the fxml file QuizCompleted.fxml and shows the user their final score from the quiz
 * @author Bajram Gerbeshi, Niklas Hultin
 * @version 1.0
 */



public class QuizCompletedController extends SceneControllerParent implements InitializeSceneInterface {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label feedbackLabel;
    @FXML
    private ImageView imageTrophy;

    private Questions[] questions;
    private int score;

    /**
     * This method is used when the user wants to continue to the home screen from the quizCompleted scene.
     * @param actionEvent The button action
     */

    public void continueMenu(ActionEvent actionEvent){
        mainController.reportResult(score);
    }
    /**
     * This method is used to set the results in the final scene and to get the results from the questions array.
     */

    public void setResult(){
        score = 0;
        for (int i = 0; i < questions.length; i++){
            if (questions[i].getCorrectAnswer()){
                score++;
            }
        }
        scoreLabel.setText(score + "/" + questions.length);
        showFeedback(score);
    }

    public void showFeedback(int score){
        if (score == questions.length){
            feedbackLabel.setText("Wow! Full pott!!");
            imageTrophy.setVisible(true);
        } else if (score >= questions.length*0.75){
            feedbackLabel.setText("Bra jobbat! Du kanske till och med kan få alla rätt nästa gång?");
        } else if (score >= (questions.length*0.5)){
            feedbackLabel.setText("Snyggt! Du är godkänd! Med lite övning kanske du kan nå ännu högre?");
        } else if (score >= (questions.length*0.25)){
            feedbackLabel.setText("Nära godkäntgränsen nu. Lite mer övning så sitter det nog. Kämpa på!");
        } else {
            feedbackLabel.setText("Ajdå, det gick ju sådär. Lite mer studier behövs nog innan nästa försök.");
        }
    }

    public void setInitialValues(Object object) {
        questions = (Questions[]) object;
        feedbackLabel.setWrapText(true);
        imageTrophy.setVisible(false);
        setResult();
    }

    public void showAnswers(ActionEvent actionEvent) {
        mainController.showDetailedResults();
    }
}
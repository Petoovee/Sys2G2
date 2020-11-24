package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sharedEntities.Questions;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ResourceBundle;

/** Class QuizController that extends SceneControllerParent that handles the fxml file Quiz.fxml and creates actions in the Scene
 * @author Bajram Gerbeshi
 * @version 1.0
 */


public class QuizController extends SceneControllerParent implements InitializeSceneInterface {

    @FXML
    private Button currentQuestion;
    @FXML
    private RadioButton radioButtonOne;
    @FXML
    private RadioButton radioButtonTwo;
    @FXML
    private RadioButton radioButtonThree;
    @FXML
    private RadioButton radioButtonFour;
    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup Group1;
    @FXML
    private Button previousQuestionButton;
    @FXML
    private Button nextQuestionButton;
    @FXML
    private Button submitResultsButton;

    private int questionNumber = -1;

    private Questions[] questions;

    private QuizCompletedController quizCompleteController;


    public QuizController(){
    }


    /**
     * This method is used both when the user hits the button to move to previous question while in the quiz.
     * @param actionEvent The button action when user clicks previousQuestionButton.
     */
    public void previousQuestion(ActionEvent actionEvent){

        updateLabels(false);
        if( questionNumber == 0){

            previousQuestionButton.setVisible(false);
        }

        if(questionNumber ==  questions.length -2){

            nextQuestionButton.setVisible(true);
            submitResultsButton.setVisible(false);
        }
    }

    /**
     * This method is used when the label at the top right of the screen needs to be updated when the user moves on to the next question and/or previous question
     * @param nextQuestion Boolean that is true if the ueser is in the next question so that the label can be updated.
     */
    public void updateLabels(boolean nextQuestion){



        if (nextQuestion){
            questionNumber++;
        } else{
            questionNumber--;
        }
        LinkedList<String> answerArray = new LinkedList<String>();
        answerArray.add(questions[questionNumber].getAnswer());

        String[] wrongAnswers = questions[questionNumber].getWrongAnswers();

        for (int i = 0; i < wrongAnswers.length; i++ ){
            answerArray.add(wrongAnswers[i]);
        }

        Collections.shuffle(answerArray);
        System.out.println(answerArray + " " + radioButtonOne);

        radioButtonOne.setText(answerArray.removeFirst());
        radioButtonTwo.setText(answerArray.removeFirst());
        radioButtonThree.setText(answerArray.removeFirst());
        radioButtonFour.setText(answerArray.removeFirst());
        currentQuestion.setText((questionNumber + 1) + "/" + questions.length);
        questionLabel.setText(questions[questionNumber].getQuestion());

    }

    /**
     * This method is used to check if the answer from the user is correct and gets sent to questions array so it can be used in QuizCompletedController to send the final score to the final scene.
     * @param questionNumber the number that is shown at the top right section of the screen (where the user is in the quiz).
     */
    public void checkAnswer(int questionNumber){
      RadioButton selectedButton = (RadioButton) Group1.getSelectedToggle();
        System.out.println(questions);
        if (selectedButton.getText().equals(questions[questionNumber].getAnswer())){
            questions[questionNumber].correctAnswer(true, selectedButton.getText());
        } else {questions[questionNumber].correctAnswer(false, selectedButton.getText());
        }
    }

    /**
     * This method is used when the user hits the button to move to next question while in the quiz.
     * @param actionEvent The button action
     */
    public void nextQuestion(ActionEvent actionEvent){
        previousQuestionButton.setVisible(true);


        checkAnswer((questionNumber));
        if(questionNumber ==  questions.length -2){

            nextQuestionButton.setVisible(false);
            submitResultsButton.setVisible(true);
        }
            updateLabels(true);
    }

    public Questions[] getQuestions() {
        return questions;
    }



    /**
     * This method is used when the results quiz is completed
     * @param actionEvent The button action
     */
    public void toResults(ActionEvent actionEvent){
        checkAnswer(questionNumber);
        mainController.quizCompleted();
    }

    /**
     * This method is used when the user hits the button to exit the quiz prematurely
     * @param actionEvent The button action
     */
    public void quitQuiz(ActionEvent actionEvent){

      boolean answer =  mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?" , "Är du säker på att du vill avsluta, dina svar sparas inte" );
      if (answer){
          mainController.setScene(ScenesEnum.Home);
      }
    }

    public void setInitialValues(Object object) {
        questions = (Questions[]) object;
        submitResultsButton.setVisible(false);
        previousQuestionButton.setVisible(false);
        nextQuestionButton.setVisible(true);
        questionNumber = -1;
        updateLabels(true);
        questionLabel.setWrapText(true);
        previousQuestionButton.setVisible(false);
        radioButtonOne.setSelected(true);
    }
}
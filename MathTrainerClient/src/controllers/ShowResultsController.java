package controllers;

import entity.ScenesEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sharedEntities.Questions;

public class ShowResultsController extends SceneControllerParent implements InitializeSceneInterface{
    @FXML
    private TableView<Questions> tableView = new TableView<>();

    @FXML
    private TableColumn<Questions, String> questionColumn;

    @FXML
    private TableColumn<Questions, String> answerColumn;

    @FXML
    private TableColumn<Questions, String> yourAnswerColumn;

    private Questions[] questions;


    @Override
    public void setInitialValues(Object object) {
        questions = (Questions[]) object;
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        yourAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("userAnswer"));

        tableView.setItems(getQuestions());
    }

    public void goToHome(ActionEvent actionEvent) {
        int score = 0;
        for (Questions question : questions){
            if (question.getAnswer().equals(question.getUserAnswer())){
                score++;
            }
        }
        mainController.reportResult(score);
    }

    public void backToScore(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.QuizCompleted);

    }
    /**
     * Get all of the questions
     */
    public ObservableList<Questions> getQuestions()
    {
        ObservableList<Questions> values = FXCollections.observableArrayList();
        for (Questions question : questions) {
            values.add(new Questions(question.getQuestion(), question.getAnswer(), question.getUserAnswer()));
        }
        return values;
    }
}

package controllers;


import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/** Controller ExercisesController handles the Exercises scene where the user can select which quiz they want to enter,
 *  the method reads the user input and starts the relevant quiz, this controller extends MainMenuControllerParent
 * @author Bajram Gerbeshi
 * @version 1.0
 */

public class ExercisesController extends MainMenuControllerParent {
    @FXML
    private Button additionButton;
    @FXML
    private Button geometryButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button placeholderButton;


    /**
     * This method is used when the user hits the button to select which quiz they want to enter.
     *
     * @param actionEvent The button action
     */
    public void buttonClicked(ActionEvent actionEvent) {
        String questions = "Questions ";
        Button button = new Button();

        if (actionEvent.getSource() == additionButton) {
            questions += "Counting";
            button = additionButton;
        } else if (actionEvent.getSource() == geometryButton) {
            questions += "Geometry";
            button = geometryButton;
        } else if (actionEvent.getSource() == statisticsButton) {
            questions += "Statistics";
            button = statisticsButton;
        } else if (actionEvent.getSource() == placeholderButton) {
            questions += "Random";
            button = placeholderButton;
        }
        mainController.takeQuiz(questions);
        //return button;
    }
}

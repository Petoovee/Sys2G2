package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controller for handling button-presses in the scene LogIn.fxml. Each method represent a possible user action.
 * @author Niklas Hultin
 * @version 1.0
 */

public class LogInController extends SceneControllerParent implements InitializeSceneInterface {


    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private String username, password;

    /**
     * Called when the user skips the log in. Confirms with the user if they are sure, and if so skips the login phase.
     * @param actionEvent
     */
    public void skipLogInClicked(ActionEvent actionEvent) {
        boolean answer = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Fortsätt utan att logga in?", "Om du inte loggar in eller skapar en användare kommer ingenting att sparas. " +
                "Är du säker på att du vill fortsätta utan att logga in?");
        if (answer){
            //ToDo: Kod för att spela som gäst
            mainController.skipLogin();
        }
    }

    /**
     * Called when the user clicks log in. Gets the user information and sends it to the main controller.
     * @param actionEvent
     */
    public void logInClicked(ActionEvent actionEvent) {
        username = usernameField.getText();
        password = passwordField.getText();
        mainController.logIn(username, password);
    }

    /**
     * Called when the user chooses to create a new user.
     * @param actionEvent
     */
    public void newUserClicked(ActionEvent actionEvent) {
        mainController.createNewUser();
    }

    /**
     * Called if the user wants to exit the program. MainController handles this call manually.
     * @param actionEvent
     */
    public void exitClicked(ActionEvent actionEvent) {
        mainController.closeProgram();
    }

    @Override
    public void setInitialValues(Object object) {
        usernameField.setText("");
        passwordField.setText("");
    }
}

package controllers;


import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for handling button-presses in the scene NewUser.fxml. Each method represent a possible user action.
 * @author Niklas Hultin
 * @version 1.0
 */

public class NewUserController extends SceneControllerParent implements InitializeSceneInterface {
    @FXML
    TextField username, school, city;
    @FXML
    PasswordField password, passwordRepeat;
    @FXML
    ChoiceBox year;

    /**
     * Returns to the login scene if the user backs out of creating a new user.
     * @param actionEvent
     */
    public void backClicked(ActionEvent actionEvent){
        mainController.setScene(ScenesEnum.LogIn);
    }

    /**
     * Checks that the values for username and password are reasonable and if so, sends them to the MainController.
     * @param actionEvent
     */
    public void createUserClicked(ActionEvent actionEvent) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username.getText());
        if (username.getText().length() < 5 || username.getText().length() > 20) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Användarnamnet är för kort eller långt", "Användarnamnet måste vara mellan 5 och 20 tecken långt");
        } else if(!matcher.matches()){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Specialtecken är ej tillåtna", "Användarnamnet får ej innehålla specialtecken, som t ex !, &, *");
        } else if (!password.getText().equals(passwordRepeat.getText())) {
            mainController.popUpWindow(Alert.AlertType.ERROR, "Felaktigt lösenord", "Lösenorden du angav stämmer ej överens med varandra");
        } else if (password.getText().length() < 6){
            mainController.popUpWindow(Alert.AlertType.ERROR, "Lösenordet är för kort", "Lösenordet måste vara minst 6 tecken långt");
        } else {
            mainController.newUser(username.getText(), password.getText(), city.getText(), school.getText(), year.getValue());
        }
    }

    @Override
    public void setInitialValues(Object object) {
        username.setText("");
        school.setText("");
        city.setText("");
        password.setText("");
        passwordRepeat.setText("");
        year.getSelectionModel().select(0);
    }
}

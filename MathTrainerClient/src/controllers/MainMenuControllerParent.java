package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;

/**
 * An abstract class that extends the SceneController, as this too will be used by scene controllers. All Main Menu
 * controllers extend this class. It defines the code for the left menu panel which stays
 * the same until you leave the main menu completely.
 * @author Niklas Hultin
 * @version 1.0
 */

public abstract class MainMenuControllerParent extends SceneControllerParent {

    /**
     * Handles the event where the user clicks the LogOut-button.
     * @param actionEvent
     */
    public void logOutClicked(ActionEvent actionEvent){
        mainController.logOut();
    }

    /**
     * Changes to the home scene when the user clicks Home.
     * @param actionEvent
     */
    public void homeButtonClicked(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.Home);
    }

    /**
     * Changes to the scene for choice of exercise categories when the user clicks Exercises.
     * @param actionEvent
     */
    public void exercisesButtonClicked(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.Exercises);
    }

    /**
     * Changes to the scene for national tests.
     * @param actionEvent
     */
    public void gameButtonClicked(ActionEvent actionEvent) {
        mainController.startGameSceneSetup();
    }


    /**
     * Changes to the settings scene.
     * @param actionEvent
     */
    public void settingsButtonClicked(ActionEvent actionEvent) {
        mainController.startSettingsScene();
    }

    /**
     * Handles the exit event if user clicks "Exit". The MainController handles this event manually.
     * @param actionEvent
     */
    public void exitClicked(ActionEvent actionEvent){
        mainController.closeProgram();
    }

}

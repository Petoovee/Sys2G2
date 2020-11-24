package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sharedEntities.User;

/**
 * This class is the initial view before starting up the game. It displays information about the game including the
 * users highest score in the game if the user is logged in. Clicking the button leads to the actual game scene.
 */
public class StartGameController extends MainMenuControllerParent implements InitializeSceneInterface {
    @FXML
    Label bestScoreLabel;
    @FXML
    Label gameInformationLabel;


    @Override
    public void setInitialValues(Object object) {
        gameInformationLabel.setWrapText(true);
        if(object instanceof User){
            User user = (User) object;
            //TODO: Nån metod för att hämta ett score från user (som bör lagras vid avklarat spel).
            // Detta skrivs sen till bestScoreLabel.
            bestScoreLabel.setText("Inget resultat registrerat");
        } else {
            bestScoreLabel.setText("Logga in för att se ditt resultat!");
            bestScoreLabel.setTextFill(Color.web("#ff0000", 0.8));
        }
    }

    public void startGameClicked(ActionEvent actionEvent) {
        mainController.setScene(ScenesEnum.Game);
    }
}

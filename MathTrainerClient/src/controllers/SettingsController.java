package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sharedEntities.User;

public class SettingsController extends MainMenuControllerParent implements InitializeSceneInterface {
	@FXML
	public Label userInfoLabel;
	private User user;

	@Override
	public void setInitialValues(Object object) {
		if (object != null) {
			user = (User) object;
			userInfoLabel.setText("Anv�ndarnamn: " + user.getUserName() + "\n" + "Årskurs: " + user.getYear() + "\n"
					+ "Skola: " + user.getSchool() + "\n");
		} else {
			userInfoLabel.setText("");
		}
	}

	// userInfoLabel.setText("Årskurs " + user.getYear() + "\n" + user.getSchool()
	// + "\n");
	// ToDO: Metoder f�r att hantera de actions anv�ndaren kan g�ra i settings.
}

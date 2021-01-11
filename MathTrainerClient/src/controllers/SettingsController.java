package controllers;

import entity.ScenesEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import sharedEntities.User;

public class SettingsController extends MainMenuControllerParent implements InitializeSceneInterface {
	@FXML
	public Label userInfoLabel;
	public Label settingsLabel;

	public Label passwordLabel;
	public PasswordField password;

	public Label schoolLabel;
	public TextField school;

	public Label cityLabel;
	public TextField city;

	public Label yearLabel;
	public ChoiceBox year;


	public Button changeSettings;

	private User user;

	@Override
	public void setInitialValues(Object object) {
		setComponentsVisible(true);
		settingsLabel.setText("\n\nÄndra uppgifter:");
		if (object != null) {
			user = (User) object;
			userInfoLabel.setText("Användarnamn: " + user.getUserName() + "\n" + "Årskurs: " + user.getYear() + "\n"
					+ "Skola: " + user.getSchool() + "\n");

			password.setText(user.getPassword());
			school.setText(user.getSchool());
			city.setText(user.getTown());
			if(user.getYear() == 6) {
				year.getSelectionModel().select(0);
			} else if(user.getYear() == 7) {
				year.getSelectionModel().select(1);
			}else if(user.getYear() == 8) {
				year.getSelectionModel().select(2);
			}else if(user.getYear() == 9) {
				year.getSelectionModel().select(3);
			}
		} else {
			userInfoLabel.setText("");
			// Ifall användaren är inte inloggad gör komponentera osynliga.
			setComponentsVisible(false);
		}
	}

	public void setComponentsVisible(boolean choice) {
		settingsLabel.setVisible(choice);
		passwordLabel.setVisible(choice);
		password.setVisible(choice);
		schoolLabel.setVisible(choice);
		school.setVisible(choice);
		cityLabel.setVisible(choice);
		city.setVisible(choice);
		yearLabel.setVisible(choice);
		year.setVisible(choice);
		changeSettings.setVisible(choice);
	}

	public void changeSettings(ActionEvent actionEvent) {
		if(password.getText().toString().length() >= 6 ) {
			boolean confirm = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Inställningar",
					"Är du säker på att du vill ändra dina uppgifter?");
			if (confirm) {
				mainController.changeUser(user.getUserName(), password.getText(), city.getText(), school.getText(), year.getValue());
				mainController.setScene(ScenesEnum.Home);
			}
		} else {
			mainController.popUpWindow(Alert.AlertType.ERROR, "Lösenordet är för kort",
					"Lösenordet måste vara minst 6 tecken långt.");
		}
	}
}

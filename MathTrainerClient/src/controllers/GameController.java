package controllers;

import entity.ScenesEnum;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.Random;
import java.util.Scanner;

//Denna �r kopplad till GameScene

/**
 * Class GameController that extends SceneControllerParent that handles the
 * game(gamesceens) which is a mathgame that runs on a time schedule
 *
 * @author Johanna Dahlborn
 * @version 1.0
 */
public class GameController extends SceneControllerParent implements InitializeSceneInterface {
	public Label countdownLabel = new Label();
	public Label plusLeftLabel;
	public Label plusRightLabel;
	public Label minusLeftLabel;
	public Label minusRightLabel;
	public Label devidedLeftLabel;
	public Label devidedRightLabel;
	public Button startQuiz = new Button();
	public Label additionRightLabel;
	public Label additionLeftLabel;
	public Button answerBtn;
	public TextField sumPlus;
	public TextField sumMinus;
	public TextField sumMulti;
	public TextField sumDiv;

	private int numb1;
	private int numb2;
	private int numb3;
	private int numb4;
	private int numb5;
	private int numb6;
	private int numb8;
	private int numb7;
	private int sum, sum1, sum2;
	private double sum3;

	private static final Integer STARTTIME = 60;
	private Timeline timeline = new Timeline();
	private Integer timeSeconds = STARTTIME;

	public GameController() {
		
	}

	/**
	 * This method is if you want to quit the game. Ends the quiz and ends the
	 * clock.
	 */
	public void quitGame(ActionEvent actionEvent) {
		boolean answer = mainController.popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?",
				"�r du s�ker p� att du vill avsluta, dina svar sparas inte");
		if (answer) {
			resetGame();
		}
	}


	@Override
	public void setInitialValues(Object object) {
		answerBtn.setDisable(true);
	}

	public void resetGame() {
		mainController.setScene(ScenesEnum.Exercises);
		startQuiz.setDisable(false);
		timeline.stop();
		
		// Reset labels
		plusLeftLabel.setText("?");
		plusRightLabel.setText("?");
		minusLeftLabel.setText("?");
		minusRightLabel.setText("?");
		additionLeftLabel.setText("?");
		additionRightLabel.setText("?");
		devidedLeftLabel.setText("?");
		devidedRightLabel.setText("?");
		countdownLabel.setText(STARTTIME.toString());
	}
	
	/**
	 * This method starts the game, and adds all the random values and starts the
	 * timer.
	 */
	public void startQuiz() {

		// Generate new quiz
		numb1 = (int) (40 * Math.random()) + 1;
		numb2 = (int) (40 * Math.random()) + 1;
		numb3 = (int) (40 * Math.random()) + 1;
		numb4 = (int) (40 * Math.random()) + 1;
		numb5 = (int) (10 * Math.random()) + 1;
		numb6 = (int) (10 * Math.random()) + 1;
		numb8 = (int) (10 * Math.random()) + 1;
		numb7 = (int) (numb8 * Math.random()) + 1;

		plusLeftLabel.setText(String.valueOf(numb1));
		plusRightLabel.setText(String.valueOf(numb2));
		minusLeftLabel.setText(String.valueOf(numb3));
		minusRightLabel.setText(String.valueOf(numb4));
		additionLeftLabel.setText(String.valueOf(numb5));
		additionRightLabel.setText(String.valueOf(numb6));
		devidedLeftLabel.setText(String.valueOf(numb7));
		devidedRightLabel.setText(String.valueOf(numb8));

		// Reset user input
		sumPlus.setText("");
		sumMinus.setText("");
		sumMulti.setText("");
		sumDiv.setText("");

		// Reset field colors
		sumPlus.setStyle("-fx-background-color: WHITE;");
		sumMinus.setStyle("-fx-background-color: WHITE;");
		sumMulti.setStyle("-fx-background-color: WHITE;");
		sumDiv.setStyle("-fx-background-color: WHITE;");

		// Re-enable answerBtn
		startQuiz.setDisable(true);
		answerBtn.setDisable(false);
		timer();

	}

	/**
	 * Method that adds a timer to the game
	 */
	public void timer() {
		countdownLabel.setText(timeSeconds.toString());
		if (timeline != null) {
			timeline.stop();
		}
		timeSeconds = STARTTIME;
		countdownLabel.setTextFill(Color.RED);
		countdownLabel.setText(timeSeconds.toString());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
			
			@Override
			public void handle(Event event) {
				timeSeconds--;
				countdownLabel.setText(timeSeconds.toString());
				if (timeSeconds <= 0) {
					timeline.stop();
					CheckAnswer();
				}
			}
		}));
		timeline.playFromStart();
	}

	/**
	 * If time is up, game ends
	 */
	public void showAlert() {
		Platform.runLater(new Runnable() {
			public void run() {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Gameover");
				alert.setHeaderText("Tiden �r ute, men f�rs�k en g�ng till!");
				alert.showAndWait();
			}
		});
	}

	/**
	 * check if answer is correct
	 */

	private void answerColor(TextField field, boolean isCorrect) {
		if (isCorrect) {
			field.setStyle("-fx-background-color: LIGHTGREEN;");
		} else {
			field.setStyle("-fx-background-color: PALEVIOLETRED;");
		}
	}
	
	private boolean checkValidity(TextField field) {
		try {
			Double.parseDouble(field.getText());
		} catch (Exception e) {
			answerColor(field, false);
			return false;			
		}
		return true;
	}

	public void CheckAnswer() {		
		int answer, answer1, answer2 = 9999;
		double answer3 = 9999;

		
		if(checkValidity(sumPlus)) {
			sum = numb1 + numb2;
			answer = Integer.parseInt(sumPlus.getText());
			answerColor(sumPlus, (answer == sum));
		}
		
		if(checkValidity(sumMinus)) {			
			sum1 = numb3 - numb4;
			answer1 = Integer.parseInt(sumMinus.getText());
			answerColor(sumMinus, (answer1 == sum1));
		}
		
		if(checkValidity(sumMulti)) {
			sum2 = numb5 * numb6;
			answer2 = Integer.parseInt(sumMulti.getText());
			answerColor(sumMulti, (answer2 == sum2));
		}
		
		if(checkValidity(sumDiv)) {
			sum3 = numb7 / (double) numb8;
			sum3 = Math.round(sum3 * 100.0) / 100.0; // Round division answers to two decimals
			answer3 = Math.round(answer3 * 100.0) / 100.0;
			answer3 = Double.parseDouble(sumDiv.getText());
			answerColor(sumDiv, (Double.compare(answer3, sum3) == 0)); // Cannot directly compare two doubles.			
		}

		timeline.stop();
		answerBtn.setDisable(true);
		startQuiz.setDisable(false);
	}
}

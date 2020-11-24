package main;

import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main is responsible for starting the application, and instantly hands over control of the Stage to MainController.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        new MainController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
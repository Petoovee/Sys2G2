package controllers;

import entity.ScenesEnum;
import entity.ScenesHashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sharedEntities.Questions;
import sharedEntities.User;

import java.io.IOException;

/**
 * Class MainController is controller of the other controllers. All scene controllers has a reference to this controller
 * to communicate with it. NetworkController communicates with it through the buffers. In this class, only the general
 * and recurring logic is handled, such as setting scenes (inner class), pop-up windows and closing the program.
 * @author Niklas Hultin, Bajram Gerbeshi
 * @version 1.5
 */

public class MainController {
    private Stage mainWindow;
    private User currentUser;
    private SceneSetter sceneSetter = new SceneSetter();
    private NetworkController networkController;
    private Questions[] currentQuiz;
    private String currentCategory;

    /**
     * Starts the network that connects to the server and creates and populates the ScenesHashMap.
     * It also sets up the window with the correct information for the start screen.
     * @param mainWindow The main window where the scenes are displayed (received from Main)
     * @author Niklas Hultin
     */
    public MainController(Stage mainWindow) {
        this.mainWindow = mainWindow;

         networkController = new NetworkController();

        try {
            sceneSetter.addScenesToHashMap();
        } catch (IOException e) {
            System.out.println("Error loading scenes");
            e.printStackTrace();
        }


        mainWindow.setOnCloseRequest(e -> {//Denna metod bestämmer vad som händer när man trycker på krysset i fönstret.
            e.consume();                //Detta stoppar close-eventen (consume) och skickar istället programmet till
            closeProgram();             //metoden closeProgram() som finns längre ner. Den skapar en confirmation ruta.
        });
        this.mainWindow.setTitle("MathTrainer");
        sceneSetter.setScene(ScenesEnum.LogIn);
        this.mainWindow.show();
    }

    /**
     * Uses the interface SceneControllerParent (used by all Scene-controllers) to send itself to the scene-controller for
     * the FXML-file loaded in to the FXMLLoader. This gives the Scene-controllers a reference to this MainController.
     * @param loader The loader for a specific FXML-file. This gives access to that files specified controller.
     * @author Niklas Hultin
     */
    public void sendSelfToControllers(FXMLLoader loader) {
        ((SceneControllerParent) loader.getController()).setMainController(this);
    }

    /**
     * Changes the current scene that is being displayed on the Stage.
     * @param sceneToShow The ScenesEnum-name of the Scene that should be displayed.
     * @author Niklas Hultin
     */
    public void setScene(ScenesEnum sceneToShow){
        sceneSetter.setScene(sceneToShow);
    }

    /**
     * Uses a static class Alarm to create Pop-Up boxes. The box will be the only interactive object until it has been
     * handled by the user. The code is only made for using the AlertTypes Confirm (Yes/no-option) and Error (Only OK).
     * @param alertType The type of box. Confirm gives a box with OK and Cancel buttons. Error only gives an OK button.
     * @param title The title of the pop-up window
     * @param message The message shown in the pop-up window.
     * @return Returns true if the user pressed the OK button, otherwise returns false. The return value is not always
     * useful (the Error Pop-Up only has one option, for example) and it is up to the developer to handle it correctly.
     * @author Niklas Hultin
     */
    public boolean popUpWindow (Alert.AlertType alertType, String title, String message){
        boolean choice = false;

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if (alert.showAndWait().get().equals(ButtonType.OK)){
            choice = true;
        }
        return choice;
    }

    /**
     * This method is used both when the user hits the button to close to program, and if the user tries to shut down
     * the program using the standard close option in the OS (for example: X in the top right corner of the window).
     * @author Niklas Hultin
     */
    public void closeProgram(){
        boolean answer = popUpWindow(Alert.AlertType.CONFIRMATION, "Avsluta?", "Är du säker på att du vill avsluta MathTrainer?");
        if (answer){
            mainWindow.close();
        }
    }

    /**
     * Method is used when creating a new user. It is sent to the server and depending on the result, the new user is
     * either logged in or an error message is shown.
     * @param username
     * @param password
     * @author Niklas Hultin
     */
    public void newUser(String username, String password, String city, String school, Object year){
        currentUser = new User(username, password, city, school, year);
        Object returnValue = networkController.sendRequest("NewUser", currentUser);

        if (returnValue instanceof User) {
            currentUser = (User) returnValue;
            setScene(ScenesEnum.Home);
            setInitialValueOfScene(currentUser);
        } else{
            String errorString = (String) returnValue;
            popUpWindow(Alert.AlertType.ERROR, errorString.substring(0, errorString.indexOf(':')), errorString.substring(errorString.indexOf(':')+2));
        }
    }

    /**
     * Log-in credentials are sent to the server. The user is either logged in, or an error message is shown.
     * @param username
     * @param password
     * @author Niklas Hultin
     */
    public void logIn(String username, String password){
        currentUser = new User(username, password);
        if (username.isBlank() || password.isBlank() ){
            popUpWindow(Alert.AlertType.ERROR, "Felaktiga användaruppgifter", "Du måste fylla i både användarnamn och lösenord");
        } else {
            Object returnValue = networkController.sendRequest("Login", currentUser);
            if (returnValue instanceof User) {
                currentUser = (User) returnValue;
                setScene(ScenesEnum.Home);
                setInitialValueOfScene(currentUser);
            } else {
                String errorString = (String) returnValue;
                popUpWindow(Alert.AlertType.ERROR, errorString.substring(0, errorString.indexOf(':')), errorString.substring(errorString.indexOf(':') + 2));
            }
        }
    }

    /**
     * Method is used to pass object of questions to network Controller and handle the return value.
     * @param quiz The quiz String that is used to send it to the neworkController
     * @author Bajram Gerbeshi
     */
    public void takeQuiz(String quiz){
        Object returnValue = networkController.sendRequest(quiz);
        if (returnValue instanceof Questions[]) {
            currentQuiz = (Questions[]) returnValue;
            currentCategory = quiz.substring(quiz.indexOf(' ') + 1);
            setScene(ScenesEnum.Quiz);
            setInitialValueOfScene(currentQuiz);

        } else {
            popUpWindow(Alert.AlertType.ERROR, "Error" , (String) returnValue);
        }
    }

    /**
     * Used to update the results, if needed, stored in the user object and send that information to the server.
     * @param score
     * @author Niklas Hultin
     */
    public void reportResult(int score) {
        if (currentUser != null) {
            int[] updatedResults = currentUser.getResults();
            int indexToChange = 4;
            if (currentCategory.equals("Counting")) {
                indexToChange = 0;
            } else if (currentCategory.equals("Geometry")) {
                indexToChange = 3;
            } else if (currentCategory.equals("Statistics")) {
                indexToChange = 1;
            } else if (currentCategory.equals("Random")) {
                indexToChange = 2;
            }

            if (score > updatedResults[indexToChange]) {
                updatedResults[indexToChange] = score;
                currentUser.setResults(updatedResults);
                Object returnValue = networkController.sendRequest("Result", currentUser);

                if (returnValue instanceof User) {
                    currentUser = (User) returnValue;
                    setScene(ScenesEnum.Home);
                    setInitialValueOfScene(currentUser);
                } else {
                    String errorString = (String) returnValue;
                    popUpWindow(Alert.AlertType.ERROR, errorString.substring(0, errorString.indexOf(':')), errorString.substring(errorString.indexOf(':') + 2));
                }
            } else {
                setScene(ScenesEnum.Home);
            }
        } else {
            setScene(ScenesEnum.Home);
        }
    }

    /**
     * Generic way of setting the initial values of a scene when it is being shown. Each Scene's controller knows how
     * to handle the type of object that they will recieve.
     * @param object
     * @author Niklas Hultin
     */
    public void setInitialValueOfScene(Object object){
        InitializeSceneInterface parent = ((FXMLLoader) mainWindow.getScene().getUserData()).getController();
        parent.setInitialValues(object);
    }

    /**
     * Changes the scene to show the result of the quiz that was just finished.
     * @author Bajram Gerbeshi
     */
    public void quizCompleted(){
        setScene(ScenesEnum.QuizCompleted);
        setInitialValueOfScene(currentQuiz);
    }

    /**
     * Logs the user out (as no constant connection is held with the server, the server is not informed). The change of
     * scene and currentUser ensures that the same user cannot make any changes without logging in again.
     * @author Niklas Hultin
     */
    public void logOut() {
        sceneSetter.setScene(ScenesEnum.LogIn);
        setInitialValueOfScene(null);
        currentUser = null;
    }

    /**
     * Changes to the scene for creating a new user
     * @author Niklas Hultin
     */
    public void createNewUser() {
        sceneSetter.setScene(ScenesEnum.NewUser);
        setInitialValueOfScene(null);
    }

    /**
     * Changes to the home scene without logging in. This means no user information is being stored.
     * @author Niklas Hultin
     */
    public void skipLogin() {
        sceneSetter.setScene(ScenesEnum.Home);
        setInitialValueOfScene(null);
    }

    /**
     * Changes to the game start-up scene. The initial values of the scene varies depend if currentUser is null or not.
     * @author Niklas Hultin
     */
    public void startGameSceneSetup() {
        sceneSetter.setScene(ScenesEnum.StartGame);
        setInitialValueOfScene(currentUser);
    }

    public void startSettingsScene() {
        sceneSetter.setScene(ScenesEnum.Settings);
        setInitialValueOfScene(currentUser);
    }

    public void showDetailedResults() {
        sceneSetter.setScene(ScenesEnum.Results);
        setInitialValueOfScene(currentQuiz);
    }

    /**
     * Inner class SceneSetter handles the Scenes. It loads them, hands over the controllers to the MainController
     * for communication, and handles communication with the ScenesHashmap. It also sets up the current scene.
     * @author Niklas Hultin
     * @version 1.0
     */
    private class SceneSetter {
        private ScenesHashMap scenes = new ScenesHashMap();

        /**
         * Loads the scenes and adds every fxml-scene to the HashMap with the corresponding ScenesEnum-name.
         * @throws IOException
         */
        private void addScenesToHashMap() throws IOException {
            FXMLLoader logInLoader = new FXMLLoader(getClass().getResource("../scenes/LogIn.fxml"));
            Scene logInScene = new Scene(logInLoader.load());
            logInScene.setUserData(logInLoader);
            sendSelfToControllers(logInLoader);

            FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("../scenes/NewUser.fxml"));
            Scene newUserScene = new Scene(newUserLoader.load());
            newUserScene.setUserData(newUserLoader);
            sendSelfToControllers(newUserLoader);

            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/Home.fxml"));
            Scene homeScene = new Scene(homeLoader.load());
            homeScene.setUserData(homeLoader);
            sendSelfToControllers(homeLoader);

            FXMLLoader exercisesLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/Exercises.fxml"));
            Scene exercisesScene = new Scene(exercisesLoader.load());
            exercisesScene.setUserData(exercisesLoader);
            sendSelfToControllers(exercisesLoader);

            FXMLLoader quizLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/Quiz.fxml"));
            Scene quizScene = new Scene(quizLoader.load());
            quizScene.setUserData(quizLoader);
            sendSelfToControllers(quizLoader);

            FXMLLoader quizCompletedLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/QuizCompleted.fxml"));
            Scene quizCompletedScene = new Scene(quizCompletedLoader.load());
            quizCompletedScene.setUserData(quizCompletedLoader);
            sendSelfToControllers(quizCompletedLoader);

            FXMLLoader startGameLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/StartGame.fxml"));
            Scene startGameScene = new Scene(startGameLoader.load());
            startGameScene.setUserData(startGameLoader);
            sendSelfToControllers(startGameLoader);

            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/Settings.fxml"));
            Scene settingsScene = new Scene(settingsLoader.load());
            settingsScene.setUserData(settingsLoader);
            sendSelfToControllers(settingsLoader);

            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("../scenes/GameScene.fxml"));
            Scene gameScene = new Scene(gameLoader.load());
            gameScene.setUserData(gameLoader);
            sendSelfToControllers(gameLoader);

            FXMLLoader resultsLoader = new FXMLLoader(getClass().getResource("../scenes/mainMenu/Results.fxml"));
            Scene resultsScene = new Scene(resultsLoader.load());
            resultsScene.setUserData(resultsLoader);
            sendSelfToControllers(resultsLoader);


            scenes.put(ScenesEnum.LogIn, logInScene);
            scenes.put(ScenesEnum.NewUser, newUserScene);
            scenes.put(ScenesEnum.Home, homeScene);
            scenes.put(ScenesEnum.Exercises, exercisesScene);
            scenes.put(ScenesEnum.Quiz, quizScene);
            scenes.put(ScenesEnum.QuizCompleted, quizCompletedScene);
            scenes.put(ScenesEnum.StartGame, startGameScene);
            scenes.put(ScenesEnum.Settings, settingsScene);
            scenes.put(ScenesEnum.Game, gameScene);
            scenes.put(ScenesEnum.Results, resultsScene);
        }

        /**
         * Sets the current scene in the main window.
         * @param sceneName The ScenesEnum name of the scene you want displayed.
         */
        public void setScene(ScenesEnum sceneName) {
            if (scenes.get(sceneName) != mainWindow.getScene())
            mainWindow.setScene(scenes.get(sceneName));
        }
    }
}
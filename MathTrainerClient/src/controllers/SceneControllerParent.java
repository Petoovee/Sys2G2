package controllers;

/**
 * An interface that all scene-controllers will implement, so that they can easily communicate with the MainController
 * @author Niklas Hultin
 * @version 1.0
 */

public abstract class SceneControllerParent {
    protected MainController mainController;

    /**
     * Sets the MainController to make it available to all scene controllers.
     * @param mainController
     */
    public void setMainController(MainController mainController){
           this.mainController = mainController;
    }
}

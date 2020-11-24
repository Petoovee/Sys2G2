package controllers;

/**
 * All scenes that have non-static values as they are entered will implement this interface. The method is called
 * by the main-controller, which sends a relevant object with data for the scene to display. It is up to each
 * scenes controller to handle this data correctly.
 * @author Niklas Hultin
 */
public interface InitializeSceneInterface {

    public abstract void setInitialValues(Object object);
}

package entity;


import javafx.scene.Scene;

import java.util.HashMap;

/**
 * Class ScenesHashMap creates a HashMap of pairs for easy access to scene. The name of a SceneEnum pairs with a
 * corresponding scene. New scenes are added with put. To get a scene you enter the SceneEnum-name of the scene.
 * @author Niklas Hultin
 * @version 1.0
 */

public class ScenesHashMap {
    private HashMap<ScenesEnum, Scene> scenes = new HashMap<>();

    public synchronized void put(ScenesEnum name, Scene scene){
        scenes.put(name, scene);
    }

    public synchronized Scene get(ScenesEnum name){ return scenes.get(name); }
}

package nl.icode4living.pokemahn.scenery;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Stack;

public class SceneryManager {

    private static SceneryManager instance;

    public static SceneryManager getInstance() {
        if (instance == null) {
            instance = new SceneryManager();
        }
        return instance;
    }

    Stack<Scenery> scenes;

    private SceneryManager() {
        scenes = new Stack<Scenery>();
    }

    public void push(Scenery scenery) {
        scenes.push(scenery);
    }

    public void pop(){
        scenes.pop();
    }

    public void set(Scenery scenery){
        scenes.pop();
        scenes.push(scenery);
    }

    public void update(float delta, OrthographicCamera camera) {
        scenes.peek().update(delta, camera);
    }

    public void render(float delta, OrthographicCamera camera) {
        scenes.peek().render(delta, camera);
    }

    public World getCurrentSceneWorld() {
        return scenes.peek().getWorld();
    }

    public String getCurrentSceneId(){
        return scenes.peek().getId();
    }
}

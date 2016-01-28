package nl.icode4living.pokemahn.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.connection.GameSocket;
import nl.icode4living.pokemahn.entities.CoTrainer;
import nl.icode4living.pokemahn.entities.Trainer;
import nl.icode4living.pokemahn.scenery.SceneryManager;
import nl.icode4living.pokemahn.scenery.cities.Aldbrough;
import nl.icode4living.pokemahn.ui.scenes.Hud;

public class GameScreen implements Screen {

    //Reference to our Game, used to set Screens
    private GdxGame game;

    //basic GameScreen variables
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;

    //Trainer objects
    private Trainer player;
    private Map<String, CoTrainer> onlinePlayers;

    public GameScreen(GdxGame game, float playerX, float playerY) {
        this.game = game;


        camera = new OrthographicCamera();
        viewport = new FitViewport(GdxGame.V_WIDTH / GdxGame.PPM, GdxGame.V_HEIGHT / GdxGame.PPM, camera);

        hud = new Hud(game.batch);

        SceneryManager.getInstance().push(new Aldbrough());

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        player = new Trainer(this, SceneryManager.getInstance().getCurrentSceneId(), playerX, playerY, 7);
        onlinePlayers = new HashMap<String, CoTrainer>();

        GameSocket.getInstance().connect();
        GameSocket.getInstance().linkToGameScreen(this);
    }

    private void update(float delta) {
        handleInput(delta);

        player.update(delta);
        camera.position.set(player.getBody().getPosition(), 0);
        SceneryManager.getInstance().update(delta, camera);

    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.move(Input.Keys.UP);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.move(Input.Keys.DOWN);
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.move(Input.Keys.LEFT);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.move(Input.Keys.RIGHT);
        else
            player.doNotMove();
    }

    @Override
    public void render(float delta) {
        update(delta);
        GameSocket.getInstance().sendUpdate(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        SceneryManager.getInstance().render(delta, camera);

        game.batch.begin();
        player.draw(game.batch);
        for (CoTrainer trainer : onlinePlayers.values()) {
            if(trainer.getLocationIdentifier().equals(SceneryManager.getInstance().getCurrentSceneId()))
                trainer.draw(game.batch);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void playerJoins(String id, String locationId, float x, float y) {
        onlinePlayers.put(
                id,
                new CoTrainer(
                        this,
                        locationId,
                        x,
                        y
                )
        );
    }

    public void removePlayer(String id) {
        onlinePlayers.remove(id);
    }

    public GdxGame getGame() {
        return game;
    }

    public Trainer getPlayer() {
        return player;
    }

    public Map<String, CoTrainer> getOnlinePlayers() {
        return onlinePlayers;
    }
}

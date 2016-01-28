package nl.icode4living.pokemahn.scenery;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.entities.pokemon.pokedex.Pokemon;
import nl.icode4living.pokemahn.util.B2WorldCreator;
import nl.icode4living.pokemahn.util.GdxSoundsManager;
import nl.icode4living.pokemahn.util.WorldContactListener;

public abstract class Scenery {

    protected final String id;

    protected final TmxMapLoader mapLoader;
    protected final OrthogonalTiledMapRenderer renderer;
    protected final Box2DDebugRenderer b2dr;
    protected final TiledMap map;

    protected final B2WorldCreator creator;
    protected final World world;

    List<Pokemon> catchablePokemons;

    public Scenery(final String id) {
        this.id = id;

        world = new World(new Vector2(0,0), true);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(id);
        renderer = new OrthogonalTiledMapRenderer(map, 1/ GdxGame.PPM);
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        world.setContactListener(new WorldContactListener());

        GdxSoundsManager.getInstance().playMusic("sfx/music/route_104.mp3");
    }

    public void update(float delta, OrthographicCamera camera){
        world.step(1 / 60f, 6, 2);
        renderer.setView(camera);
        camera.update();
    };

    public void render(float delta, OrthographicCamera camera){

        renderer.render();
        b2dr.render(world, camera.combined);
    };

    public String getId() {
        return id;
    }

    public TmxMapLoader getMapLoader() {
        return mapLoader;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }

    public TiledMap getMap() {
        return map;
    }

    public B2WorldCreator getCreator() {
        return creator;
    }

    public World getWorld() {
        return world;
    }

    public List<Pokemon> getCatchablePokemons() {
        return catchablePokemons;
    }

    public void setCatchablePokemons(List<Pokemon> catchablePokemons) {
        this.catchablePokemons = catchablePokemons;
    }
}

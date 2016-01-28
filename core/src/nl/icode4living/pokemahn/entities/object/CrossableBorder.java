package nl.icode4living.pokemahn.entities.object;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.entities.Trainer;
import nl.icode4living.pokemahn.scenery.Scenery;
import nl.icode4living.pokemahn.scenery.SceneryManager;

public class CrossableBorder {

    private World world;
    private TiledMap map;
    private Rectangle bounds;
    private Body body;
    private Scenery scenery;
    private MapObject object;

    private Fixture fixture;

    public CrossableBorder(Scenery scenery, MapObject object) {
        this.object = object;
        this.scenery = scenery;
        this.world = scenery.getWorld();
        this.map = scenery.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / GdxGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / GdxGame.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / GdxGame.PPM, bounds.getHeight() / 2 / GdxGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = GdxGame.CROSS_BORDER_BIT;
        fixture = body.createFixture(fdef);

        fixture.setUserData(this);
    }

    public void crossedBy(Trainer player) {

        String next = (String) object.getProperties().get("nextMap");

        try {
            Class<?> c = Class.forName("nl.icode4living.pokemahn.scenery." + next);
            Constructor<?> cons = c.getConstructor();
            Scenery newScenery = (Scenery) cons.newInstance();
            SceneryManager.getInstance().set(newScenery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        float x =
                Float.valueOf((String) object.getProperties().get("nextMap_X"));
        float y =
                Float.valueOf((String) object.getProperties().get("nextMap_Y"));

        player.recreateAt(x,y);
    }
}

package nl.icode4living.pokemahn.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.entities.object.CrossableBorder;
import nl.icode4living.pokemahn.entities.object.Grass;
import nl.icode4living.pokemahn.scenery.Scenery;

public class B2WorldCreator {

    public static final int BORDER_LAYER = 7;
    public static final int GRASS_LAYER = 8;
    public static final int CROSSABLE_LAYER = 9;

    public B2WorldCreator(Scenery scenery) {
        create(scenery);
    }

    private void create(Scenery scenery) {
        World world = scenery.getWorld();
        TiledMap map = scenery.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(BORDER_LAYER).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GdxGame.PPM, (rect.getY() + rect.getHeight() / 2) / GdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GdxGame.PPM, rect.getHeight() / 2 / GdxGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = GdxGame.BORDER_BIT;
            body.createFixture(fdef);
        }

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(CROSSABLE_LAYER).getObjects().getByType(RectangleMapObject.class)){
            new CrossableBorder(scenery, object);
        }

        //create grass bodies/fixtures
        for(MapObject object : map.getLayers().get(GRASS_LAYER).getObjects().getByType(RectangleMapObject.class)){
            new Grass(scenery, object);
        }
    }

    public void reCreate(Scenery scenery) {
        create(scenery);
    }
}

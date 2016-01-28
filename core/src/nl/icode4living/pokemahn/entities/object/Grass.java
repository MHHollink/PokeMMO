package nl.icode4living.pokemahn.entities.object;

import com.badlogic.gdx.Gdx;
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

import java.util.Random;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.scenery.Scenery;

public class Grass {

    private World world;
    private TiledMap map;
    private Rectangle bounds;
    private Body body;
    private Scenery scenery;
    private MapObject object;

    private Fixture fixture;

    public Random random;
    public static int pokeEncounterPercentage = 0;

    public Grass(Scenery scenery, MapObject object) {

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
        fdef.filter.categoryBits = GdxGame.GRASS_BIT;
        fixture = body.createFixture(fdef);

        fixture.setUserData(this);


        random = new Random();
    }

    public void walkedTrough() {
        // TODO TAKE POKEMONS FROM SCENERY
        pokeEncounterPercentage +=  (random.nextInt(25));




        if(pokeEncounterPercentage >= 100) {
            int encounter = random.nextInt(200)+1;
            String pokemon = "";
            if(encounter <= 60) pokemon = "Zigzagoon";
            else if (encounter <= 120) pokemon = "Wurmple";
            else if (encounter <= 160) pokemon = "Poochyena";
            else if (encounter <= 175) pokemon = "Seedot";
            else if (encounter <= 190) pokemon = "Lotad";
            else if (encounter <= 198) pokemon = "Ralts";
            else if (encounter <= 199) pokemon = "Surskit";
            Gdx.app.log("Grass", String.format("A wild %s appeard", pokemon));

            pokeEncounterPercentage = 0;
        } else
            Gdx.app.log("Grass", String.format("Change of encounter increased to %d", pokeEncounterPercentage));

    }
}

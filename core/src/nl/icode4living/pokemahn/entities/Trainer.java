package nl.icode4living.pokemahn.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.scenery.SceneryManager;
import nl.icode4living.pokemahn.ui.screens.GameScreen;

import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.UP;

public class Trainer extends Sprite {

    private String locationIdentifier;

    public boolean hasMoved = false;

    private enum State {MoveLeft, MoveRight, MoveUp, MoveDown, StandLeft, StandRight, StandUp, StandDown;}
    private State currentState = State.StandDown;
    private State previousState = State.StandDown;

    private Body body;
    private World world;

    private float radius;
    private float speed = 1.06667f;
    private float stateTimer;

    private Animation moveLeft;
    private Animation moveUp;
    private Animation moveRight;
    private Animation moveDown;
    private TextureRegion standingStillUp;
    private TextureRegion standingStillDown;
    private TextureRegion standingStillLeft;
    private TextureRegion standingStillRight;

    public Trainer(GameScreen screen, String locationIdentifier, float x, float y, float radius) {
        this.world = SceneryManager.getInstance().getCurrentSceneWorld();
        this.radius = radius;
        this.locationIdentifier = locationIdentifier;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((x*16) / GdxGame.PPM, (y*16) / GdxGame.PPM);

        shape.setRadius(radius / GdxGame.PPM);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GdxGame.TRAINER_BIT;
        fixtureDef.filter.maskBits =
                        GdxGame.GRASS_BIT |
                        GdxGame.BORDER_BIT |
                        GdxGame.CUT_PLANT_BIT |
                        GdxGame.STRENGTH_ROCK_BIT |
                        GdxGame.SMASH_ROCK_BIT |
                        GdxGame.CROSS_BORDER_BIT;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData(this);

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(screen.getGame().getAtlas().findRegion("walking_down"), i * 25, 0, 25, 25));
        moveDown = new Animation(0.2f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(screen.getGame().getAtlas().findRegion("walking_left"), i * 25, 0, 25, 25));
        moveLeft = new Animation(0.2f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(screen.getGame().getAtlas().findRegion("walking_right"), i * 25, 0, 25, 25));
        moveRight = new Animation(0.2f, frames);
        frames.clear();

        for(int i = 0; i < 4; i++)
            frames.add(new TextureRegion(screen.getGame().getAtlas().findRegion("walking_up"), i * 25, 0, 25, 25));
        moveUp = new Animation(0.2f, frames);
        frames.clear();

        standingStillDown = new TextureRegion(screen.getGame().getAtlas().findRegion("walking_down"), 25, 0, 25, 25);
        standingStillLeft = new TextureRegion(screen.getGame().getAtlas().findRegion("walking_left"), 0, 0, 25, 25);
        standingStillRight = new TextureRegion(screen.getGame().getAtlas().findRegion("walking_right"), 25, 0, 25, 25);
        standingStillUp = new TextureRegion(screen.getGame().getAtlas().findRegion("walking_up"), 25, 0, 25, 25);

        setBounds(body.getPosition().x - getWidth(), body.getPosition().y - getHeight(), 25 / GdxGame.PPM, 25 / GdxGame.PPM);
        setRegion(standingStillDown);
    }

    public void recreateAt(float x, float y) {
        this.world = SceneryManager.getInstance().getCurrentSceneWorld();
        this.locationIdentifier = SceneryManager.getInstance().getCurrentSceneId();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((x*16) / GdxGame.PPM, (y*16) / GdxGame.PPM);

        shape.setRadius(radius / GdxGame.PPM);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GdxGame.TRAINER_BIT;
        fixtureDef.filter.maskBits =
                GdxGame.GRASS_BIT |
                        GdxGame.BORDER_BIT |
                        GdxGame.CUT_PLANT_BIT |
                        GdxGame.STRENGTH_ROCK_BIT |
                        GdxGame.SMASH_ROCK_BIT |
                        GdxGame.CROSS_BORDER_BIT;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).setUserData(this);

        setBounds(body.getPosition().x - getWidth(), body.getPosition().y - getHeight(), 25 / GdxGame.PPM, 25 / GdxGame.PPM);
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        //depending on the currentState, get corresponding animation keyFrame.
        switch (currentState) {
            case MoveLeft:
                region = moveLeft.getKeyFrame(stateTimer, true);
                break;
            case MoveRight:
                region = moveRight.getKeyFrame(stateTimer, true);
                break;
            case MoveUp:
                region = moveUp.getKeyFrame(stateTimer, true);
                break;
            case MoveDown:
                region = moveDown.getKeyFrame(stateTimer, true);
                break;
            case StandLeft:
                region = standingStillLeft;
                break;
            case StandRight:
                region = standingStillRight;
                break;
            case StandUp:
                region = standingStillUp;
                break;
            case StandDown:
                region = standingStillDown;
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        //update previous currentState
        previousState = currentState;

        return region;
    }

    public void update(float delta) {
        setRegion(getFrame(delta));
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 + 0.08f);
    }

    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void move(int direction) {
        switch (direction) {
            case UP:
                body.setLinearVelocity(0,speed);
                currentState = State.MoveUp;
                break;
            case DOWN:
                body.setLinearVelocity(0,-speed);
                currentState = State.MoveDown;
                break;
            case RIGHT:
                body.setLinearVelocity(speed,0);
                currentState = State.MoveRight;
                break;
            case LEFT:
                body.setLinearVelocity(-speed,0);
                currentState = State.MoveLeft;
                break;
        }
        hasMoved = true;
    }

    public void doNotMove() {
        body.setLinearVelocity(0,0);

        switch (currentState) {
            case MoveLeft:
                currentState = State.StandLeft;
                break;
            case MoveRight:
                currentState = State.StandRight;
                break;
            case MoveUp:
                currentState = State.StandUp;
                break;
            case MoveDown:
                currentState = State.StandDown;
                break;
        }
        hasMoved = false;
    }

    public String getLocationIdentifier() {
        return locationIdentifier;
    }

    public void setLocationIdentifier(String locationIdentifier) {
        this.locationIdentifier = locationIdentifier;
    }
}

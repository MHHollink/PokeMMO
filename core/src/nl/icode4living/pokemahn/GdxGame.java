package nl.icode4living.pokemahn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import nl.icode4living.pokemahn.ui.screens.GameScreen;
import nl.icode4living.pokemahn.util.GdxSoundsManager;

public class GdxGame extends Game {

	public static final int V_WIDTH = 800;
    public static final int V_HEIGHT= 416;
	public static final float PPM = 100;

	public SpriteBatch batch;

	//Box2D Collision Bits
	public static final short NOTHING_BIT = 0;
	public static final short BORDER_BIT = 1;
	public static final short TRAINER_BIT = 2;
	public static final short CROSS_BORDER_BIT = 4;
	public static final short GRASS_BIT = 8;
	public static final short WATER_BIT = 8;
	public static final short CUT_PLANT_BIT = 32;
	public static final short SMASH_ROCK_BIT = 64;
	public static final short STRENGTH_ROCK_BIT = 128;

    private TextureAtlas atlas;

	@Override
	public void create () {
		GdxSoundsManager.getInstance().loadAssets();

		atlas = new TextureAtlas("gfx/trainers/male_u/male.pack");

		batch = new SpriteBatch();
		setScreen(new GameScreen(this, 18, 37));
	}


	@Override
	public void render () {
		super.render();
	}

    public TextureAtlas getAtlas() {
        return atlas;
    }
}

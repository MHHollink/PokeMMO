package nl.icode4living.pokemahn.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.ui.screens.GameScreen;

public class CoTrainer extends Sprite {

    private String locationIdentifier;

    public CoTrainer(GameScreen screen, String locationIdentifier, float x, float y) {
        this.locationIdentifier = locationIdentifier;
        setBounds(
                ((x*16) / GdxGame.PPM) - (25/GdxGame.PPM)/2,
                ((y*16) / GdxGame.PPM) - (25/GdxGame.PPM)/2 + 0.08f,
                25 / GdxGame.PPM,
                25 / GdxGame.PPM
        );
        setRegion(new TextureRegion(screen.getGame().getAtlas().findRegion("walking_down"), 25, 0, 25, 25));

    }

    public String getLocationIdentifier() {
        return locationIdentifier;
    }
}

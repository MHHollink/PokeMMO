package nl.icode4living.pokemahn.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import nl.icode4living.pokemahn.GdxGame;
import nl.icode4living.pokemahn.entities.object.CrossableBorder;
import nl.icode4living.pokemahn.entities.object.Grass;
import nl.icode4living.pokemahn.entities.Trainer;

public class WorldContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        int c = a.getFilterData().categoryBits | b.getFilterData().categoryBits;
        switch (c) {
            case GdxGame.TRAINER_BIT | GdxGame.GRASS_BIT:
                if(a.getFilterData().categoryBits == GdxGame.TRAINER_BIT)
                    ((Grass) b.getUserData()).walkedTrough();
                else
                    ((Grass) a.getUserData()).walkedTrough();
                break;

            case GdxGame.TRAINER_BIT | GdxGame.CROSS_BORDER_BIT:
                if(a.getFilterData().categoryBits == GdxGame.TRAINER_BIT)
                    ((CrossableBorder) b.getUserData()).crossedBy((Trainer) a.getUserData());
                else
                    ((CrossableBorder) a.getUserData()).crossedBy((Trainer) b.getUserData());
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Bulbasaur extends Pokemon{

    public Bulbasaur() {
        super("Bulbasaur",
                1,
                Ability.OVERGROWN,
                Type.GRASS,
                Type.POISON,
                2.04f,
                15.2f,
                "BULBASAUR can be seen napping in bright sunlight. " +
                        "There is a seed on its back. By soaking up the sun's rays, " +
                        "the seed grows progressively larger.",
                Rarity.NONE,
                5120,
                45
        );
    }
}

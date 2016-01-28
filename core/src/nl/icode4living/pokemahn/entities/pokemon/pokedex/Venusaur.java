package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Venusaur extends Pokemon {

    public Venusaur() {
        super("Venusaur",
                3,
                Ability.OVERGROWN,
                Type.GRASS,
                Type.POISON,
                6.07f,
                220.5f,
                "VENUSAUR's flower is said to take on vivid colors if it gets plenty " +
                        "of nutrition and sunlight. " +
                        "The flower's aroma soothes the emotions of people. ",
                Rarity.NONE,
                5120,
                45
        );
    }
}

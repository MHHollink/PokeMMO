package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Ivysaur extends Pokemon{

    public Ivysaur() {
        super("Ivysaur",
                2,
                Ability.OVERGROWN,
                Type.GRASS,
                Type.POISON,
                3.03f,
                28.7f,
                "To support its bulb, IVYSAUR's legs grow sturdy. " +
                        "If it spends more time lying in the sunlight, " +
                        "the bud will soon bloom into a large flower.",
                Rarity.NONE,
                5120,
                45
        );
    }
}

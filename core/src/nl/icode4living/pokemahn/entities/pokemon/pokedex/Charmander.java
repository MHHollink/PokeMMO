package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Charmander extends Pokemon {

    public Charmander() {
        super(
              "Charmander",
                4,
                Ability.BLAZE,
                Type.FIRE,
                Type.NONE,
                2.00f,
                18.7f,
                "The flame that burns at the tip of its tail is an indication of its emotions. " +
                        "The flame wavers when CHARMANDER is happy, and blazes when it is enraged.",
                Rarity.NONE,
                5120,
                45
        );
    }
}

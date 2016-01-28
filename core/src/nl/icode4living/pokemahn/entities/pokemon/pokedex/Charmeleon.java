package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Charmeleon extends Pokemon {

    public Charmeleon() {
        super(
                "Charmeleon",
                5,
                Ability.BLAZE,
                Type.FIRE,
                Type.NONE,
                3.07f,
                41.9f,
                " Without pity, its sharp claws destroy foes. If it encounters a strong enemy, " +
                        "it becomes agitated, and the flame on its tail flares with a bluish white color. ",
                Rarity.NONE,
                5120,
                45

        );
    }
}

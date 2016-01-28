package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Wartortle extends Pokemon {

    public Wartortle() {
        super(
                "Wartortle",
                2,
                Ability.TORRENT,
                Type.WATER,
                Type.NONE,
                3.03f,
                49.6f,
                " Its large tail is covered with rich, thick fur that deepens in color with age. " +
                        "The scratches on its shell are evidence of this POKéMON's toughness in battle. ",
                Rarity.NONE,
                5120,
                45

        );
    }
}

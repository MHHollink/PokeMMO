package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super(
                "Squirtle",
                7,
                Ability.TORRENT,
                Type.WATER,
                Type.NONE,
                1.08f,
                19.8f,
                "",
                Rarity.NONE,
                5120,
                45
                );
    }
}

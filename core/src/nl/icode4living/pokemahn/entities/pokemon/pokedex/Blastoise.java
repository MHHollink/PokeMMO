package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Blastoise extends Pokemon {

    public Blastoise(){
        super(
                "Blastoise",
                9,
                Ability.TORRENT,
                Type.WATER,
                Type.NONE,
                5.03f,
                188.5f,
                " The waterspouts that protrude from its shell are highly accurate. " +
                        "Their bullets of water can precisely nail tin cans from a distance of over 160 feet.",
                Rarity.NONE,
                5120,
                45
        );
    }
}

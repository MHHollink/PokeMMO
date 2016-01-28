package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public class Charizard extends Pokemon {
    public Charizard() {
        super(
                "Charizard",
                6,
                Ability.BLAZE,
                Type.FIRE,
                Type.FLYING,
                5.07f,
                199.5f,
                " A CHARIZARD flies about in search of strong opponents. It breathes intense flames that can melt any material. However, it will never torch a weaker foe. ",
                Rarity.NONE,
                5120,
                45
        );
    }
}

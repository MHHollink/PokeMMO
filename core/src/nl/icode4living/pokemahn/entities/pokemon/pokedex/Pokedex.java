package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import java.util.HashSet;
import java.util.Set;

public class Pokedex {

    private Set<Pokemon> pokemons;

    private static Pokedex instance;
    public static Pokedex getInstance() {
        if (instance == null) {
            instance = new Pokedex();
        }
        return instance;
    }

    private Pokedex() {
        pokemons = new HashSet<Pokemon>();

        pokemons.add(new Bulbasaur());
        pokemons.add(new Ivysaur());
        pokemons.add(new Venusaur());

        pokemons.add(new Charmander());
        pokemons.add(new Charmeleon());
        pokemons.add(new Charizard());

        pokemons.add(new Squirtle());
        pokemons.add(new Wartortle());
        pokemons.add(new Blastoise());

        pokemons.add(new Caterpie());
        pokemons.add(new Metapod());
        pokemons.add(new Butterfree());

        pokemons.add(new Weedle());
        pokemons.add(new Kakuna());
        pokemons.add(new Beedrill());

        pokemons.add(new Pidgey());
        pokemons.add(new Pidgeotto());
        pokemons.add(new Pidgeot());

        pokemons.add(new Rattata());
        pokemons.add(new Raticate());

        pokemons.add(new Spearow());
        pokemons.add(new Fearow());

        pokemons.add(new Ekans());
        pokemons.add(new Arbok());

        pokemons.add(new Pikachu());
        pokemons.add(new Raichu());

        pokemons.add(new Sandshrew());
        pokemons.add(new Sandslash());

        pokemons.add(new NidoranF());
        pokemons.add(new Nidorina());
        pokemons.add(new Nidoqueen());

        pokemons.add(new NidoranM());
        pokemons.add(new Nidorino());
        pokemons.add(new Nidoking());

        pokemons.add(new Clefairy());
        pokemons.add(new Clefable());

        pokemons.add(new Vulpix());
        pokemons.add(new Ninetales());

        pokemons.add(new Jigglypuff());
        pokemons.add(new Wigglytuff());

        pokemons.add(new Zubat());
        pokemons.add(new Golbat());

        pokemons.add(new Oddish());
        pokemons.add(new Gloom());
        pokemons.add(new Vileplume());

        pokemons.add(new Paras());
        pokemons.add(new Parasect());

        pokemons.add(new Venonat());
        pokemons.add(new Venomoth());

        pokemons.add(new Diglett());
        pokemons.add(new Dugtrio());

        pokemons.add(new Dugtrio());
        pokemons.add(new Persian());

        pokemons.add(new Psyduck());
        pokemons.add(new Golduck());

        pokemons.add(new Mankey());
        pokemons.add(new Primeape());

        pokemons.add(new Growlithe());
        pokemons.add(new Arcanine());

    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }
}

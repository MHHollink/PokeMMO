package nl.icode4living.pokemahn.entities.pokemon;

public enum Rarity {
    NONE(0),
    VERY_RARE(1.25f),
    RARE(3.33f),
    SEMI_RARE(6.75f),
    COMMON(8.5f),
    VERY_COMMON(10f);

    Rarity(float rarity) {
        this.rarity = rarity;
    }

    private float rarity;

    public float getRarity() {
        return rarity;
    }
    public float getEncounterRate() { return rarity/187.5f; }
}

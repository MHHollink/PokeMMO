package nl.icode4living.pokemahn.entities.pokemon.pokedex;

import java.util.HashMap;
import java.util.Random;

import nl.icode4living.pokemahn.entities.pokemon.Ability;
import nl.icode4living.pokemahn.entities.pokemon.attackdex.Attack;
import nl.icode4living.pokemahn.entities.pokemon.Gender;
import nl.icode4living.pokemahn.entities.pokemon.Rarity;
import nl.icode4living.pokemahn.entities.pokemon.Type;

public abstract class Pokemon {

    protected String name;
    protected int entry;
    protected Gender gender;
    protected Ability ability;
    protected Type priType;
    protected Type secType;
    protected float height;
    protected float weight;
    protected String flavourText;
    protected Rarity rarity;
    protected HashMap<String, Attack> attacks;
    protected int eggSteps;
    protected int catchRate;

    public Pokemon(String name, int entry, Ability ability, Type priType, Type secType, float height, float weight, String flavourText, Rarity rarity, int eggSteps, int catchRate) {
        this.name = name;
        this.entry = entry;
        this.gender = generateGender();
        this.ability = ability;
        this.priType = priType;
        this.secType = secType;
        this.height = height;
        this.weight = weight;
        this.flavourText = flavourText;
        this.rarity = rarity;
        this.attacks = new HashMap<String, Attack>();
        this.eggSteps = eggSteps;
        this.catchRate = catchRate;
    }

    protected Gender generateGender() {
        return new Random().nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEntry() {
        return entry;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Type getPriType() {
        return priType;
    }

    public void setPriType(Type priType) {
        this.priType = priType;
    }

    public Type getSecType() {
        return secType;
    }

    public void setSecType(Type secType) {
        this.secType = secType;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getFlavourText() {
        return flavourText;
    }

    public void setFlavourText(String flavourText) {
        this.flavourText = flavourText;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public HashMap<String, Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(HashMap<String, Attack> attacks) {
        this.attacks = attacks;
    }

    public int getEggSteps() {
        return eggSteps;
    }

    public void setEggSteps(int eggSteps) {
        this.eggSteps = eggSteps;
    }

    public int getCatchRate() {
        return catchRate;
    }

    public void setCatchRate(int catchRate) {
        this.catchRate = catchRate;
    }
}

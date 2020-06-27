package com.geraskin.poketest;

import java.util.List;

public class PokemonJson {

    private String name;
    private int id;
    private int weight;
    private int height;
    private Sprites sprites;
    private List<Types> types;
    private List<Stats> stats;

    public PokemonJson() {
    }

    class Types{
        private Type type;

        Type getType() {
            return type;
        }
    }

    class Type{
        private String name;

        String getName() {
            return name;
        }
    }

    class Stats{
        private int base_stat;

        int getBase_stat() {
            return base_stat;
        }
    }

    class Sprites{
        private String front_default;

        String getFront_default() {
            return front_default;
        }
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    int getWeight() {
        return weight;
    }

    int getHeight() {
        return height;
    }

    Sprites getSprites() {
        return sprites;
    }

    List<Types> getTypes() {
        return types;
    }

    List<Stats> getStats() {
        return stats;
    }
}

package com.example.poketest;

class Pokemon{
    private String  name;
    private int     id;
    private int     attack;
    private int     defence;
    private int     hp;
    private int     height;
    private int     weight;
    private String  type;
    private String  sprite;

    Pokemon(String name,
                   int id,
                   int attack,
                   int defence,
                   int hp,
                   int height,
                   int weight,
                   String type,
                   String sprite){

        this.name    = name;
        this.id      = id;
        this.attack  = attack;
        this.defence = defence;
        this.hp      = hp;
        this.height  = height;
        this.weight  = weight;
        this.type    = type;
        this.sprite  = sprite;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    int getAttack() {
        return attack;
    }

    int getDefence() {
        return defence;
    }

    int getHp() {
        return hp;
    }

    int getHeight() {
        return height;
    }

    int getWeight() {
        return weight;
    }

    String getType() {
        return type;
    }

    String getSprite() {
        return sprite;
    }
}
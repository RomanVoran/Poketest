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

    public Pokemon(String name,
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
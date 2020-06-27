package com.geraskin.poketest;

import retrofit2.Call;
import retrofit2.Callback;

public class PokeConnection extends Thread{
    private int index;
    private Callback<PokemonJson> jsonCallback;
    private PokeApi pokeApi;

    PokeConnection(int index, Callback<PokemonJson> jsonCallback,PokeApi pokeApi){
        this.pokeApi = pokeApi;
        this.index = index;
        this.jsonCallback = jsonCallback;
    }

    @Override
    public void run() {
        super.run();
        Call<PokemonJson> pokemonJson = pokeApi.getPokemon(index);
        pokemonJson.enqueue(jsonCallback);
    }
}

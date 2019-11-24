package com.example.poketest;

import retrofit2.Call;
import retrofit2.Callback;

public class PokeConnection extends Thread {
    private int ind;
    private Callback<PokemonJson> callback;
    private PokeApi pokeApi;

    public PokeConnection(int ind,Callback<PokemonJson> callback,PokeApi pokeApi){
        this.callback = callback;
        this.ind = ind;
        this.pokeApi = pokeApi;
    }


    @Override
    public void run(){
        Call<PokemonJson> pokemon = pokeApi.getPokemon(ind);
        pokemon.enqueue(callback);
    }
}

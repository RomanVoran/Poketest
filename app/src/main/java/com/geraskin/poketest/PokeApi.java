package com.geraskin.poketest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface PokeApi {
    @GET("pokemon/{id}")
    Call<PokemonJson> getPokemon(@Path("id")int id);
}

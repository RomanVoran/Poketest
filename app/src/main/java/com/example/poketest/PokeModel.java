package com.example.poketest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeModel {

    private int counter;
    private int count = 10;
    private List<PokemonJson> pokemonJsonList = new ArrayList<>();

    public void loadPokeList(int from){
        String pokeApiURL = "https://pokeapi.co/api/v2/";

        Callback<PokemonJson> callback = new Callback<PokemonJson>() {
            @Override
            public void onResponse(Call<PokemonJson> call, Response<PokemonJson> response) {
                addToLocalList(response.body());
            }

            @Override
            public void onFailure(Call<PokemonJson> call, Throwable t) {

            }
        };

        System.out.println(R.string.addType);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(pokeApiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApi pokeApi = retrofit.create(PokeApi.class);

        counter = 0;
        for (int i = from;i<=from+count;i++){
            new PokeConnection(i,callback,pokeApi).start();
        }
    }

    private void addToLocalList(PokemonJson pokemonJson){
        counter++;
        pokemonJsonList.add(pokemonJson);
        if (counter == count){
            counter = 0;
            // перенести в презентор и добавить вызов метода представления
            // view.setItems(pokemonJsonList);
            pokemonJsonList.clear();
        }
    }


}

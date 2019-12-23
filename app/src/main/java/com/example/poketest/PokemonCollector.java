package com.example.poketest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonCollector extends Thread {

    private int indFrom;
    private int pokePackSize;
    private static volatile boolean mReadyToLoad;
    private static volatile boolean mIsLastPokemon;
    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private PokeModel.LoadCompleteCallback completeCallback;

    PokemonCollector(int indFrom, int pokePackSize, PokeModel.LoadCompleteCallback completeCallback){
        this.indFrom = indFrom;
        this.completeCallback = completeCallback;
        this.pokePackSize = pokePackSize;
    }

    @Override
    public void run() {
        super.run();
        Callback<PokemonJson> pokemonJsonCallback = new Callback<PokemonJson>() {
            @Override
            public void onResponse(Call<PokemonJson> call, Response<PokemonJson> response) {
                mReadyToLoad = true;
                PokeList.addElement(new Pokemon(response.body().getName(),
                        response.body().getId(),
                        response.body().getStats().get(4).getBase_stat(),
                        response.body().getStats().get(3).getBase_stat(),
                        response.body().getStats().get(5).getBase_stat(),
                        response.body().getHeight(),
                        response.body().getWeight(),
                        response.body().getTypes().get(0).getType().getName(),
                        response.body().getSprites().getFront_default()));
                if (mIsLastPokemon){
                    mIsLastPokemon = false;
                    completeCallback.LoadComplete();
                    // загружен и добавлен последний покемон (далее отрисовка recycler view)
                }

            }

            @Override
            public void onFailure(Call<PokemonJson> call, Throwable t) {

            }
        };

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApi pokeApi = retrofit.create(PokeApi.class);

        mIsLastPokemon = false;
        mReadyToLoad = true;
        int nPoke = indFrom;
        while (nPoke<=(indFrom+pokePackSize-1)){
            if (mReadyToLoad){
                if (nPoke == (indFrom+pokePackSize-1)){
                    mIsLastPokemon = true;
                }
                mReadyToLoad = false;
                new PokeConnection(nPoke,pokemonJsonCallback,pokeApi).start();
                nPoke++;
            }
        }

    }
}

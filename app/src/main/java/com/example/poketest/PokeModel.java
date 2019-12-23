package com.example.poketest;


public class PokeModel {


    public void loadPokePack(int indFrom, int count,LoadCompleteCallback callback){
        new PokemonCollector(indFrom,count,callback).start();
    }

    interface LoadCompleteCallback{
        void LoadComplete();
    }

}

package com.geraskin.poketest;


class PokeModel {


    void loadPokePack(int indFrom, int count,LoadCompleteCallback callback){
        new PokemonCollector(indFrom,count,callback).start();
    }

    interface LoadCompleteCallback{
        void loadComplete(boolean loadingDone);
    }

}

package com.example.poketest;

import android.view.View;

public class PokePresenter {

    private MainActivity view;
    private PokeModel model;

    public PokePresenter(PokeModel model){
        this.model = model;
    }

    public void attachView(MainActivity mainActivity){
        this.view = mainActivity;
    }

    public void detachView(){
        this.view = null;
    }

    public void viewIsReady(){
        loadPokes();
    }

    public void loadPokes(){

    }
}

package com.example.poketest;


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

    public void refresh(){

    }

    public void viewIsReady(){
        loadPokes();
    }

    public void loadPokes(){

    }
}

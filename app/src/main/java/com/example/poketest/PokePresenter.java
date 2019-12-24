package com.example.poketest;

import static java.lang.Math.round;
import static java.lang.Math.random;

class PokePresenter {

    private MainActivity view;
    private PokeModel model;
    private int countOfPokes = 10;

    PokePresenter(PokeModel model){
        this.model = model;
    }

    void attachView(MainActivity mainActivity){
        this.view = mainActivity;
    }

    void detachView(){
        this.view = null;
    }


    void viewIsReady(){
        loadPokes(1,countOfPokes);
    }

    void sortPokes(boolean att, boolean def, boolean hp){
        PokeList.findMax(att,def,hp);
        view.addPokes();
    }

    void loadRandomPokes(){
        PokeList.clearList();
        int indFrom = (int) round(random()*500);
        loadPokes(indFrom, countOfPokes);

    }

    void loadNextPokes(){
        if (PokeList.getLastIndex()+countOfPokes>=570){
            //условие если покемонов в базе больше нет (индекс запрашиваемых покемонов больше индекса доступных в базе)
            return;
        }
        loadPokes(PokeList.getLastIndex()+1, countOfPokes);

    }

    private void loadPokes(int indFrom, int countOfPokes){
        model.loadPokePack(indFrom, countOfPokes, new PokeModel.LoadCompleteCallback() {
            @Override
            public void loadComplete() {
                view.addPokes();
            }
        });
    }
}

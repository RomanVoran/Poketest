package com.example.poketest;


import static java.lang.Math.round;
import static java.lang.Math.random;

class PokePresenter {

    private MainActivity view;
    private PokeModel model;
    private int countOfPokes = 10;
    private boolean mReadyToLoad = true;

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
        if (!mReadyToLoad) return;
        if (PokeList.size()<1){
            loadPokes(1,countOfPokes);
            return;
        }
        view.addPokes();
    }

    void sortPokes(boolean att, boolean def, boolean hp){
        if (PokeList.size()<1){
            view.showEmptyPokeListToast();
            return;
        }
        PokeList.findMax(att,def,hp);
        view.addPokes();
    }

    void loadRandomPokes(){
        if (!mReadyToLoad) return;
        PokeList.clearList();
        int indFrom = (int) round(random()*500);
        loadPokes(indFrom, countOfPokes);

    }

    void loadNextPokes(){

        if (!mReadyToLoad) return;
        if (PokeList.getLastIndex()+countOfPokes>=570){
            //условие если покемонов в базе больше нет (индекс запрашиваемых покемонов больше индекса доступных в базе)
            return;
        }
        loadPokes(PokeList.getLastIndex()+1, countOfPokes);

    }

    private void loadPokes(int indFrom, int countOfPokes){
        mReadyToLoad = false;
        view.showLoadToast();

        model.loadPokePack(indFrom, countOfPokes, new PokeModel.LoadCompleteCallback() {
            @Override
            public void loadComplete(boolean loadingDone) {
                if (loadingDone){
                    mReadyToLoad = true;
                    view.addPokes();
                }else{
                    mReadyToLoad = true;
                    view.showConnectFailureToast();
                }
            }
        });
    }
}

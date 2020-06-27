package com.geraskin.poketest;

import java.util.ArrayList;
import java.util.Collections;

class PokeList {
    private static ArrayList<Pokemon> pokeList = new ArrayList<>();

    //добавление элементов в список PokeList
    static void addElement(Pokemon poke){
        PokeList.pokeList.add(poke);
    }

    // возвращает список PokeList
    static ArrayList<Pokemon> getPokeList(){
        return PokeList.pokeList;
    }

    // возвращает количество элементов списка PokeList
    static int size(){
        return PokeList.pokeList.size();
    }

    // удаление всех покемонов из списка PokeList
    static void clearList(){
        while (PokeList.size()>0) {
            PokeList.pokeList.remove(0);
        }
    }

    // удаление первых "countOfClear" покемонов из списка PokeList
    static void clearPartList(int countOfClear){
        while ((PokeList.size()-countOfClear)>0) {
            PokeList.pokeList.remove(0);
        }
    }

    // перестановка элементов списка PokeList
    private static void pokeSwap(int ind1, int ind2){
        Collections.swap(PokeList.pokeList,ind1,ind2);
    }

    // возвращает индекс последнего в списке покемона
    static int getLastIndex(){
        if (PokeList.pokeList.size()==0){
            return 0;
        }else{
            return PokeList.pokeList.get(PokeList.size()-1).getId();
        }
    }

    // поиск покемона с максимальным заданным параметром (hp attack defence)
    // и перестановка его на первую позицию в списке
    static void findMax(boolean att, boolean def, boolean hp){
        // преобразование boolean значения checkbox в int значения для сравнения
        int hpVal = hp?1:0;
        int attVal = att?1:0;
        int defVal = def?1:0;

        int maxParam = 0;
        int idOfMaxPoke = 0;
        int i = 0;
        for(Pokemon p:pokeList){
            if ((p.getAttack()*attVal +
                    p.getDefence()*defVal +
                    p.getHp()*hpVal) > maxParam){
                maxParam = p.getAttack()*attVal + p.getDefence()*defVal + p.getHp()*hpVal;
                idOfMaxPoke = i;
            }
            i++;
        }
        PokeList.pokeSwap(0,idOfMaxPoke);
    }

}

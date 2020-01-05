package com.example.poketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private PokeAdapter pokeAdapter;
    private PokePresenter pokePresenter;
    private CheckBox checkBoxHP;
    private CheckBox checkBoxAtt;
    private CheckBox checkBoxDef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        // обновление RecyclerView в случае еслизагрузка покемонов завершилась во время работы с другим экраном
        super.onResume();
        pokeAdapter.refreshRecyclerView();
    }



    private void init(){

        checkBoxDef = findViewById(R.id.checkBox_defence);
        checkBoxHP = findViewById(R.id.checkBox_healt);
        checkBoxAtt = findViewById(R.id.checkBox_attack);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        pokeAdapter = new PokeAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recycleView_poke);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokeAdapter);

        findViewById(R.id.button_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokePresenter.loadRandomPokes();
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)){
                    // условие невозможности прокрутки списка вниз
                    pokePresenter.loadNextPokes();
                }
            }
        });


        PokeModel model = new PokeModel();
        pokePresenter = new PokePresenter(model);
        pokePresenter.attachView(this);
        pokePresenter.viewIsReady();

    }

    public void addPokes(){
        pokeAdapter.refreshRecyclerView();
    }

    public void onClickCheckBox(View view){
        // слушатель нажатия на один из ЧекБоксов
        pokePresenter.sortPokes(checkBoxAtt.isChecked(),checkBoxDef.isChecked(),checkBoxHP.isChecked());
    }

    public void showLoadToast(){
        Toast.makeText(this,"Load...",Toast.LENGTH_SHORT).show();
    }

    public void showConnectFailureToast(){
        Toast.makeText(this,"Connection failure",Toast.LENGTH_LONG).show();
    }

    public void showEmptyPokeListToast(){
        Toast.makeText(this,"No loaded pokemon",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // отвязка вьюшки активности от презентора (избежание утечек памяти)
        pokePresenter.detachView();
    }
}

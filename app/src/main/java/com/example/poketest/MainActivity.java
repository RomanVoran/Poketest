package com.example.poketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    private void init(){

        checkBoxDef = findViewById(R.id.checkBox_defence);
        checkBoxHP = findViewById(R.id.checkBox_healt);
        checkBoxAtt = findViewById(R.id.checkBox_attack);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        pokeAdapter = new PokeAdapter();

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
        Toast.makeText(this,"Load",Toast.LENGTH_SHORT);
    }

    public void showCompleteToast(){
        Toast.makeText(this,"Complete",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // отвязка вьюшки активности от презентора (избежание утечек памяти)
        pokePresenter.detachView();
    }
}

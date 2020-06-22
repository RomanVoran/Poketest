package com.example.poketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private PokeAdapter pokeAdapter;
    private PokePresenter pokePresenter;

    private ImageButton attImageButton;
    private boolean attCheck;
    private ImageButton defImageButton;
    private boolean defCheck;
    private ImageButton hpImageButton;
    private boolean hpCheck;
    private ProgressBar progBar;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        attImageButton = findViewById(R.id.ATT_imb);
        attCheck = false;
        defImageButton = findViewById(R.id.DEF_imb);
        defCheck = false;
        hpImageButton = findViewById(R.id.HP_imb);
        hpCheck = false;

        progBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        pokeAdapter = new PokeAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recycleView_poke);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokeAdapter);

        // нажатие на плавающую кнопку
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.aboutItem){
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void addPokes(){
        pokeAdapter.refreshRecyclerView();
    }

    public void showConnectFailureToast(){
        Toast.makeText(this,"Connection failure",Toast.LENGTH_LONG).show();
    }

    public void showEmptyPokeListToast(){
        Toast.makeText(this,"No loaded pokemon",Toast.LENGTH_SHORT).show();
    }

    public void showProgresBar(){
        progBar.setVisibility(View.VISIBLE);
    }

    public void hideProgresBar(){
        progBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // отвязка вьюшки активности от презентора (избежание утечек памяти)
        pokePresenter.detachView();
    }

    // функция нажатия на ImageButtonы
    public void onStatClick(View view) {
        switch (view.getId()) {
            case (R.id.ATT_imb):
                if (!attCheck)
                    attImageButton.setImageResource(R.drawable.sword_fill);
                else
                    attImageButton.setImageResource(R.drawable.sword);
                attCheck = !attCheck;
                break;
            case (R.id.DEF_imb):
                if (!defCheck)
                    defImageButton.setImageResource(R.drawable.shield_fill);
                else
                    defImageButton.setImageResource(R.drawable.shield);
                defCheck = !defCheck;
                break;
            case (R.id.HP_imb):
                if (!hpCheck)
                    hpImageButton.setImageResource(R.drawable.heart_fill);
                else
                    hpImageButton.setImageResource(R.drawable.heart);
                hpCheck = !hpCheck;
                break;
        }
        pokePresenter.sortPokes(attCheck,defCheck,hpCheck);
    }
}

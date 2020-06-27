package com.geraskin.poketest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PokeStatActivity extends AppCompatActivity {

    ImageView imageViewPoke;
    TextView  textViewHP;
    TextView  textViewAttack;
    TextView  textViewDefence;
    TextView  textViewWeight;
    TextView  textViewHeight;
    TextView  textViewName;
    TextView  textViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_stat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //показать стрелку
        getSupportActionBar().setDisplayShowHomeEnabled(true); //привязать слушателя на нажатие стрелки

        imageViewPoke = findViewById(R.id.imageView_poke);
        textViewAttack = findViewById(R.id.textView_attack);
        textViewHP = findViewById(R.id.textView_hp);
        textViewDefence = findViewById(R.id.textView_defence);
        textViewWeight = findViewById(R.id.textView_weight);
        textViewHeight = findViewById(R.id.textView_height);
        textViewName = findViewById(R.id.textView_name);
        textViewType = findViewById(R.id.textView_type);

        Intent startedIntent = getIntent();

        if ((startedIntent.hasExtra("pokeName"))&&
                startedIntent.hasExtra("pokeType")&&
                startedIntent.hasExtra("pokeAttack")&&
                startedIntent.hasExtra("pokeDefence")&&
                startedIntent.hasExtra("pokeHP")&&
                startedIntent.hasExtra("pokeHeight")&&
                startedIntent.hasExtra("pokeWeight")&&
                startedIntent.hasExtra("pokeSprite"))
        {
            String name =  startedIntent.getStringExtra("pokeName");
            String type =  startedIntent.getStringExtra("pokeType");
            String height = "" + startedIntent.getIntExtra("pokeHeight",0);
            String  weight = "" + startedIntent.getIntExtra("pokeWeight",0);
            String attack = "" + startedIntent.getIntExtra("pokeAttack",0);
            String defence = "" + startedIntent.getIntExtra("pokeDefence",0);
            String hp = "" + startedIntent.getIntExtra("pokeHP",0);
            String sprite = startedIntent.getStringExtra("pokeSprite");

            textViewName.setText(name);
            textViewType.setText(type);
            textViewDefence.setText(defence);
            textViewHP.setText(hp);
            textViewAttack.setText(attack);
            textViewHeight.setText(height);
            textViewWeight.setText(weight);

            Picasso.get().load(sprite).into(imageViewPoke);
            setTitle(startedIntent.getStringExtra("pokeName"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

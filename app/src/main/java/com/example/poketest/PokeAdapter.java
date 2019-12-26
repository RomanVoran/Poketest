package com.example.poketest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {

    private Context parent;

    PokeAdapter(Context parent){
        this.parent = parent;
    }

    private List<Pokemon> adapterPokeList = new ArrayList<>();

    void refreshRecyclerView(){
        adapterPokeList.clear();
        adapterPokeList.addAll(PokeList.getPokeList());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv,parent,false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {
        holder.bind(adapterPokeList.get(position));
    }


    @Override
    public int getItemCount() {
        return adapterPokeList.size();
    }

    class PokeViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        PokeViewHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.imag_poke);
            textView = view.findViewById(R.id.tv_poke_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // передача параметров выбранного покемона в новую активность
                    int indexOfPokeClicked = getAdapterPosition();
                    Class destinationActivity = PokeStatActivity.class;
                    Intent pokeStatIntent = new Intent(parent, destinationActivity);
                    pokeStatIntent.putExtra("pokeName", adapterPokeList.get(indexOfPokeClicked).getName());
                    pokeStatIntent.putExtra("pokeType", adapterPokeList.get(indexOfPokeClicked).getType());
                    pokeStatIntent.putExtra("pokeAttack", adapterPokeList.get(indexOfPokeClicked).getAttack());
                    pokeStatIntent.putExtra("pokeDefence", adapterPokeList.get(indexOfPokeClicked).getDefence());
                    pokeStatIntent.putExtra("pokeHP", adapterPokeList.get(indexOfPokeClicked).getHp());
                    pokeStatIntent.putExtra("pokeHeight", adapterPokeList.get(indexOfPokeClicked).getHeight());
                    pokeStatIntent.putExtra("pokeWeight", adapterPokeList.get(indexOfPokeClicked).getWeight());
                    pokeStatIntent.putExtra("pokeSprite", adapterPokeList.get(indexOfPokeClicked).getSprite());
                    parent.startActivity(pokeStatIntent);
                }
            });
        }



        void bind(Pokemon pokemon){
            textView.setText(pokemon.getName());
            Picasso.get().load(pokemon.getSprite()).into(imageView);
        }
    }
}

package com.example.poketest;

import android.util.Log;
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
        holder.bind(adapterPokeList.get(position),position);
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

            // слушатель наатия на кнопку
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MYLOGS","Tap on list item #");
                }
            });
        }



        void bind(Pokemon pokemon,int position){
            textView.setText("" + pokemon.getName() + " #" + position);
            Picasso.get().load(pokemon.getSprite()).into(imageView);
        }
    }
}

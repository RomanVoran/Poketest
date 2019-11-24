package com.example.poketest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {

    private List<Pokemon> pokeList = new ArrayList<>();

    // добавление в список новых элементов и его обновление
    public void setItems(Collection<Pokemon> pokes){
        pokeList.addAll(pokes);
        notifyDataSetChanged();
    }

    // удаление (очистка списка)
    public void clearItems(){
        pokeList.clear();
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
        holder.bind(pokeList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }

    class PokeViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public PokeViewHolder(@NonNull View view){
            super(view);
            imageView = view.findViewById(R.id.imag_poke);
            textView = view.findViewById(R.id.tv_poke_name);

            // слушатель наатия на кнопку
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }


        //
        void bind(Pokemon pokemon){
            textView.setText(pokemon.getName());
        }
    }
}

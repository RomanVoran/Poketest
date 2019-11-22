package com.example.poketest;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {


    @NonNull
    @Override
    public PokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PokeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PokeViewHolder extends RecyclerView.ViewHolder{

        public PokeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.example.cs50first;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    public static class PokedexViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout containerView;
        public TextView textView;

        PokedexViewHolder(View view){
            super(view);
            containerView = view.findViewById(R.id.pokedex_row_layout);
            textView = view.findViewById(R.id.pokedex_row_text_view);
        }
    }
    private List<Pokemon> pokemonList = Arrays.asList(
            new Pokemon("fist Pokemon", 1),
            new Pokemon("second poki", 2),
            new Pokemon("third pok", 3)
    );
    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokedex_row, parent, false);
        return new PokedexViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon currentPokemon = pokemonList.get(position);
        holder.textView.setText(currentPokemon.getName());
    }
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}

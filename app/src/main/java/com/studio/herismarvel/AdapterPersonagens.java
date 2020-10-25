package com.studio.herismarvel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.studio.herismarvel.Personagem;

import java.util.ArrayList;

public class AdapterPersonagens extends RecyclerView.Adapter<AdapterPersonagens.ViewHolder> {

    ArrayList<Personagem> personagens;

    public AdapterPersonagens( ArrayList<Personagem> personagem) {
        this.personagens = personagem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_model, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Personagem modelo = personagens.get(position);
        holder.personagem.setText(modelo.getPersonagem());
        holder.imagem.setImageResource(modelo.getNumImagem());
        holder.nome.setText(modelo.getName());

    }

    @Override
    public int getItemCount() {
        return personagens.size();    }


    //liga viewholder no cardview
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagem;
        TextView personagem;
        TextView nome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.image_card);
            personagem = itemView.findViewById(R.id.tv_card);
            nome = itemView.findViewById(R.id.tv_card2);

        }
    }

}

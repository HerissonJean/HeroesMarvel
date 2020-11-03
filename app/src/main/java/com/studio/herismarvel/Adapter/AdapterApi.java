package com.studio.herismarvel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.studio.herismarvel.Model.Personagem;
import com.studio.herismarvel.R;

import java.util.List;

public class AdapterApi extends RecyclerView.Adapter<AdapterApi.MyViewHolder> {

    private Context context;
    private List<Personagem> personagensApi;


    public AdapterApi(Context c, List<Personagem> personagensApi) {
        this.context = c;
        this.personagensApi = personagensApi;
    }

    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.cardview_model_api, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nome.setText(personagensApi.get(position).getName());
        String url =  personagensApi.get(position).getPath();
        Glide.with(context).load(url).into(holder.imagemPersonagem);

    }

    @Override
    public int getItemCount() {
        return personagensApi.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        ImageView imagemPersonagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.text_api_card);
            imagemPersonagem = itemView.findViewById(R.id.image_api_card);
        }
    }
}

package com.studio.herismarvel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.herismarvel.Personagem;
import com.studio.herismarvel.R;

public class Apresentacao extends AppCompatActivity {

    private TextView nome , personagemNome;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apresentacao2);
   //     ActionBar actionBar = getSupportActionBar();
    //    actionBar.hide();

//        findViewById(R.id.ic_apresentacao_retur).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        nome = findViewById(R.id.text_apresentacao_nome);
        personagemNome = findViewById(R.id.text_apresentacao_personagem);
        imageView = findViewById(R.id.image_apresentacao_principal);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        Personagem personagem = (Personagem) b.getSerializable("teste");
        nome.setText(personagem.getName());
        imageView.setImageResource(personagem.getNumImagem());
        personagemNome.setText(personagem.getPersonagem());

    }
}
package com.studio.herismarvel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.studio.herismarvel.Adapter.AdapterApi;
import com.studio.herismarvel.Adapter.AdapterPersonagens;
import com.studio.herismarvel.Useful.MarvelService;
import com.studio.herismarvel.Model.Personagem;
import com.studio.herismarvel.R;
import com.studio.herismarvel.Useful.RecyclerItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerHeros, recyclerVilao, recyclerAnti, recyclerAlienigena, recyclerHumano, recyclerHide, recyclerOutros;
    ArrayList<Personagem> personagensLista = new ArrayList<>();
    ArrayList<Personagem> personagensLista2 = new ArrayList<>();
    ArrayList<Personagem> personagensLista3 = new ArrayList<>();
    ArrayList<Personagem> personagensLista4 = new ArrayList<>();
    ArrayList<Personagem> personagensLista5 = new ArrayList<>();
    ArrayList<Personagem> personagensHidden = new ArrayList<>();
    ArrayList<Personagem> personagensApi = new ArrayList<>();
    private ImageView bt_hero, bt_villain, bt_antihero, bt_alienigena, bt_humano, bt_clearRecycler, bt_magic;
    private Retrofit retrofit;
    public ImageView imageView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configuração ActionBar
        //   ActionBar bar = getSupportActionBar();

        // bar.hide();

        recuperaDadosApi();

        bt_hero = findViewById(R.id.image_main_icHero);
        bt_villain = findViewById(R.id.image_main_icVilao);
        bt_antihero = findViewById(R.id.image_main_icAntiHero);
        bt_alienigena = findViewById(R.id.ic_main_icAlienigena);
        bt_humano = findViewById(R.id.image_main_icHumano);
        bt_clearRecycler = findViewById(R.id.image_main_clear);
        bt_clearRecycler = findViewById(R.id.image_main_clear);
        bt_magic = findViewById(R.id.image_main_magic);

        bt_clearRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerHide.setVisibility(View.GONE);
                bt_clearRecycler.setVisibility(View.GONE);
            }
        });

        bt_hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(1);
            }
        });

        bt_villain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(2);
            }
        });

        bt_antihero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(3);
            }
        });
        bt_alienigena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(4);
            }
        });
        bt_humano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(5);
            }
        });
        bt_magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriaEscolhida(6);
            }
        });

        //apontando no xml rec/java
        recyclerHeros = findViewById(R.id.recycler_main_heroi);
        recyclerVilao = findViewById(R.id.recycler_main_vilao);
        recyclerAnti = findViewById(R.id.recycler_main_anti);
        recyclerAlienigena = findViewById(R.id.recycler_main_alienigena);
        recyclerHumano = findViewById(R.id.recycler_main_Humano);
        recyclerHide = findViewById(R.id.recycler_main_hide);

        Personagem aux = new Personagem();
        personagensLista = aux.classificacaoRecycler(1);
        personagensLista2 = aux.classificacaoRecycler(2);
        personagensLista3 = aux.classificacaoRecycler(3);
        personagensLista4 = aux.classificacaoRecycler(4);
        personagensLista5 = aux.classificacaoRecycler(5);

        criaRecyclerApi();
        criaRecyclerHeros();
        criaRecyclerVilao();
        criaRecyclerAnti();
        criaRecyclerAlienigena();
        criaRecyclerHumano();

    }


    private void recuperaDadosApi() {

        retrofit = new Retrofit.Builder().
                baseUrl("https://gateway.marvel.com/").addConverterFactory(GsonConverterFactory.create()).build();

        MarvelService marvelService = retrofit.create(MarvelService.class);
        Call<JsonElement> call = marvelService.recuperaChar();

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {

                    JsonElement data = response.body();
                    JsonObject json = data.getAsJsonObject();
                    JsonObject code = json.get("data").getAsJsonObject();
                    JsonArray arrayJson = code.get("results").getAsJsonArray();

                    for (int i = 0; i < arrayJson.size(); i++) {

                        JsonObject jsonObject = (JsonObject) arrayJson.get(i);

                        JsonObject thumbnail = (JsonObject) jsonObject.get("thumbnail");
                        String path = String.valueOf(thumbnail.get("path").getAsString());
                        String extension = String.valueOf(thumbnail.get("extension").getAsString());
                        String name = String.valueOf(jsonObject.get("name"));
                        // Log.d("harry "+i, "onResponse: "+path+"."+extension +" - " + name );

                        Personagem personagemAux = new Personagem();
                        personagemAux.setName(name);
                        String pathAux = path + "." + extension;
                        personagemAux.setPath(pathAux);
                        personagensApi.add(personagemAux);

                    }

                    //  imageView = findViewById(R.id.apii);
                    //       textView.setText(personagensApi.get(7).getName());
                    //       Log.d("teste: ", "" + personagensApi.get(7).getName());
                    //      String url = personagensApi.get(10).getPath() + "." + personagensApi.get(0).getExtension();
                    ////        Glide.with(MainActivity.this).load(url).into(imageView);
                    //      Log.d("onResponse: ", " l " + url);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (t == null) {
                    t.getCause();
                }
            }
        });

    }

    private void criaRecyclerApi() {

//        RecyclerView recyclerView = new RecyclerView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerHeros.setLayoutManager(layoutManager);
        recyclerHeros.setItemAnimator(new DefaultItemAnimator());
        AdapterApi adapterApi = new AdapterApi(MainActivity.this, personagensApi);

        recyclerHeros.setAdapter(adapterApi);

    }

    private void criaRecyclerHeros() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerHeros.setLayoutManager(layoutManager);
        recyclerHeros.setItemAnimator(new DefaultItemAnimator());
        AdapterPersonagens adapter = new AdapterPersonagens(personagensLista);
        recyclerHeros.setAdapter(adapter);

        recyclerHeros.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerHeros,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                i.putExtra("teste", (Serializable) personagensLista.get(position));
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            }
                        }
                )
        );
    }

    private void criaRecyclerVilao() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerVilao.setLayoutManager(layoutManager);
        recyclerVilao.setItemAnimator(new DefaultItemAnimator());
        AdapterPersonagens adapterVilao = new AdapterPersonagens(personagensLista2);
        recyclerVilao.setAdapter(adapterVilao);

        recyclerVilao.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerVilao,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                i.putExtra("teste", (Serializable) personagensLista2.get(position));
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }

                )
        );
    }

    private void criaRecyclerAnti() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerAnti.setLayoutManager(layoutManager);
        recyclerAnti.setItemAnimator(new DefaultItemAnimator());
        AdapterPersonagens adapterAnti = new AdapterPersonagens(personagensLista3);
        recyclerAnti.setAdapter(adapterAnti);

        recyclerAnti.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerAnti,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                i.putExtra("teste", (Serializable) personagensLista3.get(position));
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }

                )
        );

    }

    private void criaRecyclerAlienigena() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerAlienigena.setLayoutManager(layoutManager);
        recyclerAlienigena.setItemAnimator(new DefaultItemAnimator());
        AdapterPersonagens adapterAlienigena = new AdapterPersonagens(personagensLista4);
        recyclerAlienigena.setAdapter(adapterAlienigena);

        recyclerAlienigena.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerAlienigena,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                i.putExtra("teste", (Serializable) personagensLista4.get(position));
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }

                )
        );

    }

    private void criaRecyclerHumano() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerHumano.setLayoutManager(layoutManager);
        recyclerHumano.setItemAnimator(new DefaultItemAnimator());
        AdapterPersonagens adapterHumano = new AdapterPersonagens(personagensLista5);
        recyclerHumano.setAdapter(adapterHumano);

        recyclerHumano.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerHumano,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                i.putExtra("teste", (Serializable) personagensLista5.get(position));
                                startActivity(i);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }


    private void categoriaEscolhida(int tipo) {

        recyclerHide.setVisibility(View.VISIBLE);
        bt_clearRecycler.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerHide.setLayoutManager(layoutManager);
        recyclerHide.setItemAnimator(new DefaultItemAnimator());
        switch (tipo) {
            case 1:
                personagensHidden = personagensLista;
                AdapterPersonagens adapterHero = new AdapterPersonagens( personagensHidden);
                recyclerHide.setAdapter(adapterHero);

                recyclerHide.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerHide,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    }
                                }));

                break;
            case 2:
                personagensHidden = personagensLista2;
                AdapterPersonagens adapterHero2 = new AdapterPersonagens(personagensHidden);
                recyclerHide.setAdapter(adapterHero2);

                recyclerHide.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerHide,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    }
                                }));
                break;
            case 3:
                personagensHidden = personagensLista3;
                AdapterPersonagens adapterHero3 = new AdapterPersonagens(personagensHidden);
                recyclerHide.setAdapter(adapterHero3);

                recyclerHide.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerHide,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    }
                                }));
                break;
            case 4:
                personagensHidden = personagensLista4;
                AdapterPersonagens adapterHero4 = new AdapterPersonagens(personagensHidden);
                recyclerHide.setAdapter(adapterHero4);

                recyclerHide.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerHide,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    }
                                }));
                break;
            case 5:
                personagensHidden = personagensLista5;
                AdapterPersonagens adapterHero5 = new AdapterPersonagens(personagensHidden);
                recyclerHide.setAdapter(adapterHero5);

                recyclerHide.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getApplicationContext(),
                                recyclerHide,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    }
                                }));
                break;
            case 6:
                personagensHidden = personagensApi;
                AdapterApi adapter6 = new AdapterApi(MainActivity.this, personagensApi);
                recyclerHide.setAdapter(adapter6);
//
//                recyclerHide.addOnItemTouchListener(
//                        new RecyclerItemClickListener(
//                                getApplicationContext(),
//                                recyclerHide,
//                                new RecyclerItemClickListener.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(View view, int position) {
//                                        Intent i = new Intent(MainActivity.this, Apresentacao.class);
//                                        i.putExtra("teste", (Serializable) personagensHidden.get(position));
//                                        startActivity(i);
//                                    }
//
//                                    @Override
//                                    public void onLongItemClick(View view, int position) {
//
//                                    }
//
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                    }
//                                }));
                break;
        }


    }
}



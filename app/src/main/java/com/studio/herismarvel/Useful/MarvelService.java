package com.studio.herismarvel.Useful;

//import com.google.gson.JsonElement;

//import retrofit2.Call;
//import retrofit2.http.GET;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarvelService {


    //Call chamadas ao servidor externo, obtem uma respota
    @GET("v1/public/characters?ts=1&apikey=3571f9a8b65c6c92abaf53edbc564676&hash=1760b464b7215db78973d87a9d8bb555")
    Call<JsonElement> recuperaChar();
}

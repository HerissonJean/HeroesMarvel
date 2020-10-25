package com.studio.herismarvel;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PersonagemPojo implements Serializable {

    @SerializedName("name")
    String name ;

    @SerializedName("thumbnail")
    Thumbnail thumbnail;

    public PersonagemPojo(String name, Thumbnail thumbnail) {
        this.name = name;
        this.thumbnail =  thumbnail;
    }


    public static class Thumbnail implements Serializable {

     //   @SerializedName("path")
        String path ;

   //     @SerializedName("extension")
        String extension ;

        public Thumbnail(String path, String extension) {
            this.path = path;
            this.extension =  extension;
        }

    }


}

package com.studio.herismarvel;

import java.io.Serializable;
import java.util.ArrayList;

public class Personagem implements Serializable {

    Integer numImagem;
    String name;
    String personagem;
    int identificador;
    String thumbnail;
    String path;
    String extension;


    public Personagem(Integer numImagem, int identificador, String personagem, String name) {
        this.numImagem = numImagem;
        this.identificador = identificador;
        this.personagem = personagem;
        this.name = name;

    }

    public Personagem(String name , String path,String extension) {
        this.name = name;
        this.path = path;
        this.extension = extension;
    }
    public Personagem() {

    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNumImagem() {
        return numImagem;
    }

    public void setNumImagem(Integer numImagem) {
        this.numImagem = numImagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }


    public ArrayList<Personagem> classificacaoRecycler(int tipo ) {
        ArrayList<Personagem> Aux = new ArrayList<>();
        Aux.clear();
        Integer[] numImage = {
                R.drawable.char_spider_man, R.drawable.char_pantera_negra, R.drawable.char_homem_ferro,
                R.drawable.char_caveira_vermelha, R.drawable.char_dr_doom, R.drawable.char_duende,
                R.drawable.char_deadpool, R.drawable.char_venom, R.drawable.char_justiceiro,
                R.drawable.char_thanos, R.drawable.char_ronan, R.drawable.char_talos,
                R.drawable.char_howard_stark, R.drawable.char_mary_jane, R.drawable.char_hogan};
        int[] iden = {
                1, 1, 1,
                2, 2, 2,
                3, 3, 3,
                4, 4, 4,
                5, 5, 5};
        String[] personagens = {
                "Homem Aranha ", "Pantera Negra ", "Homem de Ferro ",
                "Caveira Vermelha ", "DR Mundo ", "Duende Verde ",
                "Deadpool ", "Venom ", "Justiceiro",
                "Thanos ", "Ronan ", "Talos ",
                "Haward Stark", "Mary Jane", "Happy Hogan"};

        String[] nome = {
                "Peter Parker", "T'Challa", "Tony Stark",
                "Jöhann Schmidt", "Victor Von Doom", "Norman Osborn",
                "Wade Wilson", "Eddie Brock", "Francis Castle",
                "Deviant", "Kree", "Skrull",
                "Homem de Ferro", "Homem Aranha", "Homem de Ferro"
        };

        for (int i = 0; i < iden.length; i++) {
            if (tipo == iden[i]) {
                Personagem modelo = new Personagem(numImage[i], iden[i], personagens[i], nome[i]);
                switch (tipo) {
                    case 1:
                        Aux.add(modelo);
                        break;
                    case 2:
                        Aux.add(modelo);
                        break;
                    case 3:
                        Aux.add(modelo);
                        break;
                    case 4:
                        Aux.add(modelo);
                        break;
                    case 5:
                        Aux.add(modelo);
                        break;
                }

            }
        }
return Aux;
    }


}

package com.example.duofinder_tfg;

public class Usuario {
    private String nombre;
    private int imagen;
    private String rol;
    private String elo;
    private String champ1;
    private String champ2;
    private String champ3;
    private boolean like;

    Usuario(int image, String name, String rol, String elo, String champ1, String champ2, String champ3, boolean like){
        this.imagen = image;
        this.nombre= name;
        this.rol=rol;
        this.elo=elo;
        this.champ1=champ1;
        this.champ2=champ2;
        this.champ3=champ3;
        this.like = like;
    }

    public String getRol() {
        return rol;
    }

    public String getElo() {
        return elo;
    }

    public String getChamp1() {
        return champ1;
    }

    public String getChamp2() {
        return champ2;
    }

    public String getChamp3() {
        return champ3;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public boolean isLike() {
        return like;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public void setChamp1(String champ1) {
        this.champ1 = champ1;
    }

    public void setChamp2(String champ2) {
        this.champ2 = champ2;
    }

    public void setChamp3(String champ3) {
        this.champ3 = champ3;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}

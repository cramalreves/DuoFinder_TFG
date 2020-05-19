package com.example.duofinder_tfg;

public class Usuario {
    private String nombre;
    private int imagen;
    private boolean like;

    Usuario(String name, int image, boolean like){
        this.imagen = image;
        this.nombre= name;
        this.like = like;
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

package com.example.duofinder_tfg;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String username;
    private String discord;
    private int photo;

    public User(int id, String username, String discord, int photo) {
        this.id = id;
        this.username = username;
        this.discord = discord;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

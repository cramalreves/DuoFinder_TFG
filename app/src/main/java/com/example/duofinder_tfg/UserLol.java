package com.example.duofinder_tfg;

public class UserLol extends User{
    private String summoner_name;
    private String server;
    private String elo;
    private String role;
    private String champ1;
    private String champ2;
    private String champ3;
    private boolean like;

    public UserLol(int id, String username, String discord, int photo, String summoner_name, String server,
                   String elo, String rol, String champ1, String champ2, String champ3, boolean like) {
        super(id, username, discord, photo);
        this.summoner_name = summoner_name;
        this.server = server;
        this.elo = elo;
        this.role = rol;
        this.champ1 = champ1;
        this.champ2 = champ2;
        this.champ3 = champ3;
        this.like = like;
    }

    public String getElo() {
        return elo;
    }

    public String getServer() {
        return server;
    }

    public String getRole() {
        return role;
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

    public String getSummoner_name() {
        return summoner_name;
    }

    public boolean isLike() {
        return like;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setRole(String role) {
        this.role = role;
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

    public void setSummoner_name(String summoner_name) {
        this.summoner_name = summoner_name;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}

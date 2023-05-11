package com.example.duckling_movies;

import java.util.ArrayList;

public class Anime {
    private String name;

    private int year;

    private int curtida_qnt;

    private ArrayList<String> pessoas_curtiram;
    private String RA_resp;

    public Anime() {
        // Construtor sem argumentos necessário para o Firebase Realtime Database
    }
    public Anime(String name, int year, String RA_resp) {
        this.name = name;
        this.year = year;
        this.RA_resp = RA_resp;
        this.curtida_qnt = 0;
    }

    public Anime(String name, int year, String RA_resp, int curtida_qnt,  ArrayList<String> pessoas_curtiram) {
        this.name = name;
        this.year = year;
        this.RA_resp = RA_resp;
        this.curtida_qnt = curtida_qnt;
        this.pessoas_curtiram = pessoas_curtiram;
    }


    public int getYear(){
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRA_resp() {
        return RA_resp;
    }

    public void setRA_resp(String RA_resp) {
        this.RA_resp = RA_resp;
    }

    public int getCurtida_qnt(){
        return curtida_qnt;
    }

    public void setCurtida_qnt(int curtida_qnt) {
        this.curtida_qnt = curtida_qnt;
    }

    public ArrayList<String> getPessoas_curtiram(){
        return pessoas_curtiram;
    }

    public void setPessoas_curtiram ( ArrayList<String> pessoas_curtiram) {
        this.pessoas_curtiram = pessoas_curtiram;
    }


}
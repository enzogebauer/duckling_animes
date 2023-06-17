package com.example.duckling_movies;

import com.google.android.material.textfield.TextInputEditText;

public class Usuario {
    private String name;
    private String email;
    private String password;
    private String matricula;

    public Usuario() {
        // Construtor sem argumentos necessário para o Firebase Realtime Database
    }
    public Usuario(String name, String email, String password, String matricula) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.matricula = matricula;
    }


    public String getMatricula(){
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.name = matricula;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
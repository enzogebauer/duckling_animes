package com.example.duckling_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Anime_feed extends AppCompatActivity {
    Button btn_voltar, btn_gerenciar_animes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_feed);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_gerenciar_animes = findViewById(R.id.btn_gerenciar_animes);

        btn_gerenciar_animes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicie a activity desejada aqui
                Intent intent = new Intent(getApplicationContext(), AnimeManage.class);
                startActivity(intent);
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chamar a função EnviaDadosUsuario() dentro do onClick()
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}
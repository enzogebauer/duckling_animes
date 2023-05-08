package com.example.duckling_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimeManage extends AppCompatActivity {
    Button btn_voltar, btn_cadastrar_novo_anime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_manage);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_cadastrar_novo_anime = findViewById(R.id.btn_cadastrar_novo_anime);

        btn_cadastrar_novo_anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicie a activity desejada aqui
                Intent intent = new Intent(getApplicationContext(), PostAnimes.class);
                startActivity(intent);
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicie a activity desejada aqui
                Intent intent = new Intent(getApplicationContext(), Anime_feed.class);
                startActivity(intent);
            }
        });


    }
}
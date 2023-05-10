package com.example.duckling_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AnimeManage extends AppCompatActivity {
    Button btn_voltar, btn_cadastrar_novo_anime;
    ListView lv_animes_manage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_manage);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_cadastrar_novo_anime = findViewById(R.id.btn_cadastrar_novo_anime);
        lv_animes_manage = findViewById(R.id.list_view_animes_manage);

        preenche_animes_manage();
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

    public void preenche_animes_manage(){
        
    }
}


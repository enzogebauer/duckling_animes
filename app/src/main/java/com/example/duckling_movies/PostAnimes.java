package com.example.duckling_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PostAnimes extends AppCompatActivity {
    Button btn_cadastrar, btn_voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_animes);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        btn_voltar = findViewById(R.id.btn_voltar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Valida_anime();
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicie a activity desejada aqui
                Intent intent = new Intent(getApplicationContext(), AnimeManage.class);
                startActivity(intent);
            }
        });

    }
}
package com.example.duckling_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Anime_feed extends AppCompatActivity {
    Button btn_voltar, btn_gerenciar_animes;

    DatabaseReference animeRef = FirebaseDatabase.getInstance().getReference().child("anime");
    ListView lv_animes_feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_feed);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_gerenciar_animes = findViewById(R.id.btn_gerenciar_animes);
        lv_animes_feed = findViewById(R.id.list_view_animes_feed);

        List<String> animeList = new ArrayList<>();

        animeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Limpa a lista de animes antes de adicionar os dados atualizados
                animeList.clear();

                // Itera sobre todos os filhos do nó "anime"
                for (DataSnapshot animeSnapshot : dataSnapshot.getChildren()) {
                    // Cria um objeto Anime com os dados do snapshot
                    Anime anime = animeSnapshot.getValue(Anime.class);
                    // Adiciona o objeto Anime à lista de animes
                    animeList.add(anime.getName() + "Ano: " + anime.getYear());
                }
                // Aqui você pode fazer qualquer coisa que precisa ser feita após atualizar a lista
                // Por exemplo, atualizar a exibição da lista em um RecyclerView ou ListView
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Lida com erros de leitura do banco de dados
                Log.e("TAG", "Erro ao ler dados do Firebase", databaseError.toException());
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animeList);
        lv_animes_feed.setAdapter(adapter);

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
                GlobalVariables.RA_atual = null;
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}
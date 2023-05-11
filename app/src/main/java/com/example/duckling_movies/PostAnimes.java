package com.example.duckling_movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PostAnimes extends AppCompatActivity {
    Button btn_cadastrar, btn_voltar;

    TextInputEditText name, year;

    DatabaseReference animeRef = FirebaseDatabase.getInstance().getReference().child("anime");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_animes);
        FirebaseApp.initializeApp(this);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        btn_voltar = findViewById(R.id.btn_voltar);
        name = findViewById(R.id.nome_anime);

        year = findViewById(R.id.ano_anime);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insere_anime();
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
    public void insere_anime() {
        // Obter os dados de entrada do usuário
        String Nome = name.getText().toString();
        String ano = year.getText().toString();

        // Verificar se os campos de entrada não estão vazios
        if (TextUtils.isEmpty(Nome)) {
            name.setError("Por favor, digite o nome!");
            name.requestFocus();
        }

        if (TextUtils.isEmpty(ano)) {
            year.setError("Por favor, digite o ano do seu anime!");
            year.requestFocus();
        }



        // Criar um objeto UserModel com os dados de entrada do usuário
        Anime anime = new Anime(name.getText().toString(), Integer.parseInt(year.getText().toString()) , GlobalVariables.RA_atual);

        Query query_nome = animeRef.orderByChild("nome").equalTo(anime.getName());
        Query query_ano = animeRef.orderByChild("ano").equalTo(anime.getYear());
        query_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Se já existe um anime com o mesmo nome, mostrar um toast de erro
                if (snapshot.exists()) {
                    query_ano.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(PostAnimes.this, "Já existe um anime com mesmo nome e mesmo ano", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(PostAnimes.this, "Erro ao verificar se o ano já existe ao mesmo tempo", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                // Se os dados do usuário ainda não existem no banco de dados, adicioná-los
                animeRef.push().setValue(anime).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PostAnimes.this, "Dados do anime salvos com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostAnimes.this, "Erro ao salvar os dados do anime", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PostAnimes.this, "Erro ao verificar se o nome já existe ao mesmo tempo", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

package com.example.duckling_movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button btn_login;
    TextInputEditText email, senha;
    DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuario");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chamar a função EnviaDadosUsuario() dentro do onClick()
                ValidaLogin();
            }
        });
    }

    public void RedirecionaLogin(){
        Intent intent = new Intent(getApplicationContext(), Anime_feed.class);
        startActivity(intent);
    }

    public void ValidaLogin(){
        String Email = email.getText().toString();
        String Senha = senha.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            email.setError("Por favor, digite o e-mail");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Senha)) {
            senha.setError("Por favor, digite a senha");
            senha.requestFocus();
            return;
        }

        Query query = usuarioRef.orderByChild("email").equalTo(Email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Iterar sobre os usuários com o email fornecido e verificar a senha
                    boolean senhaCorreta = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        if (usuario.getPassword().equals(Senha)) {
                            // Senha correta, fazer o login
                            Toast.makeText(Login.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                            RedirecionaLogin();
                            GlobalVariables.RA_atual = usuario.getMatricula();
                            senhaCorreta = true;
                            break;
                        }
                    }
                    if (!senhaCorreta) {
                        // Senha incorreta, mostrar mensagem de erro
                        Toast.makeText(Login.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Usuário com o email fornecido não encontrado, mostrar mensagem de erro
                    Toast.makeText(Login.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Ocorreu um erro ao consultar o banco de dados Firebase, mostrar mensagem de erro
                Toast.makeText(Login.this, "Erro ao consultar o banco de dados Firebase", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
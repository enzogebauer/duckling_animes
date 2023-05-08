package com.example.duckling_movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    Button btnEnviarRegistro;
    TextInputEditText matricula, nome, senha, email;
    DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuario");
    TextView clickHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnEnviarRegistro = findViewById(R.id.btn_register);
        matricula = findViewById(R.id.matricula);
        nome = findViewById(R.id.nome);
        senha = findViewById(R.id.senha);
        email = findViewById(R.id.email);
        clickHere = findViewById(R.id.clickhere);
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inicie a activity desejada aqui
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btnEnviarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chamar a função EnviaDadosUsuario() dentro do onClick()
                EnviaDadosUsuario();
            }
        });
    }

    // Definir a função EnviaDadosUsuario() fora do método onClick
    public void EnviaDadosUsuario() {

        // Obter os dados de entrada do usuário
        String Nome = nome.getText().toString();
        String Email = email.getText().toString();
        String Senha = senha.getText().toString();
        String Matricula = matricula.getText().toString();
        // Verificar se os campos de entrada não estão vazios
        if (TextUtils.isEmpty(Nome)) {
            nome.setError("Por favor, digite o nome!");
            nome.requestFocus();
        }
        else{

        }
        if (TextUtils.isEmpty(Matricula)) {
            matricula.setError("Por favor, digite o número da sua matrícula!");
            matricula.requestFocus();
        }

        if (TextUtils.isEmpty(Email)) {
            email.setError("Por favor, digite o e-mai!l");
            email.requestFocus();
        }

        if (TextUtils.isEmpty(Senha)) {
            senha.setError("Por favor, digite a senha!");
            senha.requestFocus();
        }
        if (TextUtils.isEmpty(Matricula)) {
            matricula.setError("Por favor, digite a matricula!");
            matricula.requestFocus();
        }

        // Criar um objeto UserModel com os dados de entrada do usuário
        Usuario user = new Usuario(Nome, Email, Senha, Matricula);

        Query query = usuarioRef.orderByChild("matricula").equalTo(Matricula);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Se já existe um usuário com a mesma matrícula, mostrar um toast de erro
                if (snapshot.exists()) {
                    Toast.makeText(Register.this, "Já existe um usuário com a mesma matrícula", Toast.LENGTH_SHORT).show();
                    return; // não sei se precisa do return
                }

                // Verificar se já existe um usuário com o mesmo nome
                Query query = usuarioRef.orderByChild("nome").equalTo(Nome);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Se já existe um usuário com o mesmo nome, mostrar um toast de erro
                        if (snapshot.exists()) {
                            Toast.makeText(Register.this, "Já existe um usuário com o mesmo nome", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Verificar se já existe um usuário com o mesmo email
                        Query query = usuarioRef.orderByChild("email").equalTo(Email);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                // Se já existe um usuário com o mesmo email, mostrar um toast de erro
                                if (snapshot.exists()) {
                                    Toast.makeText(Register.this, "Já existe um usuário com o mesmo email", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // Se os dados do usuário ainda não existem no banco de dados, adicioná-los
                                usuarioRef.push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "Dados do usuário salvos com sucesso", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Register.this, "Erro ao salvar os dados do usuário", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(Register.this, "Erro ao verificar se o email já existe", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Register.this, "Erro ao verificar se o nome já existe", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, "Erro ao verificar se a matrícula já existe", Toast.LENGTH_SHORT).show();
            }
        });

    }




}


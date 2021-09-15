package com.example.bigboss.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bigboss.Controller.AdapterExercicioConcluido;
import com.example.bigboss.DAO.ExercicioConcluidoDAO;
import com.example.bigboss.DAO.UsuarioDAO;
import com.example.bigboss.Model.ExercicioConcluido;
import com.example.bigboss.Model.Usuario;
import com.example.bigboss.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class telaPerfil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView nomeUsuario;
    TextView divisao;
    TextView level;
    AdapterExercicioConcluido adaptador;

    RecyclerView recyclerView;
    List<ExercicioConcluido> listaBancoExercicios = new ArrayList<>();
    ExercicioConcluidoDAO exercicioConcluidoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);


        getSupportActionBar().setTitle("Perfil do Usuário");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    telaInicialBottomNavigation();
                }
                else if (item.getItemId() == R.id.help) {
                    perguntasFrequentesBottomNavigation();
                }

                return false;
            }
        });

        nomeUsuario = findViewById(R.id.nomeUsuario2);
        divisao = findViewById(R.id.textDivisao2);
        level = findViewById(R.id.textLevel);

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        Usuario usuario = usuarioDAO.buscarUsuario();

        nomeUsuario.setText(usuario.getNome());
        divisao.setText("Divisão "+ usuario.getDivisao());
        level.setText("Level " + String.valueOf(usuario.getLevel()));

        exercicioConcluidoDAO = new ExercicioConcluidoDAO(getApplicationContext());
        listaBancoExercicios = exercicioConcluidoDAO.ListarExercicios();
        adaptador = new AdapterExercicioConcluido(listaBancoExercicios);
        recyclerView = findViewById(R.id.listaExerciciosConcluidos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptador);

    }

    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        startActivity(intent);
    }

    public void perguntasFrequentesBottomNavigation(){
        Intent intent = new Intent(this, TelaPerguntasFrequente.class);
        startActivity(intent);
    }
    public void telaInicialBottomNavigation(){
        Intent intent = new Intent(this, telaInicio.class);
        startActivity(intent);
    }
}
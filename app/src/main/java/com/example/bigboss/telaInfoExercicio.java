package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class telaInfoExercicio extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textProgressBar;
    int value = 24;
    int total = 30;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_info_exercicio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Informação Exercício");

        //Barra de Progresso
        progressBar = findViewById(R.id.exercicioProgresso1);
        textProgressBar = findViewById(R.id.textExercicioProgresso1);

        progressBar.setProgress((100*value)/total);
        textProgressBar.setText(String.valueOf(value)+"/"+String.valueOf(total));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.user) {
                    telaPerfilBottomNavigation();
                }
                else if (item.getItemId() == R.id.home) {
                    telaInicialBottomNavigation();
                }
                else if (item.getItemId() == R.id.help) {
                    perguntasFrequentesBottomNavigation();
                }

                return false;
            }
        });
    }

    public void perguntasFrequentes(View view){
        Intent intent = new Intent(this, TelaPerguntasFrequente.class);
        startActivity(intent);
    }
    public void telaPerfilBottomNavigation(){
        Intent intent = new Intent(this, telaPerfil.class);
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
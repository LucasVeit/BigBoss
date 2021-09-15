package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void configuracao(View view){
        Intent intent = new Intent(this, telaConfiguracao.class);
        startActivity(intent);
    }

    public void editarExercicio(View view){
        Intent intent = new Intent(this, telaEditarExercicio.class);
        startActivity(intent);
    }

    public void perguntasFrequentes(View view){
        Intent intent = new Intent(this, TelaPerguntasFrequente.class);
        startActivity(intent);
    }

    public void adicionarExercicio(View view){
        Intent intent = new Intent(this, telaAdicionarExercicio.class);
        startActivity(intent);
    }

    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        startActivity(intent);
    }

    public void telaInicial(View view){
        //Intent intent = new Intent(this, telaInicio.class);
        Intent intent = new Intent(this, TelaInicioNOVA.class);
        startActivity(intent);
    }

    public void telaPerfil(View view){
        Intent intent = new Intent(this, telaPerfil.class);
        startActivity(intent);
    }
}
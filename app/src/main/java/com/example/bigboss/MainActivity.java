package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent intent = new Intent(this, telaPerguntasFrequentes.class);
        startActivity(intent);
    }

}
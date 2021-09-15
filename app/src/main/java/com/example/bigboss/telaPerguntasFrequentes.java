package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class telaPerguntasFrequentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perguntas_frequentes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perguntas Frequentes");
    }
}
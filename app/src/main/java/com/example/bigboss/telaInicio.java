package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class telaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Big Boss");
    }
}
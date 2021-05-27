package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class telaPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil do Usuário");
    }
}
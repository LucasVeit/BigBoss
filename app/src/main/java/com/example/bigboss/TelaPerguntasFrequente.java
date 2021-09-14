package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaPerguntasFrequente extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_perguntas_frequente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perguntas Frequentes");

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


                return false;
            }
        });

    }


    public void showmyinformation(View view){
        ExpandableRelativeLayout expandableRelativeLayout = null;

        switch (view.getId()){
            case R.id.button1:
                expandableRelativeLayout = findViewById(R.id.expand1);
                break;

            case R.id.button2:
                expandableRelativeLayout = findViewById(R.id.expand2);
                break;

            case R.id.button3:
                expandableRelativeLayout = findViewById(R.id.expand3);
                break;
        }
        assert expandableRelativeLayout != null;
        expandableRelativeLayout.toggle();
    }
    public void telaPerfilBottomNavigation(){
        Intent intent = new Intent(this, telaPerfil.class);
        startActivity(intent);
    }

    public void telaInicialBottomNavigation(){
        Intent intent = new Intent(this, telaInicio.class);
        startActivity(intent);
    }
}

package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.bigboss.Controller.AdapterExercicioAndamento;
import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.Utils.RecyclerItemClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class telaInicio extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView listaExercicios;
    List<ExercicioAndamento> listaBancoExercicios = new ArrayList<>();
    ExercicioAndamentoDAO exercicioAndamentoDAO;
    AdapterExercicioAndamento adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio);

        exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        listaBancoExercicios = exercicioAndamentoDAO.ListarExercicios();
        adaptador = new AdapterExercicioAndamento(listaBancoExercicios);

        listaExercicios = findViewById(R.id.listaExerciciosAndamento);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaExercicios.setLayoutManager(layoutManager);
        listaExercicios.setHasFixedSize(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaExercicios.setAdapter(adaptador);

        getSupportActionBar().setTitle("Big Boss");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.user) {
                    telaPerfilBottomNavigation();
                }

                else if (item.getItemId() == R.id.help) {
                    perguntasFrequentesBottomNavigation();
                }

                return false;
            }
        });

        listaExercicios.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        listaExercicios,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ExercicioAndamento exercicio = listaBancoExercicios.get( position );
                                OpeninfoExercicio(view, exercicio);
                            }
                            @Override
                            public void onItemClick(AdapterView<?> adapterView,
                                                    View view, int i, long l) {
                            }
                        }
                )
        );
    }

    public void adicionarExercicio(View view){
        Intent intent = new Intent(this, telaAdicionarExercicio.class);
        startActivity(intent);
    }
    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        startActivity(intent);
    }
    public void OpeninfoExercicio(View view, ExercicioAndamento exercicioAndamento){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        intent.putExtra("exercicio", exercicioAndamento);
        intent.putExtra("lembrete", exercicioAndamento.getLembrete());
        startActivity(intent);
    }
    public void configuracao(View view){
        Intent intent = new Intent(this, telaConfiguracao.class);
        startActivity(intent);
    }
    public void telaPerfil(View view){
        Intent intent = new Intent(this, telaPerfil.class);
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

}
package com.example.bigboss.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bigboss.Controller.AdapterExercicioAndamento;
import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.DAO.UsuarioDAO;
import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.Model.Usuario;
import com.example.bigboss.R;
import com.example.bigboss.Utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class telaInicio extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView listaExercicios;
    List<ExercicioAndamento> listaBancoExercicios = new ArrayList<>();
    ExercicioAndamentoDAO exercicioAndamentoDAO;
    UsuarioDAO usuarioDAO;
    AdapterExercicioAndamento adaptador;
    TextView nomeUsuario;
    TextView divisao;
    ProgressBar barraNivel;
    TextView level;
    TextView exp;
    TextView expMax;
    int idClicked;

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
        nomeUsuario = findViewById(R.id.nomeUsuario);
        divisao = findViewById(R.id.textDivisao);
        barraNivel = findViewById(R.id.progressBar);
        level = findViewById(R.id.textLevel);
        exp = findViewById(R.id.textViewExp);
        expMax = findViewById(R.id.textExpMax);

        usuarioDAO = new UsuarioDAO(getApplicationContext());
        Usuario usuario = usuarioDAO.buscarUsuario();
        if(usuario.getCodigo() == 0){
            usuario.setCodigo(1);
            usuario.setDivisao("Frango");
            usuario.setNome("The Boss");
            usuario.setLevel(1);
            usuario.setXp(0);
            usuarioDAO.CriarUsuario(usuario);
        }
        nomeUsuario.setText(usuario.getNome());
        divisao.setText("Divisão " + String.valueOf(usuario.getDivisao()));
        barraNivel.setProgress((100*usuario.getXp())/usuario.getLevel()*100);
        level.setText("Leval " + String.valueOf(usuario.getLevel()));
        exp.setText(String.valueOf(usuario.getXp()));
        expMax.setText(String.valueOf(usuario.getLevel() * 100));



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

    @Override
    public void onResume(){
        super.onResume();

        Usuario usuario = usuarioDAO.buscarUsuario();
        nomeUsuario.setText(usuario.getNome());
        divisao.setText("Divisão " + String.valueOf(usuario.getDivisao()));
        barraNivel.setProgress((100*usuario.getXp())/usuario.getLevel()*100);
        level.setText("Level " + String.valueOf(usuario.getLevel()));
        exp.setText(String.valueOf(usuario.getXp()));
        expMax.setText(String.valueOf(usuario.getLevel() * 100));


        listaBancoExercicios = exercicioAndamentoDAO.ListarExercicios();
        adaptador = new AdapterExercicioAndamento(listaBancoExercicios);
        listaExercicios.setAdapter(adaptador);

    }

}
package com.example.bigboss.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText novoNome;
    private Button botaoCancelar, botaoSalvar;
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

    public void createNewContentDialog(View view){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        novoNome = (EditText) popupView.findViewById(R.id.novoNome);

        botaoCancelar = (Button) popupView.findViewById(R.id.BotaoCancelar);
        botaoSalvar = (Button) popupView.findViewById(R.id.BotaoSalvar);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        botaoCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //funcionalidade do botao
            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
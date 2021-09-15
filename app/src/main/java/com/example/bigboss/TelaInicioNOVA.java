package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.bigboss.Controller.AdapterExercicioAndamento;
import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.Model.ExercicioAndamento;

import java.util.ArrayList;
import java.util.List;

public class TelaInicioNOVA extends AppCompatActivity {
    RecyclerView listaExercicios;
    List<ExercicioAndamento> listaBancoExercicios = new ArrayList<>();
    ExercicioAndamentoDAO exercicioAndamentoDAO;
    AdapterExercicioAndamento adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio_nova);

//        createItems();
        exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        listaBancoExercicios = exercicioAndamentoDAO.ListarExercicios();
        adaptador = new AdapterExercicioAndamento(listaBancoExercicios);

        listaExercicios = findViewById(R.id.listaExerciciosAndamento);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaExercicios.setLayoutManager(layoutManager);
        listaExercicios.setHasFixedSize(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Big Boss");
        listaExercicios.setAdapter(adaptador);
        Log.i("Testee", "vendo se vai");
    }

//    private void createItems(){
//        Lembrete lembrete = new Lembrete(1, "Aeróbico", 10, 15, true, true, true, true, true, true, true);
//        ExercicioAndamento exercicioAndamento = new ExercicioAndamento(1, "Aeróbico", "Etaaa", 3, "Repetições", 10, 5, 20, 10,  Calendar.getInstance(), lembrete);
//        listaBancoExercicios.add(exercicioAndamento);
//        exercicioAndamento = new ExercicioAndamento(1, "Aeróbico", "Etaaa", 3, "Repetições", 10, 5, 20, 10,  Calendar.getInstance(), lembrete);
//        listaBancoExercicios.add(exercicioAndamento);
//        listaBancoExercicios.add(exercicioAndamento);
//        listaBancoExercicios.add(exercicioAndamento);
//        listaBancoExercicios.add(exercicioAndamento);
//
//    }

}
package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bigboss.Model.ExercicioAndamento;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;

import kotlin.text.StringsKt;

public class telaEditarExercicio extends AppCompatActivity {
    //Variaveis do relógio
    TextView tvTimer;
    int tHour, tMin;
    ExercicioAndamento exercicioAndamento;
    TextInputLayout nome;
    TextInputLayout descricao;
    TextInputLayout series;
    TextInputLayout quantidade;
    TextInputLayout objetivo;
    TextInputLayout periodo;


    BottomNavigationView bottomNavigationView;

    //Dropdown Menu
    TextInputLayout metrica;
    AutoCompleteTextView metricas;
    ArrayList<String> arrayList_metricas;
    ArrayAdapter<String> arrayAdapter_metricas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_exercicio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Editar Exercício");

        //Reminder
        tvTimer = findViewById(R.id.tv_timer);

        // Outros inputs
        nome = (TextInputLayout)findViewById(R.id.textInputNome);
        descricao = (TextInputLayout)findViewById(R.id.textInputDescricao);
        series = (TextInputLayout)findViewById(R.id.textInputSeries);
        quantidade = (TextInputLayout)findViewById(R.id.textInputQuantidade);
        objetivo = (TextInputLayout)findViewById(R.id.textInputObjetivo);
        periodo = (TextInputLayout)findViewById(R.id.textInputPeriodo);



        tvTimer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //inicializa a caixa de dialogo
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        telaEditarExercicio.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Inicializa a hora e minutos
                                tHour = hourOfDay;
                                tMin = minute;
                                //Inicializa o calendario
                                Calendar calendar = Calendar.getInstance();
                                //Define a hora e minuto
                                calendar.set(0, 0, 0, tHour, tMin);
                                //Define o tempo selecionado no text view
                                tvTimer.setText(android.text.format.DateFormat.format("h:mm a", calendar));
                            }
                        }, 12, 0, false
                );
                //timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // Mostra o tempo selecionado previamente
                timePickerDialog.updateTime(tHour, tMin);
                // Mostra o dialogo
                timePickerDialog.show();
            }
        });

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
                else if (item.getItemId() == R.id.help) {
                    perguntasFrequentesBottomNavigation();
                }

                return false;
            }
        });

        exercicioAndamento = getIntent().getParcelableExtra("exercicio");
        exercicioAndamento.setLembrete(getIntent().getParcelableExtra("lembrete"));


        nome.getEditText().setText(exercicioAndamento.getNome());
        descricao.getEditText().setText(exercicioAndamento.getDescricao());
        series.getEditText().setText(String.valueOf(exercicioAndamento.getSerie()));
        quantidade.getEditText().setText(String.valueOf(exercicioAndamento.getQuantidadeMetrica()));
        objetivo.getEditText().setText(String.valueOf(exercicioAndamento.getQuantidadeObjetivo()));
        periodo.getEditText().setText(String.valueOf(exercicioAndamento.getNumeroDias()));

        //métricas
        metrica=(TextInputLayout)findViewById(R.id.textInputMetrica);
        metricas=(AutoCompleteTextView)findViewById(R.id.autoCompleteMetrica);

        metrica.getEditText().setText(exercicioAndamento.getMetrica());

        arrayList_metricas = new ArrayList<>();
        arrayList_metricas.add("Repetições");
        arrayList_metricas.add("Minutos");
        arrayList_metricas.add("Horas");
        arrayList_metricas.add("Metros");
        arrayList_metricas.add("Quilômetros");
        arrayAdapter_metricas = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, arrayList_metricas);
        metricas.setAdapter(arrayAdapter_metricas);

        tHour = exercicioAndamento.getLembrete().getHora();
        tMin = exercicioAndamento.getLembrete().getMinuto();
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, tHour, tMin);
        tvTimer.setText(android.text.format.DateFormat.format("HH:mm", calendar));

        Button button;
        button = findViewById(R.id.buttonDomingo);
        if(!exercicioAndamento.getLembrete().isDomingo()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSegunda);
        if(!exercicioAndamento.getLembrete().isSegunda()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonTerca);
        if(!exercicioAndamento.getLembrete().isTerca()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuarta);
        if(!exercicioAndamento.getLembrete().isQuarta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuinta);
        if(!exercicioAndamento.getLembrete().isQuinta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSexta);
        if(!exercicioAndamento.getLembrete().isSexta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSabado);
        if(!exercicioAndamento.getLembrete().isSabado()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }

    }

    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
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
    public void telaInicialBottomNavigation(){
        Intent intent = new Intent(this, telaInicio.class);
        startActivity(intent);
    }

}
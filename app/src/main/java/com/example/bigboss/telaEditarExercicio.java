package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.Model.ExercicioAndamento;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

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

    Boolean dom, seg, ter, qua, qui, sex, sab;

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
                                tvTimer.setText(android.text.format.DateFormat.format("HH:mm", calendar));
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

        tHour = exercicioAndamento.getHora();
        tMin = exercicioAndamento.getMinuto();
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, tHour, tMin);
        tvTimer.setText(android.text.format.DateFormat.format("HH:mm", calendar));

        dom = exercicioAndamento.isDomingo();
        seg = exercicioAndamento.isSegunda();
        ter = exercicioAndamento.isTerca();
        qua = exercicioAndamento.isQuarta();
        qui = exercicioAndamento.isQuinta();
        sex = exercicioAndamento.isSexta();
        sab = exercicioAndamento.isSabado();
        Button button;
        button = findViewById(R.id.buttonDomingo);
        if(!exercicioAndamento.isDomingo()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSegunda);
        if(!exercicioAndamento.isSegunda()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonTerca);
        if(!exercicioAndamento.isTerca()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuarta);
        if(!exercicioAndamento.isQuarta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonQuinta);
        if(!exercicioAndamento.isQuinta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSexta);
        if(!exercicioAndamento.isSexta()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }
        button = findViewById(R.id.buttonSabado);
        if(!exercicioAndamento.isSabado()){
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
            button.setTextColor(getResources().getColor(R.color.font));
        }else{
            button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
            button.setTextColor(getResources().getColor(R.color.icon));
        }

    }

    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        intent.putExtra("exercicio", exercicioAndamento);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
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

    public void ChangeState(View view){
        Button button;
        switch(view.getId()){
            case R.id.buttonDomingo:
                button = findViewById(R.id.buttonDomingo);
                if(dom){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                dom = !dom;
                break;
            case R.id.buttonSegunda:
                button = findViewById(R.id.buttonSegunda);
                if(seg){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                seg = !seg;
                break;
            case R.id.buttonTerca:
                button = findViewById(R.id.buttonTerca);
                if(ter){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                ter = !ter;
                break;
            case R.id.buttonQuarta:
                button = findViewById(R.id.buttonQuarta);
                if(qua){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                qua = !qua;
                break;
            case R.id.buttonQuinta:
                button = findViewById(R.id.buttonQuinta);
                if(qui){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                qui = !qui;
                break;
            case R.id.buttonSexta:
                button = findViewById(R.id.buttonSexta);
                if(sex){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                sex = !sex;
                break;
            case R.id.buttonSabado:
                button = findViewById(R.id.buttonSabado);
                if(sab){
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_grey));
                    button.setTextColor(getResources().getColor(R.color.font));
                }else{
                    button.setBackground(getResources().getDrawable(R.drawable.custom_button_days_blue));
                    button.setTextColor(getResources().getColor(R.color.icon));
                }
                sab = !sab;
                break;
        }
    }

    public void EditarExercicio(View view){
        exercicioAndamento.setNome(nome.getEditText().getText().toString());
        exercicioAndamento.setDescricao(descricao.getEditText().getText().toString());
        exercicioAndamento.setSerie(Integer.parseInt(series.getEditText().getText().toString()));
        exercicioAndamento.setMetrica(metrica.getEditText().getText().toString());
        exercicioAndamento.setQuantidadeMetrica(Integer.parseInt(quantidade.getEditText().getText().toString()));
        exercicioAndamento.setQuantidadeObjetivo(Integer.parseInt(objetivo.getEditText().getText().toString()));
        exercicioAndamento.setNumeroDias(Integer.parseInt(periodo.getEditText().getText().toString()));
        exercicioAndamento.setQuantidadeRealizada(0);
        exercicioAndamento.setDataInicio(Calendar.getInstance());
        exercicioAndamento.setHora(tHour);
        exercicioAndamento.setMinuto(tMin);
        exercicioAndamento.setDomingo(dom);
        exercicioAndamento.setSegunda(seg);
        exercicioAndamento.setTerca(ter);
        exercicioAndamento.setQuarta(qua);
        exercicioAndamento.setQuinta(qui);
        exercicioAndamento.setSexta(sex);
        exercicioAndamento.setSabado(sab);

        ExercicioAndamentoDAO exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        exercicioAndamentoDAO.AtualizarExercicio(exercicioAndamento);

        infoExercicio(view);
    }

}
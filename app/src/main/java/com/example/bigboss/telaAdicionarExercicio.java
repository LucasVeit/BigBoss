package com.example.bigboss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.bigboss.DAO.ExercicioAndamentoDAO;
import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.Model.Lembrete;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class telaAdicionarExercicio extends AppCompatActivity {
    //Variaveis do relógio
    TextView tvTimer;
    int tHour, tMin;
    TextInputLayout nome;
    TextInputLayout descricao;
    TextInputLayout series;
    TextInputLayout quantidade;
    TextInputLayout objetivo;
    TextInputLayout periodo;


    //Dropdown Menu
    TextInputLayout metrica;
    AutoCompleteTextView metricas;
    ArrayList<String> arrayList_metricas;
    ArrayAdapter<String> arrayAdapter_metricas;

    Boolean dom, seg, ter, qua, qui, sex, sab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_exercicio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar Exercício");

        //Reminder
        tvTimer = findViewById(R.id.tv_timer);

        // Outros inputs
        nome = (TextInputLayout)findViewById(R.id.textInputNome);
        descricao = (TextInputLayout)findViewById(R.id.textInputDescricao);
        series = (TextInputLayout)findViewById(R.id.textInputSeries);
        quantidade = (TextInputLayout)findViewById(R.id.textInputQuantidade);
        objetivo = (TextInputLayout)findViewById(R.id.textInputObjetivo);
        periodo = (TextInputLayout)findViewById(R.id.textInputPeriodo);

        //métricas
        metrica= (TextInputLayout)findViewById(R.id.textInputMetrica);
        metricas=(AutoCompleteTextView)findViewById(R.id.autoCompleteMetrica);
        arrayList_metricas = new ArrayList<>();
        arrayList_metricas.add("Repetições");
        arrayList_metricas.add("Minutos");
        arrayList_metricas.add("Horas");
        arrayList_metricas.add("Metros");
        arrayList_metricas.add("Quilômetros");
        arrayAdapter_metricas = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, arrayList_metricas);
        metricas.setAdapter(arrayAdapter_metricas);

        dom = false;
        seg = true;
        ter = true;
        qua = true;
        qui = true;
        sex = true;
        sab = false;

        tHour = 8;
        tMin = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, tHour, tMin);
        tvTimer.setText(android.text.format.DateFormat.format("HH:mm", calendar));

        tvTimer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //inicializa a caixa de dialogo
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        telaAdicionarExercicio.this,
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
                        }, 12, 0, true
                );
                //timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // Mostra o tempo selecionado previamente
                timePickerDialog.updateTime(tHour, tMin);
                // Mostra o dialogo
                timePickerDialog.show();
            }
        });
    }
    public void telaInicial (View view){
        Intent intent = new Intent(this, telaInicio.class);
        startActivity(intent);
    }
    public void infoExercicio(View view){
        Intent intent = new Intent(this, telaInfoExercicio.class);
        startActivity(intent);
    }

    public void AdicionarExercicio(View view){
        ExercicioAndamento exercicio = new ExercicioAndamento();
        exercicio.setNome(nome.getEditText().getText().toString());
        exercicio.setDescricao(descricao.getEditText().getText().toString());
        exercicio.setSerie(Integer.parseInt(series.getEditText().getText().toString()));
        exercicio.setMetrica(metrica.getEditText().getText().toString());
        exercicio.setQuantidadeMetrica(Integer.parseInt(quantidade.getEditText().getText().toString()));
        exercicio.setQuantidadeObjetivo(Integer.parseInt(objetivo.getEditText().getText().toString()));
        exercicio.setNumeroDias(Integer.parseInt(periodo.getEditText().getText().toString()));
        exercicio.setQuantidadeRealizada(0);
        exercicio.setDataInicio(Calendar.getInstance());

        Lembrete lembrete = new Lembrete(0, nome.getEditText().getText().toString(), tHour, tMin, dom, seg, ter, qua, qui, sex, sab);
        exercicio.setLembrete(lembrete);

        ExercicioAndamentoDAO exercicioAndamentoDAO = new ExercicioAndamentoDAO(getApplicationContext());
        exercicioAndamentoDAO.InserirExercicio(exercicio);
        telaInicial(view);
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

}
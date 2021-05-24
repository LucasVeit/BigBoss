package com.example.bigboss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;

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

        //métricas
        metrica=(TextInputLayout)findViewById(R.id.textInputMetrica);
        metricas=(AutoCompleteTextView)findViewById(R.id.autoCompleteMetrica);
        arrayList_metricas = new ArrayList<>();
        arrayList_metricas.add("Repetições");
        arrayList_metricas.add("Minutos");
        arrayList_metricas.add("Horas");
        arrayList_metricas.add("Metros");
        arrayList_metricas.add("Quilômetros");
        arrayAdapter_metricas = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdownitem, arrayList_metricas);
        metricas.setAdapter(arrayAdapter_metricas);



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
    }


}
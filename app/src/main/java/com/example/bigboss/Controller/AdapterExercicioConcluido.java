package com.example.bigboss.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.Model.ExercicioConcluido;
import com.example.bigboss.R;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdapterExercicioConcluido extends RecyclerView.Adapter<AdapterExercicioConcluido.MyViewHolder>  {
    private List<ExercicioConcluido> listaExercicios;

    public AdapterExercicioConcluido(List<ExercicioConcluido> listaExercicios) {
        this.listaExercicios = listaExercicios;
    }

    @Override
    public AdapterExercicioConcluido.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_box_exercicio_concluido, parent, false);
        return new AdapterExercicioConcluido.MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(AdapterExercicioConcluido.MyViewHolder holder, int position) {
        ExercicioConcluido exercicioConcluido = listaExercicios.get(position);
        holder.nomeExercicio.setText(exercicioConcluido.getNome());

        holder.series.setText("Series: " + String.valueOf(exercicioConcluido.getSerie()));
        holder.metricaValor.setText(exercicioConcluido.getMetrica() + ": " + String.valueOf(exercicioConcluido.getQuantidadeMetrica()));
        holder.exercicioProgresso.setText(String.valueOf(exercicioConcluido.getQuantidadeObjetivo()) + "/" + String.valueOf(exercicioConcluido.getQuantidadeObjetivo()));
        holder.progressoExercicio.setProgress(100);
    }

    @Override
    public int getItemCount() {
        return listaExercicios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeExercicio;
        TextView series;
        TextView metricaValor;
        TextView exercicioProgresso;
        ProgressBar progressoExercicio;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomeExercicio = itemView.findViewById(R.id.textNomeExercicioConcluido);
            series = itemView.findViewById(R.id.textSeriesConcluido);
            metricaValor = itemView.findViewById(R.id.textMetricaValorConcluido);
            exercicioProgresso = itemView.findViewById(R.id.textExercicioProgresso3);
            progressoExercicio = itemView.findViewById(R.id.exercicioProgresso3);
        }
    }
}

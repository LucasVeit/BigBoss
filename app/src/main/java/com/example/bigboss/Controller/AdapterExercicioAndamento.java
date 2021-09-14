package com.example.bigboss.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.R;

import java.util.List;

public class AdapterExercicioAndamento extends RecyclerView.Adapter<AdapterExercicioAndamento.MyViewHolder>  {
    private List<ExercicioAndamento> listaExercicios;

    public AdapterExercicioAndamento(List<ExercicioAndamento> listaExercicios) {
        this.listaExercicios = listaExercicios;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_box_exercicio_andamento, parent, false);
        return new MyViewHolder(itemLista);
    }

    //Vai criar a visualização na lista
    //position controla o posição dos itens
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ExercicioAndamento exercicioAndamento = listaExercicios.get(position);
        holder.nomeExercicio.setText(exercicioAndamento.getNome());
        holder.series.setText("Series: " + String.valueOf(exercicioAndamento.getSerie()));
        holder.metricaValor.setText(exercicioAndamento.getMetrica() + ": " + String.valueOf(exercicioAndamento.getQuantidadeMetrica()));
        holder.tempo.setText(exercicioAndamento.getNome());
        holder.exercicioProgresso.setText(exercicioAndamento.getNome());
        holder.progressoExercicio.setProgress((100*exercicioAndamento.getQuantidadeRealizada())/exercicioAndamento.getQuantidadeObjetivo());;
    }

    @Override
    public int getItemCount() {
        return listaExercicios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeExercicio;
        TextView series;
        TextView metricaValor;
        TextView tempo;
        TextView exercicioProgresso;
        ProgressBar progressoExercicio;
        ImageView checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomeExercicio = itemView.findViewById(R.id.textNomeExercicio);
            series = itemView.findViewById(R.id.textSeries);
            metricaValor = itemView.findViewById(R.id.textMetricaValor);
            tempo = itemView.findViewById(R.id.textTempo);
            exercicioProgresso = itemView.findViewById(R.id.textExercicioProgresso);
            progressoExercicio = itemView.findViewById(R.id.exercicioProgresso);
            checkbox = itemView.findViewById(R.id.checkBox);
        }
    }
}

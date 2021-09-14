package com.example.bigboss.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bigboss.Utils.ConnectSQLite;
import com.example.bigboss.Utils.CreateSQLite;
import com.example.bigboss.Model.ExercicioConcluido;

import java.util.ArrayList;
import java.util.List;

public class ExercicioConcluidoDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;
    private CreateSQLite banco;
    private ConnectSQLite connectionBanco;

    public ExercicioConcluidoDAO(Context ctx){
        connectionBanco = ConnectSQLite.getInstance(ctx);
        escrita = connectionBanco.getDatabase(true);
        leitura = connectionBanco.getDatabase(false);
    }

    public void InserirExercicio(ExercicioConcluido exercicioConcluido){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nome", exercicioConcluido.getNome());
        dados.put("serie", exercicioConcluido.getSerie());
        dados.put("metrica", exercicioConcluido.getMetrica());
        dados.put("quantidadeMetrica", exercicioConcluido.getQuantidadeMetrica());
        dados.put("quantidadeObjetivo", exercicioConcluido.getQuantidadeObjetivo());
        escrita.insert("ExercicioConcluido", null, dados);
    }

    public List<ExercicioConcluido> ListarExercicios(){
        List<ExercicioConcluido> exercicios = new ArrayList<>();
        ContentValues dados;

        String sql = "SELECT * FROM ExercicioConcluido";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
        int indicenome = cursor.getColumnIndex("nome");
        int indiceserie = cursor.getColumnIndex("serie");
        int indicemetrica = cursor.getColumnIndex("metrica");
        int indicequantidadeMetrica = cursor.getColumnIndex("quantidadeMetrica");
        int indicequantidadeObjetivo = cursor.getColumnIndex("quantidadeObjetivo");

        cursor.moveToFirst();

        while(cursor != null){
            int codigo = cursor.getInt(indicecodigo);
            String nome = cursor.getString(indicenome);
            int serie = cursor.getInt(indiceserie);
            String metrica = cursor.getString(indicemetrica);
            int quantidadeMetrica = cursor.getInt(indicequantidadeMetrica);
            int quantidadeObjetivo = cursor.getInt(indicequantidadeObjetivo);

            ExercicioConcluido exercicio = new ExercicioConcluido(codigo, nome, serie, metrica, quantidadeMetrica, quantidadeObjetivo);
            exercicios.add(exercicio);
            cursor.moveToNext();
        }

        return exercicios;
    }

    public ExercicioConcluido BuscarExercicio(String nomeExercicio){
        ExercicioConcluido exercicio;
        ContentValues dados;

        String sql = "SELECT * FROM ExercicioConcluido where nome = \"" + nomeExercicio + "\";";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
        int indicenome = cursor.getColumnIndex("nome");
        int indiceserie = cursor.getColumnIndex("serie");
        int indicemetrica = cursor.getColumnIndex("metrica");
        int indicequantidadeMetrica = cursor.getColumnIndex("quantidadeMetrica");
        int indicequantidadeObjetivo = cursor.getColumnIndex("quantidadeObjetivo");

        cursor.moveToFirst();

        int codigo = cursor.getInt(indicecodigo);
        String nome = cursor.getString(indicenome);
        int serie = cursor.getInt(indiceserie);
        String metrica = cursor.getString(indicemetrica);
        int quantidadeMetrica = cursor.getInt(indicequantidadeMetrica);
        int quantidadeObjetivo = cursor.getInt(indicequantidadeObjetivo);

        exercicio = new ExercicioConcluido(codigo, nome, serie, metrica, quantidadeMetrica, quantidadeObjetivo);

        return exercicio;
    }

    public void AtualizarExercicio(ExercicioConcluido exercicioConcluido){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nome", exercicioConcluido.getNome());
        dados.put("serie", exercicioConcluido.getSerie());
        dados.put("metrica", exercicioConcluido.getMetrica());
        dados.put("quantidadeMetrica", exercicioConcluido.getQuantidadeMetrica());
        dados.put("quantidadeObjetivo", exercicioConcluido.getQuantidadeObjetivo());

        String[] cod = {String.valueOf(exercicioConcluido.getCodigo())};
        escrita.update("ExercicioConcluido", dados, "codigo = ?", cod);
    }

    public void ExcluirExercicio(int codigoExercicio){
        String[] cod = {String.valueOf(codigoExercicio)};
        escrita.delete("ExercicioConcluido", "codigo = ?", cod);
    }

}

package com.example.bigboss.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bigboss.Utils.ConnectSQLite;
import com.example.bigboss.Utils.CreateSQLite;
import com.example.bigboss.Model.ExercicioAndamento;
import com.example.bigboss.Model.Lembrete;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExercicioAndamentoDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;
    private CreateSQLite banco;
    private ConnectSQLite connectionBanco;
    LembreteDAO lembreteDAO;

    public ExercicioAndamentoDAO(Context ctx){
        connectionBanco = ConnectSQLite.getInstance(ctx);
        lembreteDAO = new LembreteDAO(ctx);
        escrita = connectionBanco.getDatabase(true);
        leitura = connectionBanco.getDatabase(false);
    }

    public void InserirExercicio(ExercicioAndamento exercicioAndamento){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nome", exercicioAndamento.getNome());
        dados.put("descricao", exercicioAndamento.getDescricao());
        dados.put("serie", exercicioAndamento.getSerie());
        dados.put("metrica", exercicioAndamento.getMetrica());
        dados.put("quantidadeMetrica", exercicioAndamento.getQuantidadeMetrica());
        dados.put("quantidadeRealizada", exercicioAndamento.getQuantidadeRealizada());
        dados.put("quantidadeObjetivo", exercicioAndamento.getQuantidadeObjetivo());
        dados.put("numeroDias", exercicioAndamento.getNumeroDias());
        dados.put("diaInicio", exercicioAndamento.getDataInicio().get(Calendar.DAY_OF_MONTH));
        dados.put("mesInicio", exercicioAndamento.getDataInicio().get(Calendar.MONTH));
        dados.put("anoInicio", exercicioAndamento.getDataInicio().get(Calendar.YEAR));

        escrita.insert("ExercicioAndamento", null, dados);

        lembreteDAO.InserirLembrete(exercicioAndamento.getLembrete());
    }

    public List<ExercicioAndamento> ListarExercicios(){
        List<ExercicioAndamento> exercicios = new ArrayList<>();
        ContentValues dados;

        String sql = "SELECT * FROM ExercicioAndamento";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
        int indicenome = cursor.getColumnIndex("nome");
        int indicedescricao = cursor.getColumnIndex("descricao");
        int indiceserie = cursor.getColumnIndex("serie");
        int indicemetrica = cursor.getColumnIndex("metrica");
        int indicequantidadeMetrica = cursor.getColumnIndex("quantidadeMetrica");
        int indicequantidadeRealizada = cursor.getColumnIndex("quantidadeRealizada");
        int indicequantidadeObjetivo = cursor.getColumnIndex("quantidadeObjetivo");
        int indicenumeroDias = cursor.getColumnIndex("numeroDias");
        int indicediaInicio = cursor.getColumnIndex("diaInicio");
        int indicemesInicio = cursor.getColumnIndex("mesInicio");
        int indiceanoInicio = cursor.getColumnIndex("anoInicio");

        cursor.moveToFirst();
        Log.i("Banco", String.valueOf(exercicios.size()));

        while(!cursor.isAfterLast()){
            int codigo = cursor.getInt(indicecodigo);
            String nome = cursor.getString(indicenome);
            String descricao = cursor.getString(indicedescricao);
            int serie = cursor.getInt(indiceserie);
            String metrica = cursor.getString(indicemetrica);
            int quantidadeMetrica = cursor.getInt(indicequantidadeMetrica);
            int quantidadeRealizada = cursor.getInt(indicequantidadeRealizada);
            int quantidadeObjetivo = cursor.getInt(indicequantidadeObjetivo);
            int numeroDias = cursor.getInt(indicenumeroDias);
            int diaInicio = cursor.getInt(indicediaInicio);
            int mesInicio = cursor.getInt(indicemesInicio);
            int anoInicio = cursor.getInt(indiceanoInicio);

            Log.i("Banco", "Found 1");
            Lembrete lembrete = lembreteDAO.BuscarLembrete(nome);

            Log.i("Banco", "Found 2");

            Calendar calendar = Calendar.getInstance();
            calendar.set(anoInicio, mesInicio, diaInicio);

            ExercicioAndamento exercicio = new ExercicioAndamento(codigo, nome, descricao, serie, metrica, quantidadeMetrica, quantidadeRealizada, quantidadeObjetivo, numeroDias, calendar, lembrete);
            exercicios.add(exercicio);

            Log.i("Banco", "Found 3");
            cursor.moveToNext();

            Log.i("Banco", "Found 4");
        }
        Log.i("Banco", String.valueOf(exercicios.size()));
        return exercicios;
    }

    public ExercicioAndamento BuscarExercicio(String nomeExercicio){
        ExercicioAndamento exercicio;
        ContentValues dados;

        String sql = "SELECT * FROM ExercicioAndamento where nome = \"" + nomeExercicio + "\";";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
        int indicenome = cursor.getColumnIndex("nome");
        int indicedescricao = cursor.getColumnIndex("descricao");
        int indiceserie = cursor.getColumnIndex("serie");
        int indicemetrica = cursor.getColumnIndex("metrica");
        int indicequantidadeMetrica = cursor.getColumnIndex("quantidadeMetrica");
        int indicequantidadeRealizada = cursor.getColumnIndex("quantidadeRealizada");
        int indicequantidadeObjetivo = cursor.getColumnIndex("quantidadeObjetivo");
        int indicenumeroDias = cursor.getColumnIndex("numeroDias");
        int indicediaInicio = cursor.getColumnIndex("diaInicio");
        int indicemesInicio = cursor.getColumnIndex("mesInicio");
        int indiceanoInicio = cursor.getColumnIndex("anoInicio");

        cursor.moveToFirst();

        int codigo = cursor.getInt(indicecodigo);
        String nome = cursor.getString(indicenome);
        String descricao = cursor.getString(indicedescricao);
        int serie = cursor.getInt(indiceserie);
        String metrica = cursor.getString(indicemetrica);
        int quantidadeMetrica = cursor.getInt(indicequantidadeMetrica);
        int quantidadeRealizada = cursor.getInt(indicequantidadeRealizada);
        int quantidadeObjetivo = cursor.getInt(indicequantidadeObjetivo);
        int numeroDias = cursor.getInt(indicenumeroDias);
        int diaInicio = cursor.getInt(indicediaInicio);
        int mesInicio = cursor.getInt(indicemesInicio);
        int anoInicio = cursor.getInt(indiceanoInicio);

        Lembrete lembrete = lembreteDAO.BuscarLembrete(nome);

        Calendar calendar = Calendar.getInstance();
        calendar.set(anoInicio, mesInicio, diaInicio);

        exercicio = new ExercicioAndamento(codigo, nome, descricao, serie, metrica, quantidadeMetrica, quantidadeRealizada, quantidadeObjetivo, numeroDias, calendar, lembrete);

        return exercicio;
    }

    public void AtualizarExercicio(ExercicioAndamento exercicioAndamento){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nome", exercicioAndamento.getNome());
        dados.put("descricao", exercicioAndamento.getDescricao());
        dados.put("serie", exercicioAndamento.getSerie());
        dados.put("metrica", exercicioAndamento.getMetrica());
        dados.put("quantidadeMetrica", exercicioAndamento.getQuantidadeMetrica());
        dados.put("quantidadeRealizada", exercicioAndamento.getQuantidadeRealizada());
        dados.put("quantidadeObjetivo", exercicioAndamento.getQuantidadeObjetivo());
        dados.put("numeroDias", exercicioAndamento.getNumeroDias());
        dados.put("diaInicio", exercicioAndamento.getDataInicio().get(Calendar.DAY_OF_MONTH));
        dados.put("mesInicio", exercicioAndamento.getDataInicio().get(Calendar.MONTH));
        dados.put("anoInicio", exercicioAndamento.getDataInicio().get(Calendar.YEAR));

        String[] cod = {String.valueOf(exercicioAndamento.getCodigo())};
        escrita.update("ExercicioAndamento", dados, "codigo = ?", cod);
    }

    public void ExcluirExercicio(int codigoExercicio){
        String[] cod = {String.valueOf(codigoExercicio)};
        escrita.delete("ExercicioAndamento", "codigo = ?", cod);
    }

}

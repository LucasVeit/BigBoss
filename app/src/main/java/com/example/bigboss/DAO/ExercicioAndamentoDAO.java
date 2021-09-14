package com.example.bigboss.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bigboss.Utils.ConnectSQLite;
import com.example.bigboss.Utils.CreateSQLite;
import com.example.bigboss.Model.ExercicioAndamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExercicioAndamentoDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;
    private CreateSQLite banco;
    private ConnectSQLite connectionBanco;

    public ExercicioAndamentoDAO(Context ctx){
        connectionBanco = ConnectSQLite.getInstance(ctx);
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
        dados.put("hora", exercicioAndamento.getHora());
        dados.put("minuto", exercicioAndamento.getMinuto());
        dados.put("domingo", Unbooleanizer(exercicioAndamento.isDomingo()));
        dados.put("segunda", Unbooleanizer(exercicioAndamento.isSegunda()));
        dados.put("terca", Unbooleanizer(exercicioAndamento.isTerca()));
        dados.put("quarta", Unbooleanizer(exercicioAndamento.isQuarta()));
        dados.put("quinta", Unbooleanizer(exercicioAndamento.isQuinta()));
        dados.put("sexta", Unbooleanizer(exercicioAndamento.isSexta()));
        dados.put("sabado", Unbooleanizer(exercicioAndamento.isSabado()));

        escrita.insert("ExercicioAndamento", null, dados);
    }
    public int Unbooleanizer(boolean value){
        if(value)
            return 1;
        else
            return 0;

    }

    public Boolean Booleanizer(int value){
        if(value == 1)
            return true;
        else
            return false;
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
        int indicenomeExercicio = cursor.getColumnIndex("nomeExercicio");
        int indicehora = cursor.getColumnIndex("hora");
        int indiceminuto = cursor.getColumnIndex("minuto");
        int indicedomingo = cursor.getColumnIndex("domingo");
        int indicesegunda = cursor.getColumnIndex("segunda");
        int indiceterca = cursor.getColumnIndex("terca");
        int indicequarta = cursor.getColumnIndex("quarta");
        int indicequinta = cursor.getColumnIndex("quinta");
        int indicesexta = cursor.getColumnIndex("sexta");
        int indicesabado = cursor.getColumnIndex("sabado");

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
            int hora = cursor.getInt(indicehora);
            int minuto = cursor.getInt(indiceminuto);
            boolean domingo = Booleanizer(cursor.getInt(indicedomingo));
            boolean segunda = Booleanizer(cursor.getInt(indicesegunda));
            boolean terca = Booleanizer(cursor.getInt(indiceterca));
            boolean quarta = Booleanizer(cursor.getInt(indicequarta));
            boolean quinta = Booleanizer(cursor.getInt(indicequinta));
            boolean sexta = Booleanizer(cursor.getInt(indicesexta));
            boolean sabado = Booleanizer(cursor.getInt(indicesabado));

            Calendar calendar = Calendar.getInstance();
            calendar.set(anoInicio, mesInicio, diaInicio);

            ExercicioAndamento exercicio = new ExercicioAndamento(codigo, nome, descricao, serie, metrica, quantidadeMetrica, quantidadeRealizada, quantidadeObjetivo, numeroDias, calendar, hora, minuto, domingo, segunda, terca, quarta, quinta, sexta, sabado);
            exercicios.add(exercicio);
            cursor.moveToNext();
            Log.i("Codigo exercício", String.valueOf(exercicio.getCodigo()));
        }

        Log.i("Numero exercícios", String.valueOf(exercicios.size()));
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
        int indicehora = cursor.getColumnIndex("hora");
        int indiceminuto = cursor.getColumnIndex("minuto");
        int indicedomingo = cursor.getColumnIndex("domingo");
        int indicesegunda = cursor.getColumnIndex("segunda");
        int indiceterca = cursor.getColumnIndex("terca");
        int indicequarta = cursor.getColumnIndex("quarta");
        int indicequinta = cursor.getColumnIndex("quinta");
        int indicesexta = cursor.getColumnIndex("sexta");
        int indicesabado = cursor.getColumnIndex("sabado");

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
        int anoInicio = cursor.getInt(indiceanoInicio);;
        int hora = cursor.getInt(indicehora);
        int minuto = cursor.getInt(indiceminuto);
        boolean domingo = Booleanizer(cursor.getInt(indicedomingo));
        boolean segunda = Booleanizer(cursor.getInt(indicesegunda));
        boolean terca = Booleanizer(cursor.getInt(indiceterca));
        boolean quarta = Booleanizer(cursor.getInt(indicequarta));
        boolean quinta = Booleanizer(cursor.getInt(indicequinta));
        boolean sexta = Booleanizer(cursor.getInt(indicesexta));
        boolean sabado = Booleanizer(cursor.getInt(indicesabado));

        Calendar calendar = Calendar.getInstance();
        calendar.set(anoInicio, mesInicio, diaInicio);

        exercicio = new ExercicioAndamento(codigo, nome, descricao, serie, metrica, quantidadeMetrica, quantidadeRealizada, quantidadeObjetivo, numeroDias, calendar, hora, minuto, domingo, segunda, terca, quarta, quinta, sexta, sabado);

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
        dados.put("hora", exercicioAndamento.getHora());
        dados.put("minuto", exercicioAndamento.getMinuto());
        dados.put("domingo", Unbooleanizer(exercicioAndamento.isDomingo()));
        dados.put("segunda", Unbooleanizer(exercicioAndamento.isSegunda()));
        dados.put("terca", Unbooleanizer(exercicioAndamento.isTerca()));
        dados.put("quarta", Unbooleanizer(exercicioAndamento.isQuarta()));
        dados.put("quinta", Unbooleanizer(exercicioAndamento.isQuinta()));
        dados.put("sexta", Unbooleanizer(exercicioAndamento.isSexta()));
        dados.put("sabado", Unbooleanizer(exercicioAndamento.isSabado()));
        String[] cod = {String.valueOf(exercicioAndamento.getCodigo())};
        escrita.update("ExercicioAndamento", dados, "codigo = ?", cod);
    }

    public void ExcluirExercicio(int codigoExercicio){
        String[] cod = {String.valueOf(codigoExercicio)};
        escrita.delete("ExercicioAndamento", "codigo = ?", cod);
    }

}

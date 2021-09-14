package com.example.bigboss.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bigboss.Utils.ConnectSQLite;
import com.example.bigboss.Utils.CreateSQLite;
import com.example.bigboss.model.Lembrete;
import com.example.bigboss.model.Usuario;

import java.util.ArrayList;

public class LembreteDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;
    private CreateSQLite banco;
    private ConnectSQLite connectionBanco;

    public LembreteDAO(Context ctx){
        connectionBanco = ConnectSQLite.getInstance(ctx);
        escrita = connectionBanco.getDatabase(true);
        leitura = connectionBanco.getDatabase(false);
    }

    public void InserirLembrete(Lembrete lembrete){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nomeExercicio", lembrete.getNomeExercicio());
        dados.put("hora", lembrete.getHora());
        dados.put("minuto", lembrete.getMinuto());
        dados.put("domingo", Unbooleanizer(lembrete.isDomingo()));
        dados.put("segunda", Unbooleanizer(lembrete.isSegunda()));
        dados.put("terca", Unbooleanizer(lembrete.isTerca()));
        dados.put("quarta", Unbooleanizer(lembrete.isQuarta()));
        dados.put("quinta", Unbooleanizer(lembrete.isQuinta()));
        dados.put("sexta", Unbooleanizer(lembrete.isSexta()));
        dados.put("sabado", Unbooleanizer(lembrete.isSabado()));

        escrita.insert("Lembrete", null, dados);

    }

    public ArrayList<Lembrete> ListarLembretes(){
        ArrayList<Lembrete> lembretes = new ArrayList<>();
        ContentValues dados;

        String sql = "SELECT * FROM lembrete";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
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

        while(cursor != null){
            int codigo = cursor.getInt(indicecodigo);
            String nomeExercicio = cursor.getString(indicenomeExercicio);
            int hora = cursor.getInt(indicehora);
            int minuto = cursor.getInt(indiceminuto);
            boolean domingo = Booleanizer(cursor.getInt(indicedomingo));
            boolean segunda = Booleanizer(cursor.getInt(indicesegunda));
            boolean terca = Booleanizer(cursor.getInt(indiceterca));
            boolean quarta = Booleanizer(cursor.getInt(indicequarta));
            boolean quinta = Booleanizer(cursor.getInt(indicequinta));
            boolean sexta = Booleanizer(cursor.getInt(indicesexta));
            boolean sabado = Booleanizer(cursor.getInt(indicesabado));

            Lembrete lembrete = new Lembrete(codigo, nomeExercicio, hora, minuto, domingo, segunda, terca, quarta, quinta, sexta, sabado);
            lembretes.add(lembrete);

            cursor.moveToNext();
        }

        return  lembretes;
    }

    public Lembrete BuscarLembrete(String exercicio){
        Lembrete lembrete;

        String sql = "select * from lembrete where nomeExercicio = \"" + exercicio + "\";";
        Cursor cursor = leitura.rawQuery(sql, null);

        int indicecodigo = cursor.getColumnIndex("codigo");
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

        int codigo = cursor.getInt(indicecodigo);
        String nomeExercicio = cursor.getString(indicenomeExercicio);
        int hora = cursor.getInt(indicehora);
        int minuto = cursor.getInt(indiceminuto);
        boolean domingo = Booleanizer(cursor.getInt(indicedomingo));
        boolean segunda = Booleanizer(cursor.getInt(indicesegunda));
        boolean terca = Booleanizer(cursor.getInt(indiceterca));
        boolean quarta = Booleanizer(cursor.getInt(indicequarta));
        boolean quinta = Booleanizer(cursor.getInt(indicequinta));
        boolean sexta = Booleanizer(cursor.getInt(indicesexta));
        boolean sabado = Booleanizer(cursor.getInt(indicesabado));

        lembrete = new Lembrete(codigo, nomeExercicio, hora, minuto, domingo, segunda, terca, quarta, quinta, sexta, sabado);

        return lembrete;
    }

    public void AtualizarLembrete(Lembrete lembrete){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nomeExercicio", lembrete.getNomeExercicio());
        dados.put("hora", lembrete.getHora());
        dados.put("minuto", lembrete.getMinuto());
        dados.put("domingo", Unbooleanizer(lembrete.isDomingo()));
        dados.put("segunda", Unbooleanizer(lembrete.isSegunda()));
        dados.put("terca", Unbooleanizer(lembrete.isTerca()));
        dados.put("quarta", Unbooleanizer(lembrete.isQuarta()));
        dados.put("quinta", Unbooleanizer(lembrete.isQuinta()));
        dados.put("sexta", Unbooleanizer(lembrete.isSexta()));
        dados.put("sabado", Unbooleanizer(lembrete.isSabado()));

        String[] cod = {String.valueOf(lembrete.getCodigo())};
        escrita.update("Lembrete", dados, "codigo = ?", cod);
    }

    public void ExcluirLembrete(int codigoLembrete){
        String[] cod = {String.valueOf(codigoLembrete)};
        escrita.delete("Lembrete", "codigo = ?", cod);
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
}

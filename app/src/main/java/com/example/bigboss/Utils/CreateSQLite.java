package com.example.bigboss.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CreateSQLite extends SQLiteOpenHelper {
    public CreateSQLite(Context ctx){
        super(ctx, "Big Boss", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String criarUsuario = "create table if not exists Usuario(" +
                "codigo integer primary key," +
                "nome text," +
                "divisao text," +
                "level integer," +
                "xp integer," +
                "imagem blob);";

        String criarExercicioAndamento = "create table if not exists ExercicioAndamento(" +
                "codigo integer primary key autoincrement," +
                "nome text," +
                "descricao text," +
                "serie integer," +
                "metrica text," +
                "quantidadeMetrica integer," +
                "quantidadeRealizada integer," +
                "quantidadeObjetivo integer," +
                "numeroDias integer," +
                "diaInicio integer," +
                "mesInicio integer," +
                "anoInicio integer);";

        String criarExercicioConcluido = "create table if not exists ExercicioConcluido(" +
                "codigo integer primary key autoincrement," +
                "nome text," +
                "serie integer," +
                "metrica text," +
                "quantidadeMetrica integer," +
                "quantidadeObjetivo integer);";

        String criarLembrete ="create table if not exists Lembrete(" +
                "codigo integer primary key autoincrement," +
                "nomeExercicio text," +
                "hora integer," +
                "minuto integer," +
                "domingo integer," +
                "segunda integer," +
                "terca integer," +
                "quarta integer," +
                "quinta integer," +
                "sexta integer," +
                "sabado integer," +
                "foreign key(nomeExercicio) references exercicioAndamento(nome));";

        try{
            db.execSQL(criarUsuario);
            db.execSQL(criarLembrete);
            db.execSQL(criarExercicioAndamento);
            db.execSQL(criarExercicioConcluido);

            Log.i("Info Banco","Sucesso ao criar o banco");
        }catch(Exception e){
            Log.i("Info Banco", "Erro ao criar o banco");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova){
        String excluirUsuario = "drop table if exists usuario;";
        String excluirExercicioAndamento = "drop table if exists exercicioAndamento";
        String excluirExercicioConcluido = "drop table if exists exercicioConcluido";
        String excluirLembrete = "drop table if exists lembrete";

        try{
            db.execSQL(excluirUsuario);
            db.execSQL(excluirLembrete);
            db.execSQL(excluirExercicioAndamento);
            db.execSQL(excluirExercicioConcluido);
            onCreate(db);
            Log.i("Info Banco", "Sucesso ao atualizar o banco");
        }catch(Exception e){
            Log.i("Info Banco", "Erro ao atualizar o banco");
        }
    }

}

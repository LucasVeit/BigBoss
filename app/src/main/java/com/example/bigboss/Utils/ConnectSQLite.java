package com.example.bigboss.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConnectSQLite {
    private static ConnectSQLite connection;
    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;

    private ConnectSQLite(Context ctx){
        CreateSQLite sqlite = new CreateSQLite(ctx);
        escrita = sqlite.getWritableDatabase();
        leitura = sqlite.getReadableDatabase();
    }

    public static ConnectSQLite getInstance(Context ctx){
        if(connection == null)
            connection = new ConnectSQLite(ctx);
        return connection;
    }

    public SQLiteDatabase getDatabase(Boolean escrita){
        if(escrita)
            return this.escrita;
        else
            return this.leitura;
    }
}

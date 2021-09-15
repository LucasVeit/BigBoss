package com.example.bigboss.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bigboss.Utils.ConnectSQLite;
import com.example.bigboss.Utils.CreateSQLite;
import com.example.bigboss.Model.Usuario;

public class UsuarioDAO {
    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;
    private CreateSQLite banco;
    private ConnectSQLite connectionBanco;

    public UsuarioDAO(Context ctx){
        connectionBanco = ConnectSQLite.getInstance(ctx);
        escrita = connectionBanco.getDatabase(true);
        leitura = connectionBanco.getDatabase(false);
    }

    public Usuario buscarUsuario(){
        Usuario usuario = null;
        String consulta = "select * from usuario";
        Cursor cursor = leitura.rawQuery(consulta, null);

        int indiceCodigo = cursor.getColumnIndex("codigo");
        int indiceNome = cursor.getColumnIndex("nome");
        int indiceDivisao = cursor.getColumnIndex("divisao");
        int indiceLevel = cursor.getColumnIndex("level");
        int indiceXp = cursor.getColumnIndex("xp");
        int indiceImagem = cursor.getColumnIndex("imagem");

        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            int codigo = cursor.getInt(indiceCodigo);
            String nome = cursor.getString(indiceNome);
            String divisao = cursor.getString(indiceDivisao);
            int level = cursor.getInt(indiceLevel);
            int xp = cursor.getInt(indiceXp);
            byte[] imagem = cursor.getBlob(indiceImagem);

            usuario = new Usuario(codigo, nome, divisao, level, xp, imagem);
        }else{
            usuario = new Usuario(0, "", "", 0, 0, null);
        }
        return usuario;
    }

    public void CriarUsuario(Usuario usuario){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("codigo", usuario.getCodigo());
        dados.put("nome", usuario.getNome());
        dados.put("divisao", usuario.getDivisao());
        dados.put("level", usuario.getLevel());
        dados.put("xp", usuario.getXp());
        dados.put("imagem", usuario.getImagem());

        escrita.insert("Usuario", null, dados);

    }

    public void AtualizarUsuario(Usuario usuario){
        ContentValues dados;

        dados = new ContentValues();
        dados.put("nome", usuario.getNome());
        dados.put("divisao", usuario.getDivisao());
        dados.put("level", usuario.getLevel());
        dados.put("xp", usuario.getXp());
        dados.put("imagem", usuario.getImagem());

        String[] cod = {String.valueOf(usuario.getCodigo())};
        escrita.update("Usuario", dados, "codigo = ?", cod);

    }

    public void deletarUsuario(Usuario usuario){
        String[] cod = {String.valueOf(usuario.getCodigo())};
        escrita.delete("usuario", "codigo = ?", cod);
    }
}

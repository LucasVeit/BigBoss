package com.example.bigboss.model;

public class Usuario {
    private int codigo;
    private String nome;
    private String divisao;
    private int level;
    private int xp;
    private byte[] imagem;

    public Usuario() {
    }

    public Usuario(int codigo, String nome, String divisao, int level, int xp, byte[] imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.divisao = divisao;
        this.level = level;
        this.xp = xp;
        this.imagem = imagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}

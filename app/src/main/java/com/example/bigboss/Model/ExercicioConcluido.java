package com.example.bigboss.Model;

public class ExercicioConcluido {
    private int codigo;
    private String nome;
    private int serie;
    private String metrica;
    private int quantidadeMetrica;
    private int quantidadeObjetivo;

    public ExercicioConcluido() {
    }

    public ExercicioConcluido(int codigo, String nome, int serie, String metrica, int quantidadeMetrica, int quantidadeObjetivo) {
        this.codigo = codigo;
        this.nome = nome;
        this.serie = serie;
        this.metrica = metrica;
        this.quantidadeMetrica = quantidadeMetrica;
        this.quantidadeObjetivo = quantidadeObjetivo;
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

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    public int getQuantidadeMetrica() {
        return quantidadeMetrica;
    }

    public void setQuantidadeMetrica(int quantidadeMetrica) {
        this.quantidadeMetrica = quantidadeMetrica;
    }

    public int getQuantidadeObjetivo() {
        return quantidadeObjetivo;
    }

    public void setQuantidadeObjetivo(int quantidadeObjetivo) {
        this.quantidadeObjetivo = quantidadeObjetivo;
    }
}

package com.example.bigboss.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class ExercicioAndamento implements Parcelable {
    private int codigo;
    private String nome;
    private String descricao;
    private int serie;
    private String metrica;
    private int quantidadeMetrica;
    private int quantidadeRealizada;
    private int quantidadeObjetivo;
    private int numeroDias;
    private Calendar dataInicio;
    private Lembrete lembrete;

    protected ExercicioAndamento(Parcel in) {
        codigo = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        serie = in.readInt();
        metrica = in.readString();
        quantidadeMetrica = in.readInt();
        quantidadeRealizada = in.readInt();
        quantidadeObjetivo = in.readInt();
        numeroDias = in.readInt();
    }

    public static final Creator<ExercicioAndamento> CREATOR = new Creator<ExercicioAndamento>() {
        @Override
        public ExercicioAndamento createFromParcel(Parcel in) {
            return new ExercicioAndamento(in);
        }

        @Override
        public ExercicioAndamento[] newArray(int size) {
            return new ExercicioAndamento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(codigo);
        parcel.writeString(nome);
        parcel.writeString(descricao);
        parcel.writeInt(serie);
        parcel.writeString(metrica);
        parcel.writeInt(quantidadeMetrica);
        parcel.writeInt(quantidadeRealizada);
        parcel.writeInt(quantidadeObjetivo);
        parcel.writeInt(numeroDias);
    }

    public ExercicioAndamento() {
    }

    public ExercicioAndamento(int codigo, String nome, String descricao, int serie, String metrica, int quantidadeMetrica, int quantidadeRealizada, int quantidadeObjetivo, int numeroDias, Calendar dataInicio, Lembrete lembrete) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.serie = serie;
        this.metrica = metrica;
        this.quantidadeMetrica = quantidadeMetrica;
        this.quantidadeRealizada = quantidadeRealizada;
        this.quantidadeObjetivo = quantidadeObjetivo;
        this.numeroDias = numeroDias;
        this.dataInicio = dataInicio;
        this.lembrete = lembrete;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public int getQuantidadeRealizada() {
        return quantidadeRealizada;
    }

    public void setQuantidadeRealizada(int quantidadeRealizada) {
        this.quantidadeRealizada = quantidadeRealizada;
    }

    public int getQuantidadeObjetivo() {
        return quantidadeObjetivo;
    }

    public void setQuantidadeObjetivo(int quantidadeObjetivo) {
        this.quantidadeObjetivo = quantidadeObjetivo;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Lembrete getLembrete() {
        return lembrete;
    }

    public void setLembrete(Lembrete lembrete) {
        this.lembrete = lembrete;
    }

}

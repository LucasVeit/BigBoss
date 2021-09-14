package com.example.bigboss.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
    private int hora;
    private int minuto;
    private boolean domingo;
    private boolean segunda;
    private boolean terca;
    private boolean quarta;
    private boolean quinta;
    private boolean sexta;
    private boolean sabado;

    public ExercicioAndamento() {
    }

    public ExercicioAndamento(int codigo, String nome, String descricao, int serie, String metrica, int quantidadeMetrica, int quantidadeRealizada, int quantidadeObjetivo, int numeroDias, Calendar dataInicio, int hora, int minuto, boolean domingo, boolean segunda, boolean terca, boolean quarta, boolean quinta, boolean sexta, boolean sabado) {
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
        this.hora = hora;
        this.minuto = minuto;
        this.domingo = domingo;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
    }

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
        hora = in.readInt();
        minuto = in.readInt();
        domingo = in.readByte() != 0;
        segunda = in.readByte() != 0;
        terca = in.readByte() != 0;
        quarta = in.readByte() != 0;
        quinta = in.readByte() != 0;
        sexta = in.readByte() != 0;
        sabado = in.readByte() != 0;
        Long milli = in.readLong();
        String timeZone = in.readString();
        dataInicio = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
        dataInicio.setTimeInMillis(milli);
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

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean isSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean isTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean isQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean isQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean isSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

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
        parcel.writeInt(hora);
        parcel.writeInt(minuto);
        parcel.writeByte((byte) (domingo ? 1 : 0));
        parcel.writeByte((byte) (segunda ? 1 : 0));
        parcel.writeByte((byte) (terca ? 1 : 0));
        parcel.writeByte((byte) (quarta ? 1 : 0));
        parcel.writeByte((byte) (quinta ? 1 : 0));
        parcel.writeByte((byte) (sexta ? 1 : 0));
        parcel.writeByte((byte) (sabado ? 1 : 0));
        parcel.writeLong(dataInicio.getTimeInMillis());
        parcel.writeString(dataInicio.getTimeZone().getID());
    }
}

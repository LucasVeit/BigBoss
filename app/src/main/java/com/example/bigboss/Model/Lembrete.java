package com.example.bigboss.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Lembrete implements Parcelable {
    private int codigo;
    private String nomeExercicio;
    private int hora;
    private int minuto;
    private boolean domingo;
    private boolean segunda;
    private boolean terca;
    private boolean quarta;
    private boolean quinta;
    private boolean sexta;
    private boolean sabado;

    public Lembrete() {
    }

    public Lembrete(int codigo, String nomeExercicio, int hora, int minuto, boolean domingo, boolean segunda, boolean terca, boolean quarta, boolean quinta, boolean sexta, boolean sabado) {
        this.codigo = codigo;
        this.nomeExercicio = nomeExercicio;
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

    protected Lembrete(Parcel in) {
        codigo = in.readInt();
        nomeExercicio = in.readString();
        hora = in.readInt();
        minuto = in.readInt();
        domingo = in.readByte() != 0;
        segunda = in.readByte() != 0;
        terca = in.readByte() != 0;
        quarta = in.readByte() != 0;
        quinta = in.readByte() != 0;
        sexta = in.readByte() != 0;
        sabado = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(codigo);
        parcel.writeString(nomeExercicio);
        parcel.writeInt(hora);
        parcel.writeInt(minuto);
        parcel.writeByte((byte) (domingo ? 1 : 0));
        parcel.writeByte((byte) (segunda ? 1 : 0));
        parcel.writeByte((byte) (terca ? 1 : 0));
        parcel.writeByte((byte) (quarta ? 1 : 0));
        parcel.writeByte((byte) (quinta ? 1 : 0));
        parcel.writeByte((byte) (sexta ? 1 : 0));
        parcel.writeByte((byte) (sabado ? 1 : 0));
    }

    public static final Creator<Lembrete> CREATOR = new Creator<Lembrete>() {
        @Override
        public Lembrete createFromParcel(Parcel in) {
            return new Lembrete(in);
        }

        @Override
        public Lembrete[] newArray(int size) {
            return new Lembrete[size];
        }
    };

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
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
}

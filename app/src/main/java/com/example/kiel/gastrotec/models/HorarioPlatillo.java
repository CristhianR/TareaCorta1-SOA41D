package com.example.kiel.gastrotec.models;

public class HorarioPlatillo {
    // Atributos de la clase
    private int mIdHorarioComida;
    private String mNombreHorario;
    private String mHorarioComida;
    private int mIdRestaurante;

    // <------------------------------------- CONSTRUCTORES ------------------------------------->
    public HorarioPlatillo(int mIdHorarioComida, String mNombreHorario, String mHorarioComida, int mIdRestaurante) {
        this.mIdHorarioComida = mIdHorarioComida;
        this.mNombreHorario = mNombreHorario;
        this.mHorarioComida = mHorarioComida;
        this.mIdRestaurante = mIdRestaurante;
    }

    public HorarioPlatillo(String mNombreHorario, String mHorarioComida, int mIdRestaurante) {
        this.mNombreHorario = mNombreHorario;
        this.mHorarioComida = mHorarioComida;
        this.mIdRestaurante = mIdRestaurante;
    }

    public HorarioPlatillo(){
    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdHorarioComida(int mIdHorarioComida) {
        this.mIdHorarioComida = mIdHorarioComida;
    }

    public void setmNombreHorario(String mNombreHorario) {
        this.mNombreHorario = mNombreHorario;
    }

    public void setmHorarioComida(String mHorarioComida) {
        this.mHorarioComida = mHorarioComida;
    }

    public void setmIdRestaurante(int mIdRestaurante) {
        this.mIdRestaurante = mIdRestaurante;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdHorarioComida() {
        return mIdHorarioComida;
    }

    public String getmNombreHorario() {
        return mNombreHorario;
    }

    public String getmHorarioComida() {
        return mHorarioComida;
    }

    public int getmIdRestaurante() {
        return mIdRestaurante;
    }
}

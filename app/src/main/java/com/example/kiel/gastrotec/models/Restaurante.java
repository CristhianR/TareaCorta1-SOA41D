package com.example.kiel.gastrotec.models;

public class Restaurante {
    // Atributos de de la clase
    private int mIdRest;
    private String mNomRest;
    private String mLocRest;
    private String mHorarioRest;

    // <------------------------------------- CONSTRUCTORES ------------------------------------->
    public Restaurante(int mIdRest, String mNomRest, String mLocRest, String mHorarioRest) {
        this.mIdRest = mIdRest;
        this.mNomRest = mNomRest;
        this.mLocRest = mLocRest;
        this.mHorarioRest = mHorarioRest;
    }

    public Restaurante(String mNomRest, String mLocRest, String mHorarioRest){
        this.mNomRest = mNomRest;
        this.mLocRest = mLocRest;
        this.mHorarioRest = mHorarioRest;
    }

    public Restaurante(){

    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdRest(int mIdRest) {
        this.mIdRest = mIdRest;
    }

    public void setmNomRest(String mNomRest) {
        this.mNomRest = mNomRest;
    }

    public void setmLocRest(String mLocRest) {
        this.mLocRest = mLocRest;
    }

    public void setmHorarioRest(String mHorarioRest) {
        this.mHorarioRest = mHorarioRest;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdRest() {
        return mIdRest;
    }

    public String getmNomRest() {
        return mNomRest;
    }

    public String getmLocRest() {
        return mLocRest;
    }

    public String getmHorarioRest() {
        return mHorarioRest;
    }
}

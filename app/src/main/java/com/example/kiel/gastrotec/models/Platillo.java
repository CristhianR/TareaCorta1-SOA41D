package com.example.kiel.gastrotec.models;

import java.io.StringReader;

public class Platillo {
    // Atributos de la clase
    private int mIdPlatillo;
    private String mNombrePlatillo;
    private int mVotos;
    private int mIdRestaurante;

    // <------------------------------------- CONSTRUCTORES ------------------------------------->
    public Platillo(int mIdPlatillo, String mNombrePlatillo, int mVotos, int mIdRestaurante) {
        this.mIdPlatillo = mIdPlatillo;
        this.mNombrePlatillo = mNombrePlatillo;
        this.mVotos = mVotos;
        this.mIdRestaurante = mIdRestaurante;
    }

    public Platillo(String mNombrePlatillo, int mVotos, int mIdRestaurante) {
        this.mNombrePlatillo = mNombrePlatillo;
        this.mVotos = mVotos;
        this.mIdRestaurante = mIdRestaurante;
    }

    public Platillo(){

    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdPlatillo(int mIdPlatillo) {
        this.mIdPlatillo = mIdPlatillo;
    }

    public void setmNombrePlatillo(String mNombrePlatillo) {
        this.mNombrePlatillo = mNombrePlatillo;
    }

    public void setmVotos(int mVotos) {
        this.mVotos = mVotos;
    }

    public void setmIdRestaurante(int mIdRestaurante) {
        this.mIdRestaurante = mIdRestaurante;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdPlatillo() {
        return mIdPlatillo;
    }

    public String getmNombrePlatillo() {
        return mNombrePlatillo;
    }

    public int getmVotos() {
        return mVotos;
    }

    public int getmIdRestaurante() {
        return mIdRestaurante;
    }
}

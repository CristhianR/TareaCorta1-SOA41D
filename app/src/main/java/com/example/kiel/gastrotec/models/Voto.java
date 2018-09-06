package com.example.kiel.gastrotec.models;

public class Voto {
    // Atributos de la clase
    private int mIdVoto;
    private int mIdCliente;
    private int mIdPlatillo;
    private int mValor;

    // <------------------------------------- CONSTRUCTORES ------------------------------------->


    public Voto(int mIdVoto, int mIdCliente, int mIdPlatillo, int mValor) {
        this.mIdVoto = mIdVoto;
        this.mIdCliente = mIdCliente;
        this.mIdPlatillo = mIdPlatillo;
        this.mValor = mValor;
    }

    public Voto(int mIdCliente, int mIdPlatillo, int mValor) {
        this.mIdCliente = mIdCliente;
        this.mIdPlatillo = mIdPlatillo;
        this.mValor = mValor;
    }

    public Voto(){
    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdVoto(int mIdVoto) {
        this.mIdVoto = mIdVoto;
    }

    public void setmIdCliente(int mIdCliente) {
        this.mIdCliente = mIdCliente;
    }

    public void setmIdPlatillo(int mIdPlatillo) {
        this.mIdPlatillo = mIdPlatillo;
    }

    public void setmValor(int mValor) {
        this.mValor = mValor;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdVoto() {
        return mIdVoto;
    }

    public int getmIdCliente() {
        return mIdCliente;
    }

    public int getmIdPlatillo() {
        return mIdPlatillo;
    }

    public int getmValor() {
        return mValor;
    }
}

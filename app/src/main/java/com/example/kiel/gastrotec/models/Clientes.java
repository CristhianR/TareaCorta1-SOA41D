package com.example.kiel.gastrotec.models;

public class Clientes {
    // Atributos de la clase
    private int mIdCliente;
    private String mNomCliente;
    private String mApe1;
    private String mApe2;
    private String mCarrera;
    private String mEmail;
    private String mPassword;

    // Constructores
    public Clientes(int mIdCliente, String mNomCliente, String mApe1, String mApe2, String mCarrera, String mEmail, String mPassword) {
        this.mIdCliente = mIdCliente;
        this.mNomCliente = mNomCliente;
        this.mApe1 = mApe1;
        this.mApe2 = mApe2;
        this.mCarrera = mCarrera;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    public Clientes(){

    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdCliente(int mIdCliente) {
        this.mIdCliente = mIdCliente;
    }

    public void setmNomCliente(String mNomCliente) {
        this.mNomCliente = mNomCliente;
    }

    public void setmApe1(String mApe1) {
        this.mApe1 = mApe1;
    }

    public void setmApe2(String mApe2) {
        this.mApe2 = mApe2;
    }

    public void setmCarrera(String mCarrera) {
        this.mCarrera = mCarrera;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdCliente() {
        return mIdCliente;
    }

    public String getmNomCliente() {
        return mNomCliente;
    }

    public String getmApe1() {
        return mApe1;
    }

    public String getmApe2() {
        return mApe2;
    }

    public String getmCarrera() {
        return mCarrera;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }
}

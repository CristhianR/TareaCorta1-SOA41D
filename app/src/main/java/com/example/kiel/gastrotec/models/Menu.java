package com.example.kiel.gastrotec.models;

public class Menu {
    // Atributos de la clase
    private int mIdMenu;
    private int mIdPlatillo;
    private  int mIdHorario;

    // <------------------------------------- CONSTRUCTORES ------------------------------------->
    public Menu(int mIdMenu, int mIdPlatillo, int mIdHorario) {
        this.mIdMenu = mIdMenu;
        this.mIdPlatillo = mIdPlatillo;
        this.mIdHorario = mIdHorario;
    }

    public Menu(int mIdPlatillo, int mIdHorario) {
        this.mIdPlatillo = mIdPlatillo;
        this.mIdHorario = mIdHorario;
    }

    public Menu(){
    }

    // <------------------------------------- SETTERS ------------------------------------->
    public void setmIdMenu(int mIdMenu) {
        this.mIdMenu = mIdMenu;
    }

    public void setmIdPlatillo(int mIdPlatillo) {
        this.mIdPlatillo = mIdPlatillo;
    }

    public void setmIdHorario(int mIdHorario) {
        this.mIdHorario = mIdHorario;
    }

    // <------------------------------------- GETTERS ------------------------------------->
    public int getmIdMenu() {
        return mIdMenu;
    }

    public int getmIdPlatillo() {
        return mIdPlatillo;
    }

    public int getmIdHorario() {
        return mIdHorario;
    }
}

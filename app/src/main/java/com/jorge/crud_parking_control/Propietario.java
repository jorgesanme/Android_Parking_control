package com.jorge.crud_parking_control;

import java.io.Serializable;

public class Propietario implements Serializable {
    private int _id;
    private String nombre;
    private String apellidos;
    private String vehiculo;
    private String matricula;
    private int plaza;
    private String mando;
    private int isActivo;

    //contructor de la clase objeto Propietario

    public Propietario(int _id, String nombre, String apellidos, String vehiculo, String matricula,int plaza, String mando, Integer isActivo) { //String vehiculo, String matricula, int plaza, String mando, Boolean isActivo
        this._id = _id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.vehiculo = vehiculo;
        this.matricula = matricula;
        this.plaza = plaza;
        this.mando = mando;
        this.isActivo = isActivo;
    }

    public Propietario(int _id,  String nombre,  String apellidos) {
        this._id = _id;
        this.nombre = nombre;
        this.apellidos = apellidos;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPlaza() {
        return plaza;
    }

    public void setPlaza(int plaza) {
        this.plaza = plaza;
    }

    public String getMando() {
        return mando;
    }

    public void setMando(String mando) {
        this.mando = mando;
    }

    public int getIsActivo() {
        return isActivo;
    }

    public void setIsActivo(Integer isActivo) {
        this.isActivo = isActivo;
    }
}

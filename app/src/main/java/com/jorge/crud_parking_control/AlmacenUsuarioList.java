package com.jorge.crud_parking_control;


import java.util.ArrayList;

public class AlmacenUsuarioList implements AlmacenUsuarios {
    private ArrayList<Propietario> usuarios;

    public AlmacenUsuarioList() {
        usuarios = new ArrayList<Propietario>();
/**
        usuarios.add(new Propietario(0, cursor.getString(1), cursor.getString(2), cursor.getString(3), "Pepito", cursor.getInt(5), "Domingez", cursor.getInt(7)));
        usuarios.add(new Propietario(1, cursor.getString(1), cursor.getString(2), cursor.getString(3), "Pedro", cursor.getInt(5), "Martinez", cursor.getInt(7)));
        usuarios.add(new Propietario(1, cursor.getString(1), cursor.getString(2), cursor.getString(3), "Paco", cursor.getInt(5), "Pérez", cursor.getInt(7)));
*/
    }

    @Override
    public void guardarUsuarios(Integer id, String nombre, String apellidos, String vehiculo,
                                String matricula, int plaza, String mando, Integer activo) {
       /*
        usuarios.add(0, nombre + " " + apellidos + " "+vehiculo+ " "+matricula
                + " "+plaza+ " "+mando+ " "+activo);

        */
    }

    @Override
    public void modificarUsuarios(Integer id, String nombre, String apellidos, String vehiculo, String matricula, int plaza, String mando, Integer activo) {

    }

    @Override
    public ArrayList<Propietario> listaUsuarios(int cantidad) {
        return usuarios;
    }

    @Override
    public void borrarUsuario(int id) {

    }

    @Override
    public ArrayList<Propietario> unUsuario(int id) {
        return null;
    }


}
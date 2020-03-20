package com.jorge.crud_parking_control;

import java.util.ArrayList;

public interface AlmacenUsuarios {


        public void guardarUsuarios(Integer id, String nombre, String apellidos, String vehiculo,
                                    String matricula, int plaza, String mando, Integer activo);

        public void modificarUsuarios(Integer id, String nombre, String apellidos, String vehiculo,
                                    String matricula, int plaza, String mando, Integer activo);

        public ArrayList<Propietario> listaUsuarios(int cantidad);

        public void borrarUsuario(int id);

        public ArrayList<Propietario> unUsuario(int id);


}

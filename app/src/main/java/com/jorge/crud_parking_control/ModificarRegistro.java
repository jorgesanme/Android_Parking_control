package com.jorge.crud_parking_control;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.jorge.crud_parking_control.R.id.Insert_activo;

public class ModificarRegistro extends AppCompatActivity{

    protected EditText nombre,apellidos, vehiculo,matricula,mando,plaza;
    protected Switch activo;
    protected Button binsertar, bdescartar;
    private ArrayList<Propietario> lista;
    private int idIntem =0;
    private Object Context;
    String textoToast;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar);
        Context= getApplicationContext();

        //asignar variables a las vistas del layout crear.xml
        nombre= findViewById(R.id.editText_NOMBRE);
        apellidos= findViewById(R.id.editText_APELLIDOS);
        vehiculo= findViewById(R.id.editText_MARCA_MODELO);
        matricula= findViewById(R.id.editText_MATRICULA);
        mando= findViewById(R.id.editText_MANDO);
        plaza = findViewById(R.id.editText_Nº_PLAZA);
        activo= findViewById(Insert_activo);
        textoToast = getString(R.string.modificado);
        //activo= findViewById(R.id.editText_ACTIVO);

        // asignar boton a al boton del layout
        binsertar = findViewById(R.id.Insertar_Boton_insertar);
        bdescartar= findViewById(R.id.Insertar_Boton_descartar);

        // se deben asignar a los edit.text el valor recogido desde el pending
        // con un select * from usuarios

        Bundle extra= getIntent().getExtras();

        if (extra!=null){

            //traido desde eliminar
            idIntem= Integer.parseInt(extra.getString("id"));

            lista= MainActivity.usuarios.unUsuario(idIntem);
            nombre.setText(lista.get(0).getNombre());
            apellidos.setText(lista.get(0).getApellidos());
            vehiculo.setText(lista.get(0).getVehiculo());
            matricula.setText(lista.get(0).getMatricula());
            mando.setText(lista.get(0).getMando());
            plaza.setText(String.valueOf(lista.get(0).getPlaza()));
            activo.setChecked(lista.get(0).getIsActivo()>0);

        }
        // asignarle evento
        binsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nombreI="";
                String apellidosI="";
                String vehiculoI="";
                String matriculaI="";
                int plazaInt=0;
                String mandoI="";
                Integer activoI = 0;
                try {
                    nombreI= (nombre.getText().toString());
                    apellidosI= String.valueOf(apellidos.getText());
                    vehiculoI=String.valueOf(vehiculo.getText());
                    matriculaI=String.valueOf(matricula.getText());
                    plazaInt= Integer.parseInt(String.valueOf(plaza.getText()));
                    mandoI=String.valueOf(mando.getText());
                // se comprueba el estado del Switch ( activo/ desactivo)
                    activoI= activo.isChecked()?1:0;


                }catch (NumberFormatException n){
                }
                // necesito pasar el id del usuario desde un pendingIntent
                MainActivity.usuarios.modificarUsuarios(lista.get(0).get_id(),nombreI,apellidosI,vehiculoI,matriculaI,plazaInt,mandoI, activoI);
                Intent i = new Intent((android.content.Context) Context, usuarios.class);
                startActivity(i);
                Toast.makeText((android.content.Context) Context,textoToast, Toast.LENGTH_LONG).show();

            }
        });

        bdescartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((android.content.Context) Context, usuarios.class);
                startActivity(i);
            }
        });
    }

    //final de la clase
}
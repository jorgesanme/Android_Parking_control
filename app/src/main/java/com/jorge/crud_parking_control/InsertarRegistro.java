package com.jorge.crud_parking_control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import static com.jorge.crud_parking_control.R.id.Insert_activo;

public class InsertarRegistro extends AppCompatActivity{

    protected EditText nombre,apellidos, vehiculo,matricula,mando,plaza;
    protected Switch activo;
    protected Button binsertar,bdescartar;
    private Context context;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);
        context= getApplicationContext();
        //asignar variables a las vistas del layout crear.xml
        nombre= findViewById(R.id.editText_NOMBRE);
        apellidos= findViewById(R.id.editText_APELLIDOS);
        vehiculo= findViewById(R.id.editText_MARCA_MODELO);
        matricula= findViewById(R.id.editText_MATRICULA);
        mando= findViewById(R.id.editText_MANDO);
        plaza = findViewById(R.id.editText_Nº_PLAZA);
        activo= findViewById(Insert_activo);

        // asignar boton a al boton del layout
        binsertar = findViewById(R.id.Insertar_Boton_insertar);
        bdescartar= findViewById(R.id.Insertar_Boton_descartar);
        // asignarle evento
        binsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /**
                 * llamar al método para insertar en la BBDD local
                 */
                InsertarDatos();
            }
        });

        bdescartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((android.content.Context) context, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void InsertarDatos() {
        String nombreI="";
        String apellidosI="";
        String vehiculoI="";
        String matriculaI="";
        int plazaInt=0;
        String mandoI="";
        Integer activoI =0;
        try {
            //se obtiene los valores tecleados en las View
            nombreI= (nombre.getText().toString());
            apellidosI= String.valueOf(apellidos.getText());
            vehiculoI=String.valueOf(vehiculo.getText());
            matriculaI=String.valueOf(matricula.getText());
            plazaInt= Integer.parseInt(String.valueOf(plaza.getText()));
            mandoI=String.valueOf(mando.getText());
            if (activo.isChecked()){
                activoI = activo.isChecked()?1:0;
            }else{
                //activoI = Boolean.parseBoolean(activo.getTextOff().toString());
            }

        }catch (NumberFormatException n){

        }
        /**
         * se llama al metodo de la  base de datos desde MainActivity para insertar datos en la BBDD
         */
        MainActivity.usuarios.guardarUsuarios(null,nombreI,apellidosI,vehiculoI,matriculaI,plazaInt,mandoI, activoI);
        Intent i = new Intent((android.content.Context) context, usuarios.class);
        startActivity(i);
    }

    //final de la clase
}
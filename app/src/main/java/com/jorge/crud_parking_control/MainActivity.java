package com.jorge.crud_parking_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button bInsertar,  bMostrar, bSalir;
    //public AlmacenUsuarios usuarios= new AlmacenUsuariosSW_PHP_MiHosting(this);
    public static AlmacenUsuarios usuarios = new AlmacenUsuarioList();
    private SharedPreferences pref;
    private Context pasar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //preparar conexión internet
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
                permitNetwork().build());
        /** inicando preferencias para la selección de BBDD
         * actualmente solo en local, en futuras actualizaciónes se
         * implantará el servicio Hosting PHP
         */

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        // asignar las variables a los botones de las vistas layout
        bInsertar= findViewById(R.id.Main_boton_Insertar);
        bMostrar= findViewById(R.id.Main_boton_mostrar);
        bSalir= findViewById(R.id.Main_boton_Salir);

        // asignar un evento a cada boton
        bInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarInsertar(null);
            }
        });
        bMostrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarMostrar(null);
            }
        });
        bSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               salir();
            }
        });

        //base datos en hosting externo
        //usuarios = new AlmacenUsuariosSW_PHP_MiHosting(this);
        //lista interna
        //usuarios = new AlmacenUsuarioList();
        /** Se deja SQLite por defecto y unica opción por el momento */
        usuarios = new AlmacenUsuariosSQL(this);


    }

    static final int ACTIV_INSERTAR = 0;
    public void lanzarInsertar(View view) {
        Intent i = new Intent(this, InsertarRegistro.class);
        startActivityForResult(i, ACTIV_INSERTAR);
    }
    @Override protected void onActivityResult (int requestCode,
                                               int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * en un principio se penso en dos formas de almacenar los datos
         * una en local ( actualmente predeterminada)
         * otra en un Servicio en un hosting con PHP (este último daba fallos)
         */
        if (pref.getString("Almacenar","1").equals("1")){
            // base datos interna
            usuarios = new AlmacenUsuariosSQL(this);
        }else if(pref.getString("Almacenar","true").equals("2")) {
            //base datos en hosting externo
            //usuarios = new AlmacenUsuariosSW_PHP_MiHosting(this);

            usuarios = new AlmacenUsuariosSQL(this);
        }

    }


    //Método para lanzar mostrar el contenido de la bbdd
    public void lanzarMostrar(View view) {
        Intent i = new Intent(this, usuarios.class);
        startActivity(i);
    }
    // Método que lanza las preferencias
    static final int ACTIV_PREF = 1;
    public void lanzarPreferencias(View view) {
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivityForResult(i,ACTIV_PREF);

    }
    public void salir(){
        // se declara una intención para salir de la aplicación
        Intent intentSalir = new Intent(Intent.ACTION_MAIN);
        intentSalir.addCategory(Intent.CATEGORY_HOME);
        intentSalir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentSalir);

    }


    // FIN MainActivity
}

package com.jorge.crud_parking_control;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import android.util.Log;

public class AlmacenUsuariosSQL extends SQLiteOpenHelper  implements AlmacenUsuarios{

    private static final String TAG = "MyActivity";

    //constructor
    public AlmacenUsuariosSQL(Context context) {
        super(context, "parking", null, 1);
    }
    //Métodos de SQLiteOpenHelper
    @Override public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE usuarios (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "apellidos TEXT, " +
                    "vehiculo TEXT, " +
                    "matricula TEXT, " +
                    "plaza INTEGER, " +
                    "mando TEXT, " +
                    "activo INTEGER, " +
                    "fecha BIGINT)");

        }catch (SQLException e){
            Log.d(TAG,"eror en INSERCION DE DATOS ");
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
            if (oldVersion==1 && newVersion==2){
                onCreate(db);
                //Crea las nuevas tablas
                Cursor cursor = db.rawQuery("SELECT * "+
                        "FROM usuarios",null);
                //Recorre la tabla antigua
                while (cursor.moveToNext()) {
                    guardarUsuarios( cursor.getInt(0), cursor.getString(1),
                            cursor.getString(2),cursor.getString(3),cursor.getString(4),
                            cursor.getInt(5),cursor.getString(6),cursor.getInt(7));
                }
                //Crea los nuevos registros
                cursor.close();
                db.execSQL("DROP TABLE usuarios"); //Elimina tabla antigua
            }
    }
    //Métodos de AlmacenUsuarios
    @Override
    public void guardarUsuarios(Integer id, String nombre, String apellidos, String vehiculo, String matricula, int plaza, String mando, Integer activo) {

        SQLiteDatabase db = getWritableDatabase();

        String insertar="INSERT INTO usuarios (`_id`, `nombre`, `APELLIDOS`, " +
                "`VEHICULO`, `MATRICULA`, `PLAZA`, `MANDO`, `ACTIVO`) VALUES ( " +
                "null, '"+
                nombre+"', '"+apellidos+"','"+vehiculo+"'," +
                "'"+matricula+"', '"+plaza+"', '"+mando+"','"+activo+"' )";
        db.execSQL(insertar);
        db.close();
    }
    @Override

    public void borrarUsuario(int id){
        SQLiteDatabase db = getReadableDatabase();

        //"DELETE FROM Usuarios WHERE codigo=6 "
        //Eliminamos el registro del usuario '6'
        //db.delete("Usuarios", "codigo=6", null);
        db.delete("usuarios","_id="+id,null);
        db.close();

    }

    @Override
    public ArrayList<Propietario> unUsuario(int id) {
        ArrayList<Propietario> result = new ArrayList<Propietario>();
        SQLiteDatabase db = getReadableDatabase();
        String[] CAMPOS = {"_id","nombre","apellidos","vehiculo","matricula","plaza","mando","activo"};
        Cursor cursor=db.query("usuarios", CAMPOS, "_id="+id, null,
                null, null, null, null);

        while (cursor.moveToNext()){
            result.add(new Propietario(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getInt(5),
                    cursor.getString(6),cursor.getInt(7)));

        }
        cursor.close();
        db.close();
        return result;

    }

    @Override
    public ArrayList<Propietario> listaUsuarios(int cantidad) {
        ArrayList<Propietario> result = new ArrayList<Propietario>();
        SQLiteDatabase db = getReadableDatabase();
        // metodo query()
        String[] CAMPOS = {"_id","nombre", "apellidos"};
        Cursor cursor=db.query("usuarios", CAMPOS, null, null,
                null, null, "_id", null);
        while (cursor.moveToNext()){
            result.add(new Propietario(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        cursor.close();
        db.close();
        return result;
    }
    public void modificarUsuarios(Integer id, String nombre, String apellidos, String vehiculo,String matricula, int plaza, String mando, Integer activo) {

        SQLiteDatabase db = getWritableDatabase();
       //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("apellidos",apellidos);
        valores.put("vehiculo",vehiculo);
        valores.put("matricula",matricula);
        valores.put("plaza",plaza);
        valores.put("mando",mando);
        valores.put("activo",activo);

        //Actualizamos el registro en la base de datos
        db.update("Usuarios", valores, "_id="+id, null);
        db.close();

    }


}

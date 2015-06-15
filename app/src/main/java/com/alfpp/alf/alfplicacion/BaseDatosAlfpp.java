package com.alfpp.alf.alfplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Alf on 02/06/2015.
 */
public class BaseDatosAlfpp extends SQLiteOpenHelper{
    private static final int VERSION_BASEDATOS = 1;

    //Nombre del Archivo de Base de Datos
    private static final String NOMBRE_BASEDATOS = "bdal.db";

    //Sentencia SQL creacion Tabla Usuario

    public BaseDatosAlfpp(Context context){
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

    }
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE, usuario TEXT,password TEXT) ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists users");
        db.execSQL("CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE, usuario TEXT,password TEXT) ");
    }
    public void insertaUsuario(String nombre, String apellidos, int edad, double peso, String user, String pass){
        int idUsuario=0;
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("apellidos",apellidos);
            values.put("edad",edad);
            values.put("peso",peso);
            values.put("usuario",user);
            values.put("password",pass);
            idUsuario = (int) db.insert("users",null,values);
        }
        db.close();
    }
    public void modificaUsuario(int id,String nombre, String apellidos, int edad){
        SQLiteDatabase db = getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre",nombre);
            valores.put("apellidos",apellidos);
            valores.put("edad", edad);

            db.update("users", valores, "_id=" + id, null);

        db.close();
    }
    public void borrarUsuario(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("usuarios","_id="+id,null);
    }

}

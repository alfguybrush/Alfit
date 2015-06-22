package com.alfpp.alf.alfplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alf on 02/06/2015.
 */
public class BaseDatosAlfpp extends SQLiteOpenHelper{
    private static final int VERSION_BASEDATOS = 1;

    //Nombre del Archivo de Base de Datos
    private static final String NOMBRE_BASEDATOS = "qalf1.db";

    //Sentencia SQL creacion Tabla Usuario

    public BaseDatosAlfpp(Context context){
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

    }
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE usuario(_id INTEGER PRIMARY KEY AUTOINCREMENT, sexo INTEGER, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE, usuario TEXT,password TEXT) ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists users");
        db.execSQL("CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT,sexo INTEGER, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE, usuario TEXT,password TEXT) ");
    }
    public boolean insertaUsuario(String nombre, String apellidos, int edad, double peso, String user, String pass,int Sexo){
        int idUsuario=0;
        SQLiteDatabase db = getWritableDatabase();
        boolean valido;
        if(db!=null){
            ContentValues values = new ContentValues();
            values.put("sexo",Sexo);
            values.put("nombre",nombre);
            values.put("apellidos",apellidos);
            values.put("edad",edad);
            values.put("peso",peso);
            values.put("usuario",user);
            values.put("password",pass);
            idUsuario = (int) db.insert("usuario",null,values);
            valido = true;
        }else{
            valido = false;
        }
        db.close();
        return valido;
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
        db.delete("usuarios", "_id=" + id, null);
    }

    public String getUsuario(String id){
        SQLiteDatabase db = getWritableDatabase();
        String user = "";
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT usuario FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
               user = c.getString(0);
            } while(c.moveToNext());

        }
        return user;
    }
}

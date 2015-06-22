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
    private static final String NOMBRE_BASEDATOS = "qalfV1.0.db";

    //Sentencia SQL creacion Tabla Usuario

    public BaseDatosAlfpp(Context context){
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

    }
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE usuario(_id INTEGER PRIMARY KEY AUTOINCREMENT, sexo INTEGER, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE,altura DOUBLE, usuario TEXT,password TEXT, Vo2 DOUBLE) ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists usuario");
        db.execSQL("CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT,sexo INTEGER, nombre TEXT, apellidos TEXT, edad INTEGER, peso DOUBLE, altura DOUBLE, usuario TEXT,password TEXT) ");
    }
    public boolean insertaUsuario(String nombre, String apellidos, int edad, double peso,double altura, String user, String pass,int Sexo){
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
            values.put("altura",altura);
            values.put("usuario",user);
            values.put("password",pass);
            values.put("Vo2",0.0);
            idUsuario = (int) db.insert("usuario",null,values);
            valido = true;
        }else{
            valido = false;
        }
        db.close();
        return valido;
    }
    //Sexo 0 = Homnbre , 1 = Mujer;
    public void modificaUsuario(int id,String nombre,String apellidos,String user, String pass,int Sexo, int edad, double peso, double altura){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sexo",Sexo);
        values.put("nombre",nombre);
        values.put("apellidos",apellidos);
        values.put("edad",edad);
        values.put("peso",peso);
        values.put("altura",altura);
        values.put("usuario",user);
        values.put("password",pass);
        db.update("usuario", values, "_id=" + id, null);

        db.close();
    }
    public void borrarUsuario(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("usuarios", "_id=" + id, null);
    }

    //////********Operaciones con Usuario***************///
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
    //////********Operaciones con Nombre***************///
    public String getNombre(String id){
        SQLiteDatabase db = getWritableDatabase();
        String name = "";
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT nombre FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                name = c.getString(0);
            } while(c.moveToNext());

        }
        return name;
    }
    //////********Operaciones con Apellido***************///
    public String getApellido(String id){
        SQLiteDatabase db = getWritableDatabase();
        String name = "";
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT apellidos FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                name = c.getString(0);
            } while(c.moveToNext());

        }
        return name;
    }
    //////********Operaciones con Peso***************///
    public double getPeso(String id){
        SQLiteDatabase db = getWritableDatabase();
        double weight = 0;
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT peso FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                weight = c.getDouble(0);
            } while(c.moveToNext());

        }
        return weight;
    }

    //////********Operaciones con Altura***************///
    public double getAltura(String id){
        SQLiteDatabase db = getWritableDatabase();
        double weight = 0;
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT altura FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                weight = c.getDouble(0);
            } while(c.moveToNext());

        }
        return weight;
    }

    //////********Operaciones con Edad***************///
    public int getEdad(String id){
        SQLiteDatabase db = getWritableDatabase();
        int age = 0;
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT edad FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                age = c.getInt(0);
            } while(c.moveToNext());

        }
        return age;
    }

    //////********Operaciones con Sexo***************///
    public int getSexo(String id){
        SQLiteDatabase db = getWritableDatabase();
        int genre = 0;
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT edad FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                genre = c.getInt(0);
            } while(c.moveToNext());

        }
        return genre;
    }

    //////********Operaciones con Vo2***************///
    public void setVo2(String id, double vo2){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[] {Double.toString(vo2),id};
        Cursor c = db.rawQuery(" UPDATE usuario SET Vo2=? WHERE Id=? ", args);
    }
    public double getVo2(String id){
        SQLiteDatabase db = getWritableDatabase();
        double vo2 = 0;
        String[] args = new String[] {id};
        Cursor c = db.rawQuery(" SELECT vo2 FROM usuario WHERE _id=? ", args);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                vo2 = c.getInt(0);
            } while(c.moveToNext());

        }
        return vo2;
    }



}

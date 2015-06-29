package com.alfpp.alf.alfplicacion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alf on 27/06/2015.
 */
public class Usuario implements Parcelable {

    int id,edad;
    String Nombre,Apellido,User;
    int sexo; //0 Masculino 1 Femenino
    //IMAGEN
    double peso,vo2;

    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(Nombre);
        out.writeString(Apellido);
        out.writeString(User);
        out.writeInt(sexo);
        out.writeDouble(peso);
        out.writeDouble(vo2);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        public Usuario[] newArray(int size) {
            return new Usuario [size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Usuario(Parcel in) {
        id = in.readInt();
        Nombre = in.readString();
        Apellido= in.readString();
        User = in.readString();
        sexo = in.readInt();
        peso = in.readDouble();
        vo2 = in.readDouble();
    }

    public Usuario(int idUser, String user, String nombre, String apellido,int sexo,int edad,double peso){
        this.id = idUser;
        this.Nombre = nombre;
        this.Apellido =apellido;
        this.User=user;
        this.peso = peso;
        this.sexo = sexo;
        this.edad = edad;

    }

    public void setNombre(String nombre){
        this.Nombre = nombre;
    }
    public void setApellido(String apellido){
        this.Apellido = apellido;
    }
    public void setUser(String user){
        this.User = user;
    }

    public void setVo2(double vo2){
        this.vo2 = vo2;
    }
    public void setPeso(double peso){
        this.peso = peso;
    }



    public int getId ( ){
        return id;
    }
    public String getNombre(){
        return this.Nombre;
    }
    public String getApellido(){
        return this.Apellido;
    }
    public String getUser(){
        return this.User;
    }

    public double getPeso(){
        return peso;
    }
    public double getVo2(){
        return vo2;
    }



}

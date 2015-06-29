package com.alfpp.alf.alfplicacion;

/**
 * Created by Alf on 29/06/2015.
 */
public class Actividad {

    private int idCarrera, idUsuario;
    private double velocidadMedia, intensidadMedia,duracion,distancia;
    private String fecha;

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Actividad(int idCarrera, int idUsuario, double velocidadMedia, double intensidadMedia, double duracion, double distancia, String fecha) {

        this.idCarrera = idCarrera;
        this.idUsuario = idUsuario;
        this.velocidadMedia = velocidadMedia;
        this.intensidadMedia = intensidadMedia;
        this.duracion = duracion;
        this.distancia = distancia;
        this.fecha = fecha;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getVelocidadMedia() {
        return velocidadMedia;
    }

    public void setVelocidadMedia(double velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }

    public double getIntensidadMedia() {
        return intensidadMedia;
    }

    public void setIntensidadMedia(double intensidadMedia) {
        this.intensidadMedia = intensidadMedia;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public int getIdCarrera() {

        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Actividad(int idCarrera) {

        this.idCarrera = idCarrera;
    }
}

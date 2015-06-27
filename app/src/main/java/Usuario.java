/**
 * Created by Alf on 27/06/2015.
 */
public class Usuario {

    int id;
    String Nombre,Apellido,User;
    //IMAGEN
    double peso,vo2;

    public void Usuario(int idUser, String user, String nombre, String apellido,double peso){
        this.id = idUser;
        this.Nombre = nombre;
        this.Apellido =apellido;
        this.User=user;
        this.peso = peso;

    }
}

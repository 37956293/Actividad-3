/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection; // es la conexion "fisica" a la base de datos.
import java.sql.DriverManager; // sirva para gestionar lo que tenemos en la bse de datos
import java.sql.SQLException; // proporciona los errores en la base de datos
/**
 *
 * @author Usuario
 */
public class conexion {
    
    public final String puerto = "3306";
    public final String db = "db_docente";
    public final String /**/usuario = "root";
    public final String /**/contra = "Doom2023$";
    public final String /**/URLCONEXION = String.format("jdbc:mysql://localhost:3306/db_docente");
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    
    public Connection conexionBD;
    
    public void abrir_conexion(){
        try{
        Class.forName(jdbc);
        conexionBD = DriverManager.getConnection(URLCONEXION,usuario,contra);
        System.out.println("Conexion Correcta bb!");
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println("Algo salio mal w" + ex.getMessage());
        }
    }
    public void cerrar_conexion(){
        try{
            conexionBD.close();
        }catch(SQLException ex){
            System.out.println("Algo salio mal w" + ex.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
/**
 *
 * @author Usuario
 */
public class Cliente extends persona{
    conexion cn;
public Cliente (){}
    public Cliente (String id_nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento){
        super(id_nit,nombres,apellidos,direccion,telefono,fecha_nacimiento);
    }
    public void crear (){
    try{
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        String query = "INSERT INTO persona(id_nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getId_nit());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento());
        int executar = parametro.executeUpdate();
        System.out.println("ingreso exitoso" + Integer.toString(executar));
        cn.cerrar_conexion();
    }catch(SQLException ex){
    System.out.println("Error en crear" + ex.getMessage());
    }
}
    @Override
    public DefaultTableModel leer (){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new conexion();
            cn.abrir_conexion();
            String query = "select * from persona";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado [] = {"id_nit","nombres","apellidos","direccion","telefono","fecha_nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [] = new String [6];
            while(consulta.next()){
                datos [0] = consulta.getString("id_nit");
                datos [1] = consulta.getString("nombres");
                datos [2] = consulta.getString("apellidos");
                datos [3] = consulta.getString("direccion");
                datos [4] = consulta.getString("telefono");
                datos [5] = consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
        cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error en leer" + ex.getMessage());
        }
        return tabla;
    }
    
    public void actualizar(){
          try{
              PreparedStatement parametro;
              cn = new conexion();
              cn.abrir_conexion();
              String query = "UPDATE persona SET id_nit = ?, nombres = ?, apellidos = ?,direccion = ?,telefono = ?,fecha_nacimiento = ?";
              parametro  = (PreparedStatement) cn.conexionBD.prepareStatement(query);
              parametro.setString(1, getId_nit());
              parametro.setString(2, getNombres());
              parametro.setString(3, getApellidos());
              parametro.setString(4, getDireccion());
              parametro.setString(5, getTelefono());
              parametro.setString(6, getFecha_nacimiento());
              int executar = parametro.executeUpdate();
              System.out.println("Modificacion Exitosa:" + Integer.toString(executar));
              cn.cerrar_conexion();
          
          
          }catch(SQLException ex){
              System.out.println("Error en Actualizar" + ex.getMessage());
          }       
    }
    public void borrar(){
        try{
            PreparedStatement parametro;
            cn = new conexion();
            cn.abrir_conexion();
            String query = "delete from persona";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            
            int executar = parametro.executeUpdate();
            System.out.println("Eliminacion Exitosa:" + Integer.toString(executar));
            
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Eliminacion Erronea" + ex.getMessage());
            
        }
    }
}
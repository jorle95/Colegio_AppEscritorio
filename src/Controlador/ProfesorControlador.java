
package Controlador;

import Modelo.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfesorControlador {
        public static void crearRegistro(int id, String nombre, String correo, int edad) {//***CREAR***
        Conexion.conectar();
        
        try {
              Conexion.getConnect().createStatement().execute("insert into profesores"
                    + " (id,nombre,correo,edad) "
                    + "values ("+id+", '"+nombre+"','"+correo+"',"+edad+");");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
    }
    
    public static ArrayList<Profesor> consultarRegistros() {//***LEER***
        
        ArrayList<Profesor> profesores = new ArrayList<>();
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select * from profesores");
            
            while (rs.next()) {
                long id = Long.parseLong(rs.getString("id"));
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                int edad = Integer.parseInt(rs.getString("edad"));
                
                
                profesores.add(new Profesor(id, nombre, correo, edad));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return profesores;
    }
    
    
    public static Profesor obtenerRegistrosById(long idr) {
        
        Profesor profesor = null;
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery(""
                    + "select * from profesores where id="+idr+";");
            
            int id =Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            int edad = Integer.parseInt(rs.getString("edad"));


            profesor = new Profesor(id, nombre, correo, edad);

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return profesor;
    }
    
    public static void modificarRegistro(long id, String nombre, String correo, int edad){//*****ACTUALIZAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "update profesores set nombre='"+nombre+"',"
                    +"correo='"+correo+"',"+ "edad="+edad+" where id="+id+";");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        
    }
        public static void eliminarRegistro(long ide){//****ELIMINAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "delete from profesores where id="+ide+";");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();      
    }
}

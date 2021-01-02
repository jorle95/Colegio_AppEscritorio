
package Controlador;

import Modelo.Estudiante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstudianteControlador {
    
    public static void crearRegistro(int id, String nombre, String celular, int edad) {//*********CREAR
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute("insert into estudiantes"
                    + " (id,nombre,celular,edad) "
                    + "values ("+id+", '"+nombre+"','"+celular+"',"+edad+");");
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
    }
 
      public static ArrayList<Estudiante> consultarRegistros() { //***LEER***
        
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select * from estudiantes");
            
            while (rs.next()) {
                long id = Long.parseLong(rs.getString("id"));
                String nombre = rs.getString("nombre");
                String celular = rs.getString("celular");
                int edad = Integer.parseInt(rs.getString("edad"));
                
                
                estudiantes.add(new Estudiante(id, nombre, celular, edad));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return estudiantes;
    }        
    
       public static void agregarCurso(int idEstudiante, int idCurso){
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute("insert into curso_estudiante"
                    + " (id_curso, id_estudiante) "
                    + "values ("+idCurso+", "+idEstudiante+");");
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();  
    }
    
    public static void modificarRegistro(long id, String nombre, String celular, int edad){//*****ACTUALIZAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "update estudiantes set nombre='"+nombre+"',"
                    +"celular='"+celular+"',"+ "edad="+edad+" where id="+id+";");
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        
    }   
       
        public static void eliminarRegistro(long ide){//****ELIMINAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "delete from estudiantes where id="+ide+";");
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();      
    }       
       
    public static ArrayList<Estudiante> extraerEstudianteByProfesor(long idProfesor){
        Estudiante estudiante = null;
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        boolean agregar = true;
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select id_estudiante, "
                    + "estudiantes.nombre, estudiantes.celular, estudiantes.edad from curso_estudiante, estudiantes\n" +
                    "where id_curso IN ( select id from cursos where profesor="+idProfesor+" and id_estudiante=estudiantes.id)");
            
            while(rs.next()){
                  agregar = true;
                  int id =Integer.parseInt(rs.getString("id_estudiante"));
                  String nombre = rs.getString("nombre");
                  String celular = rs.getString("celular");
                  int edad =Integer.parseInt(rs.getString("edad"));
                  estudiante = new Estudiante(id,nombre,celular, edad);
                  for (Estudiante e : estudiantes){
                      if (e.getNombre().equals(estudiante.getNombre())){
                          agregar = false;
                      }
                  }
                  if (agregar){
                      estudiantes.add(estudiante);
                  }
            }
            Collections.sort(estudiantes);
            
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();
        return estudiantes;
    }
 
  public static ArrayList<Estudiante> extraerEstudianteByCurso(long idCurso){
        Estudiante estudiante = null;
        boolean agregar = true;
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select curso_estudiante.id_estudiante, estudiantes.nombre, estudiantes.celular, estudiantes.edad\n" +
                            "from curso_estudiante, estudiantes where (id_curso="+idCurso+" and id_estudiante=estudiantes.id)");
            
            while(rs.next()){
                  agregar = true;
                  int id =Integer.parseInt(rs.getString("id_estudiante"));
                  String nombre = rs.getString("nombre");
                  String celular = rs.getString("celular");
                  int edad =Integer.parseInt(rs.getString("edad"));
                  estudiante = new Estudiante(id,nombre,celular, edad);
                  for (Estudiante e : estudiantes){
                      if (e.getNombre().equals(estudiante.getNombre())){
                          agregar = false;
                      }
                  }
                  if (agregar){
                      estudiantes.add(estudiante);
                  }
                  
            }
            Collections.sort(estudiantes);
            
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();
        return estudiantes;
    }

    public static Estudiante obtenerRegistrosById(long idr) {
        
        Estudiante estudiante = null;
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery(""
                    + "select * from estudiantes where id="+idr+";");
            
            int id =Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            String celular = rs.getString("celular");
            int edad = Integer.parseInt(rs.getString("edad"));


            estudiante = new Estudiante(id, nombre, celular, edad);

        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return estudiante;
    }

}

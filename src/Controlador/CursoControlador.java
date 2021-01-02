
package Controlador;

import Modelo.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoControlador {
    
    public static void crearRegistro(int id, String nombre, int capacidad, int profesorid) {//*****CREAR*****
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute("insert into cursos"
                    + " (id,nombre,capacidad,profesor) "
                    + "values ("+id+",'"+nombre+"',"+capacidad+","+profesorid+");");
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
    }
    
    public static ArrayList<Curso> consultarRegistros() { // **-***LEER******
        
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select * from cursos");
            
            while (rs.next()) {
                long id = Long.parseLong(rs.getString("id"));
                String nombre = rs.getString("nombre");
                int capacidad = Integer.parseInt(rs.getString("capacidad"));
                int profe = Integer.parseInt(rs.getString("profesor"));
                
                
                cursos.add(new Curso(id, nombre, capacidad, profe));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return cursos;
    }

    public static void modificarRegistro(long id, String nombre, int capacidad, int profe){//*****ACTUALIZAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "update cursos set nombre='"+nombre+"',"
                    +"capacidad="+capacidad+","+ "profesor="+profe+" where id="+id+";");
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        
    }   
       
        public static void eliminarRegistro(long ide){//****ELIMINAR****
        
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "delete from cursos where id="+ide+";");
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();      
    }    
    
    public static void agregarCurso(int idCurso, int idProfesor){
        Conexion.conectar();
        
        try {
            Conexion.getConnect().createStatement().execute(""
                    + "update cursos set profesor="+idProfesor+" "
                    +" where id="+idCurso+";");
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion(); 
    }
    
    public static ArrayList<Curso> extraerRegistrosByProfesor(long idProfesor){
        Curso curso = null;
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        boolean agregar = true;
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery(""
                    + "select * from cursos where profesor="+idProfesor+";");
            
            while(rs.next()){
                  agregar = true;
                  int id =Integer.parseInt(rs.getString("id"));
                  String nombre = rs.getString("nombre");
                  int capacidad =Integer.parseInt(rs.getString("capacidad"));
                  curso = new Curso(id,nombre, capacidad);
                  for (Curso e : cursos){
                      if (e.getNombre().equals(curso.getNombre())){
                          agregar = false;
                      }
                  }
                  if (agregar){
                      cursos.add(curso);
                  }
            }
            Collections.sort(cursos);
            
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();
        return cursos;
    }
    
    public static Curso obtenerRegistrosById(long idr) {
        
        Curso curso = null;
        
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery(""
                    + "select * from cursos where id="+idr+";");
            
            int id =Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            int capacidad = Integer.parseInt(rs.getString("capacidad"));
            int pr = Integer.parseInt(rs.getString("profesor"));


            curso = new Curso(id, nombre, capacidad, pr);

        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();   
        
        return curso;
    }
    
      public static ArrayList<Curso> extraerCursoByEstudiante(long idEstudiante){
        Curso curso = null;
        boolean agregar = true;
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        Conexion.conectar();
        
        try {
            ResultSet rs = Conexion.getConnect().createStatement().executeQuery("select curso_estudiante.id_curso, cursos.nombre, cursos.capacidad, cursos.profesor\n" +
                            "from curso_estudiante, cursos where (id_estudiante="+idEstudiante+" and id_curso=cursos.id)");
            
            while(rs.next()){
                  agregar = true;
                  int id =Integer.parseInt(rs.getString("id_curso"));
                  String nombre = rs.getString("nombre");
                  int capacidad =Integer.parseInt(rs.getString("capacidad"));
                  int prof =Integer.parseInt(rs.getString("profesor"));
                  curso = new Curso(id,nombre,capacidad, prof);
                  for (Curso c : cursos){
                      if (c.getNombre().equals(curso.getNombre())){
                          agregar = false;
                      }
                  }
                  if (agregar){
                      cursos.add(curso);
                  }
                  
            }
            Collections.sort(cursos);
            
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        Conexion.cerrarConexion();
        return cursos;
    }
}

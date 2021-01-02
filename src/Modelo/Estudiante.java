package Modelo;

import java.util.ArrayList;

public class Estudiante implements Comparable<Estudiante>{
    private long id;
    private String nombre;
    private String celular;
    private int edad;
    private ArrayList<Curso> cursos;

    public Estudiante(long id, String nombre, String celular, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
@Override
    public int compareTo(Estudiante e){
        
        if(e.getId()>this.id){
                return -1;
            }else if(e.getId()==this.id){
                return 0;
            }else{
                return 1;
            }
}
    
}

package Modelo;

import java.util.ArrayList;


public class Curso implements Comparable<Curso>{
    public long id;
    public String nombre;
    public int capacidad;
    public int profesorid;
    public ArrayList<Estudiante> estudiantes;

    public Curso(long id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Curso(long id, String nombre, int capacidad, int profesorid) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesorid = profesorid;
    }
    
    @Override
    public int compareTo(Curso e){
        if(e.getId()>this.id){
                return -1;
            }else if(e.getId()==this.id){
                return 0;
            }else{
                return 1;
            }
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getProfesorid() {
        return profesorid;
    }

    public void setProfesorid(int profesorid) {
        this.profesorid = profesorid;
    }
            
}

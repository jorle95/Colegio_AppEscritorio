package Controlador;


import Vista.Dashboard;


public class Principal {

    public static void main(String[] args) {
        System.out.println("Sistema de operaciones colegio");
        System.out.println("Para que el sistema funcione correctamente, se deben ingresar todos los valores que sean editables al momento de seleccionar las opciones de agregar o modificar");
        System.out.println("Adicionalmente, no se deben repetir los registros id existentes");
        Dashboard db = new Dashboard();
        db.setVisible(true);
        db.setLocationRelativeTo(null);

    }
    
}

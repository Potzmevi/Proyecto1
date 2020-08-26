/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import BaseDeDatos.Conexion;
import FrontEnd.CargarArchivo;
import FrontEnd.Ingresar;
import FrontEnd.Login;
import FrontEnd.CrearTiempo;
import FrontEnd.CrearTienda;
import FrontEnd.MenuEmpresa;
import java.io.File;


/**
 *
 * @author potz
 */
public class Main {
    public static Conexion conexion = new Conexion();
        
    /**
     * Main, Mandamos a llamar nuestro form para ingresar y creamos una conexion global
     * Establecemos la conexion
     * Creamos nuestra carpeta para guardar los reportes
     * @param args 
     */
    public static void main(String[] args) {
         File directorio = new File("Reportes");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio reportes creados");
            }
        }
        Ingresar ingresar = new Ingresar();
        ingresar.setVisible(true);
        conexion.conexionDB();
    }
}

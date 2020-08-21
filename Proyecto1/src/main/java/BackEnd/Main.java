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


/**
 *
 * @author potz
 */
public class Main {
    public static Conexion conexion = new Conexion();
        
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
        conexion.conexionDB();
    }
}

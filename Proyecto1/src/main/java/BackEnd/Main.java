/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import FrontEnd.CargarArchivo;
import FrontEnd.Ingresar;
import FrontEnd.Login;


/**
 *
 * @author potz
 */
public class Main {
    public static void main(String[] args) {
        Ingresar ingresar = new Ingresar();
        ingresar.setVisible(true);
        CargarArchivo cargar= new CargarArchivo();
        cargar.setVisible(true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import FrontEnd.CargarArchivo;
import FrontEnd.Ingresar;
import FrontEnd.Login;
import FrontEnd.CrearTiempo;


/**
 *
 * @author potz
 */
public class Main {
    public static void main(String[] args) {
        Ingresar ingresar = new Ingresar();
        ingresar.setVisible(true);
        CrearTiempo tiempo = new CrearTiempo();
        tiempo.setVisible(true);
    }
}

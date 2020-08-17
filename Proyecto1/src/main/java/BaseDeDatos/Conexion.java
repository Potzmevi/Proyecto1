/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class Conexion {

    public static final int MYSQL_ERROR= 1062;
    Connection conexion = null;
    private final String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "juanpablo07";
    private final String url = "jdbc:mysql://localhost/INTELAF?useSSL=false";

    ;

    public void conexionDB() {
        conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar " + e);
        }

    }

    public Connection getConnection() {
        return (Connection) conexion;
    }

    //metodo encargado de salir de la base de datos
    public void disconnectDB() {
        conexion = null;
        if (conexion == null) {
            System.out.println("Conexion terminada");
        }
    }

    public void Insert(String query) {
        try {
            conexionDB();
            Statement stmt = null;
            stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
            disconnectDB();
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_ERROR) {
                System.out.println("entraaa");
                JOptionPane.showMessageDialog(null, "Error, el codigo de tienda ya existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }

    }
    public ResultSet ComboBox(String query){
        Statement stmt = null;
        try {
            conexionDB();
            stmt = null;
            stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            return null;

        }
    }
}

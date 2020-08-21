/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import FrontEnd.CargarArchivo;
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

    public static final int MYSQL_ERROR = 1062;
    Connection conexion = null;
    private final String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "juanpablo07";
    private final String url = "jdbc:mysql://localhost:3306/INTELAF";

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
                JOptionPane.showMessageDialog(null, "Error en la linea "+CargarArchivo.contadorlinea+" del archivo de texto");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }

    }

    public ResultSet ComboBox(String query) {
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

    public int InsertVenta(String query) {
        int numero=0;
        int resultado = -1;
        try {
            conexionDB();
            Statement stmt = null;
            stmt = getConnection().createStatement();
            numero = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
            rs.close();

            stmt.close();
            disconnectDB();
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_ERROR) {
                JOptionPane.showMessageDialog(null, "Error el ID o codigo ya existe");
            } else {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return resultado;
    }

    public ResultSet Table(String query) {
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

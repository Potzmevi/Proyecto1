/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import FrontEnd.CargarArchivo;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author potz
 */
public class Conexion {

    /**
     * Datos para conectar con la base de datos
     */
    public static final int MYSQL_ERROR = 1062;
    Connection conexion = null;
    private final String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "juanpablo07";
    private final String url = "jdbc:mysql://localhost:3306/INTELAF";
    
    /**
     * Metodo para conectar con la base de datos
     * Mandando el user, password y url
     */
    public void conexionDB() {
        conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar " + e);
        }

    }
    
    /**
     * Obtenemos la conexion
     * @return 
     */
    public Connection getConnection() {
        return (Connection) conexion;
    }

    /**
     * metodo encargado de desconectar de la base de datos
     */
    public void disconnectDB() {
        conexion = null;
        if (conexion == null) {
            
        }
    }

    /**
     * Metodo para Insertar datos en la base de datos
     * Usamos PreparedStatement para mandar la query
     * @param query 
     */
    public void Insert(String query) {
        try {
            conexionDB();
            PreparedStatement stmt = null;
            stmt = getConnection().prepareStatement(query);
            stmt.executeUpdate(query);
          
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_ERROR) {
                JOptionPane.showMessageDialog(null, "Error en la linea "+CargarArchivo.contadorlinea+" del archivo de texto");
            } else {
                JOptionPane.showMessageDialog(null, "Error " + e);
            }
        }finally {
              disconnectDB();
        }

    }

    /**
     * Metodo para insertar datos de la base a un combobox
     * Usamos PreparedStatement
     * @param query
     * @return 
     */
    public ResultSet ComboBox(String query) {
        PreparedStatement stmt = null;
        try {
            conexionDB();
            stmt = null;
            stmt = getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            return result;
        } catch (Exception e) {
            return null;

        }finally {
              disconnectDB();
        }
    }

    /**
     * Metodo para insertar ventas en la base de datos
     * Obteniendo la primary key de la tabla llamada para crear la factura y venta al mismo tiempo
     * @param query
     * @return 
     */
    public int InsertVenta(String query) {
        int numero=0;
        int resultado = -1;
        try {
            conexionDB();
            PreparedStatement stmt = null;
            stmt = getConnection().prepareStatement(query);
            //Nos devuelve la primary key de lo que acabamos de llamar
            numero = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getInt(1);
            }
            rs.close();

            stmt.close();
           
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_ERROR) {
                JOptionPane.showMessageDialog(null, "Error el ID o codigo ya existe");
            } else {
                JOptionPane.showMessageDialog(null, e);
            }
        } finally {
             disconnectDB();
        }
        return resultado;
    }

    /**
     * Metodo para llenar las Jtables usando PreparedStatement
     * Obteniendo los datos con un resultset y guardandolos
     * @param query
     * @return 
     */
    public ResultSet Table(String query) {
        PreparedStatement stmt = null;
        try {
            conexionDB();
            stmt = null;
            stmt = getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            return null;

        }finally {
             disconnectDB();
        }
    }
}

package config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String bd = "jdbc:mysql://localhost/tienda";
            String usuario = "root";
            String password = "";
            Connection conexion = DriverManager.getConnection(bd, usuario, password);
            return conexion;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException err) {
            System.out.println(err.toString());
        }
        return null;
    }
}

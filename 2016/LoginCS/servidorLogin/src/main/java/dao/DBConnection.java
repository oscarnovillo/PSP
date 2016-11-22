/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class DBConnection {

    public static final String DB_URL = "";
    public static final String DRIVER = "org.sqlite.JDBC";
    
    public DBConnection()
    {
        
    }
    
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;

        connection = DriverManager.getConnection(
                "jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8144411", "sql8144411", "ZEZRvssuFH");

        return connection;
    }

    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

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


    
    public Connection getConnection(String conn) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conn);
        } catch (SQLException ex) {
        }
        return connection;
    }
    
    public Connection getConnectionMysql() throws ClassNotFoundException {
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8144411"
                    ,"sql8144411","ZEZRvssuFH");
        } catch (SQLException ex) {
        }
        return connection;
    }
    
    public void cerrarConexion( Connection connection )
    {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

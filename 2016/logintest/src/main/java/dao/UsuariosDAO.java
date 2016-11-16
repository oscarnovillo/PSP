/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author oscar
 */
public class UsuariosDAO {

    public List<Usuario> getUsers() {
        List<Usuario> lista = null;
        DBConnection db = new DBConnection();
        Connection con  = null;
        try {
            con = db.getConnectionMysql();
            QueryRunner qr =  new QueryRunner();
            ResultSetHandler<List<Usuario>> h = 
                    new BeanListHandler<Usuario>(Usuario.class);
            lista = qr.query(con, "select * FROM LOGIN", h);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public void updateUser(Usuario u) {
        DBConnection db = new DBConnection();
        Connection con  = null;
        try {
            con = db.getConnectionMysql();
            QueryRunner qr =  new QueryRunner();
             
            int filas = qr.update(con, 
                    "UPDATE LOGIN SET USER=? , FECHA=? WHERE id=?",
                    u.getUser(),u.getFecha(),u.getId());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            db.cerrarConexion(con);
        }
        

    }
    
    
    public Usuario addUser(Usuario u) {
        DBConnection db = new DBConnection();
        Connection con  = null;
        
        try {
            con = db.getConnectionMysql();
            con.setAutoCommit(false);
            QueryRunner qr =  new QueryRunner();
             
            int id = qr.insert(con, 
                    "INSERT INTO LOGIN (USER,PASSWORD,FECHA) VALUES(?,?,?)",
                     new ScalarHandler<BigInteger>(),
                     u.getUser(),u.getPassword(), u.getFecha()).intValue();
            
            u.setId(id);
            con.commit();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            
            db.cerrarConexion(con);
        }
        return u;

    }
    

}

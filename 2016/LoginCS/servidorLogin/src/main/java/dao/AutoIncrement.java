/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dam.model.Nuevo;
import dam.model.Usuario;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author oscar
 */
public class AutoIncrement {
    
     public Nuevo addNuevo(Nuevo u) {
        DBConnection db = new DBConnection();
        Connection con  = null;
        
        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            QueryRunner qr =  new QueryRunner();
             
            int id = qr.insert(con, 
                    "INSERT INTO NUEVA (COL,FECHA) VALUES(?,?)",
                     new ScalarHandler<BigInteger>(),
                     u.getCol(), u.getFecha()).intValue();
            
            u.setId(id);
            con.commit();
            
        } catch (Exception ex) {
            
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            db.cerrarConexion(con);
        }
        return u;

    }
    
    
}

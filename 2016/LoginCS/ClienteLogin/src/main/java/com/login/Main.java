/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import dam.model.Usuario;
import dao.ProxyUsuariosDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PasswordHash;


/**
 *
 * @author oscar
 */
public class Main {
    
    public static void main(String[] args) {
        
        try {
            //conexion con BD y meter usuario.
            ProxyUsuariosDAO user = new ProxyUsuariosDAO();
            List<Usuario> lista = user.getUsers();
            
            for (Usuario u : lista)
            { 
                System.out.println(u);
            }
            Usuario usu = new Usuario();
            usu.setUser("PROBANDO JSON");
            String hash = user.addUser(usu);
            System.out.println(hash);
            boolean ok = PasswordHash.validatePassword(usu.getUser(), hash);
            System.out.println(ok);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

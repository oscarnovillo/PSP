/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import dam.model.Usuario;
import dao.ProxyUsuariosDAO;
import java.util.Date;
import java.util.List;


/**
 *
 * @author oscar
 */
public class Main {
    
    public static void main(String[] args) {
        
        //conexion con BD y meter usuario.
        ProxyUsuariosDAO user = new ProxyUsuariosDAO();
        List<Usuario> lista = user.getUsers();
        
        for (Usuario u : lista)
        {
            System.out.println(u); 
        }
        
    }
    
}

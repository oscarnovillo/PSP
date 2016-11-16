/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import dao.UsuariosDAO;
import java.util.Date;
import java.util.List;
import model.Usuario;

/**
 *
 * @author oscar
 */
public class Main {
    
    public static void main(String[] args) {
        
        //conexion con BD y meter usuario.
        UsuariosDAO user = new UsuariosDAO();
        List<Usuario> lista = user.getUsers();
        
        for (Usuario u : lista)
        {
            System.out.println(u); 
        }
        //lista.get(0).setUser("pedro Nuevo");
        lista.get(0).setFecha(new Date());
        user.updateUser(lista.get(0));
        //Usuario nuevo = new Usuario("user nuevo","pass",new Date());
        //user.addUser(nuevo);
        //System.out.println(nuevo);
       
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dam.model.Usuario;
import dao.UsuariosDAO;
import java.util.List;

/**
 *
 * @author oscar
 */
public class ServiciosUsuarios {

    public List<Usuario> getUsers() {
        List<Usuario> lista = null;
        UsuariosDAO user = new UsuariosDAO();
        lista = user.getUsers();
        return lista;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.*;
import java.util.Date;

/**
 *
 * @author oscar
 */
public class Usuario {
    
    private int id;
    private String user;
    private String password;
    private Date fecha;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    public Usuario() {
    }

    public Usuario(String user, String password, Date fecha) {
        this.user = user;
        this.password = password;
        this.fecha = fecha;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", user=" + user + ", password=" + password + ", fecha=" + fecha + '}';
    }
    
    
}

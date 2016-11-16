/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.*;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author oscar
 */
public class Usuario {
    
    private int id;
    private StringProperty user;
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
        dateProperty().set(fecha);
        this.fecha = fecha;
    }
    
    
    public Usuario() {
        this.user = new SimpleStringProperty();
    }
      
    
    private final ObjectProperty date = new SimpleObjectProperty();
    private final StringProperty destiny = new SimpleStringProperty();
    
    public ObjectProperty dateProperty()
    {
        return date;
    }
            
    
    public StringProperty userProperty() {
        return destiny ;
    }
    public final String getUser() {
        return userProperty().get();
    }
    public final void setUser(String destiny) {
        userProperty().set(destiny);
    }
    
       
    public Usuario(String user, String password, Date fecha) {
        this.setUser(user);
        this.password = password;
        this.fecha = fecha;
    }


//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }

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

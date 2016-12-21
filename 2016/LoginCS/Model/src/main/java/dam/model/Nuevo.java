/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.model;

import java.util.Date;

/**
 *
 * @author oscar
 */
public class Nuevo {
    
    private int id;
    private String col;
    private Date fecha;

    public Nuevo( String col, Date fecha) {
        
        this.col = col;
        this.fecha = fecha;
    }

    public Nuevo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}

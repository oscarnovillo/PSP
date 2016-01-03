/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemploj2ee.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "JUEGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juegos.findAll", query = "SELECT j FROM Juegos j"),
    @NamedQuery(name = "Juegos.findById", query = "SELECT j FROM Juegos j WHERE j.id = :id"),
    @NamedQuery(name = "Juegos.findByNombre", query = "SELECT j FROM Juegos j WHERE j.nombre = :nombre")})
public class Juegos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JuegosPK juegosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "NOMBRE")
    private String nombre;

    public Juegos() {
    }

    public Juegos(JuegosPK juegosPK) {
        this.juegosPK = juegosPK;
    }

    public Juegos(JuegosPK juegosPK, int id, String nombre) {
        this.juegosPK = juegosPK;
        this.id = id;
        this.nombre = nombre;
    }

    public JuegosPK getJuegosPK() {
        return juegosPK;
    }

    public void setJuegosPK(JuegosPK juegosPK) {
        this.juegosPK = juegosPK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (juegosPK != null ? juegosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juegos)) {
            return false;
        }
        Juegos other = (Juegos) object;
        if ((this.juegosPK == null && other.juegosPK != null) || (this.juegosPK != null && !this.juegosPK.equals(other.juegosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejemploj2ee.entity.Juegos[ juegosPK=" + juegosPK + " ]";
    }
    
}

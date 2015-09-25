/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primerejemplohilos;

/**
 *
 * @author oscar
 */
public class Turno {
    
    int turno;
    int numHilos;

    public Turno(int numHilos) {
        this.numHilos = numHilos;
    }
    

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
        this.turno %= numHilos;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primerejemplohilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class MiHilo implements Runnable {

    private int id;
    private StringBuffer sb;
    private StringBuffer out;
    private String text;
    private Turno turno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MiHilo(int id, StringBuffer sb, StringBuffer out, String text, Turno turno) {
        this.id = id;
        this.sb = sb;
        this.text = text;
        this.out = out;
        this.turno = turno;

    }

    @Override
    public void run() {
        boolean hecho = false;
        synchronized (sb) {

            try {
                while (!hecho) {
                    if (turno.getTurno() == id) {
                        sb.append(text);
                        System.out.print("hola mundo hilo " + id + sb.toString() + "\n");
                        turno.setTurno(id+1);
                        hecho = true;
                        sb.notifyAll();
                    } else {
                        sb.wait();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(MiHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

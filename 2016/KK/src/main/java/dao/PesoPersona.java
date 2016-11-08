/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author oscar
 */
public class PesoPersona {
    
    private int peso;
    private boolean lesionado;
    private ErrorPeso error;

    public PesoPersona() {
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }


    public ErrorPeso getError() {
        return error;
    }

    public void setError(ErrorPeso error) {
        this.error = error;
    }
    
}

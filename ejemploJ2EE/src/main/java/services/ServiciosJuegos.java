/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.JuegosDAO;
import java.util.ArrayList;
import java.util.Date;
import model.Juego;

/**
 *
 * @author oscar
 */
public class ServiciosJuegos {
    
    public ArrayList<Juego> getAllJuegos()
    {
        ArrayList<Juego> juegos =null;
        JuegosDAO juegosDAO = new JuegosDAO();
        juegos = juegosDAO.getAllJuegos();
        return juegos;
    }
 
    public ArrayList<Juego> getAllJuegos(String conn)
    {
        ArrayList<Juego> juegos =null;
        JuegosDAO juegosDAO = new JuegosDAO();
        juegos = juegosDAO.getAllJuegos(conn);
        return juegos;
    }
    
    public ArrayList<Juego> getAllJuegosMySQL()
    {
        ArrayList<Juego> juegos =null;
        JuegosDAO juegosDAO = new JuegosDAO();
        juegos = juegosDAO.getAllJuegosMySQL();
        return juegos;
    }
    
    public void updateJuego(Juego j)
    {
        
    }
    
}

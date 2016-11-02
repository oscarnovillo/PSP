/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import config.Configuration;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author oscar
 */
public class CalculoPeso {
    
    private LocalDateTime ultimallamada;
    
    private String nombre = "";
    private String peso = "";
    private CloseableHttpClient httpclient ;

    public CalculoPeso() {
        httpclient = HttpClients.createDefault();
    }
  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    
    
    
    public void registro(String nombre,String peso)
    {
        try {
            this.nombre = nombre;
            this.peso = peso;
            HttpGet httpGet = new HttpGet(
                    Configuration.getInstance().getUrlBase()
                            +"/Registro?nombre=jj&peso=80");
            HttpClientContext context = HttpClientContext.create();
            
            CloseableHttpResponse response1 = 
                    httpclient.execute(httpGet, context);
        } catch (IOException ex) {
            Logger.getLogger(CalculoPeso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String adelgazamiento(String workout)
    {
       try {

            HttpGet httpGet = new HttpGet(
                    Configuration.getInstance().getUrlBase()
                            +"/Workout?workout="+workout);
            HttpClientContext context = HttpClientContext.create();
            
            CloseableHttpResponse response1 = 
                    httpclient.execute(httpGet, context);
            
            //analizar el resultado
            
        } catch (IOException ex) {
            Logger.getLogger(CalculoPeso.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    
    public void logout()
    {
        nombre = null;
    }
    
}

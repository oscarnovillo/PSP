/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author oscar
 */
public class Configuration {
    
    private static Configuration config;
    
    public static Configuration getInstance(InputStream in)
    {
        if (config == null)
        {
            config =  new Configuration();
            Yaml yaml = new Yaml();
            config = (Configuration)yaml.loadAs(in,Configuration.class);
        }
        return config;
    }
    
    
    public static Configuration getInstance()
    {
        if (config == null)
        {
           
        }
        return config;
    }
    
    private String dburl;
    private ArrayList<String> lista;

    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    
    private Configuration() {
       Properties p = new Properties();
        try {
            p.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
       // this.dburl = p.getProperty("dburl");
        
        
        
        
    }

   

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

   
    
}

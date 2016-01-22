/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;


import java.io.InputStream;
import java.util.ArrayList;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author oscar
 */
public class Configuration {
    
    private static Configuration config;
    private static String pathBase;
    
    public static Configuration getInstance(InputStream in,String pathBase)
    {
        if (config == null)
        {
            config =  new Configuration(pathBase);
            Yaml yaml = new Yaml();
            config = (Configuration)yaml.loadAs(in,Configuration.class);
        }
        return config;
    }
    
    
    public static Configuration getInstance()
    {
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

    
    private Configuration(String pathBase) {
      this.pathBase = pathBase;
    }

   

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = "jdbc:sqlite:"+pathBase+"/"+dburl;
    }

   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author oscar
 */
public class Configuration {
    
  private static Configuration config;

    public static Configuration getInstance()  {
        if (config == null) {
            Yaml yaml = new Yaml();
            config = (Configuration) yaml.loadAs(Configuration.class.getResourceAsStream("config.yml"), Configuration.class);

        }
        return config;
    }
    
    public static Configuration getInstance(InputStream file)  {
        if (config == null) {
            Yaml yaml = new Yaml();
            config = (Configuration) yaml.loadAs(file, Configuration.class);

        }
        return config;
    }   
    private String urlBase;
    private List<String> lista;
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    

    public static Configuration getConfig() {
        return config;
    }

    public static void setConfig(Configuration config) {
        Configuration.config = config;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
    

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    private Configuration() {

    }
    
}

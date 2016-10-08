package config;


import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */
public class Configuration {
     
   private static Configuration config;
  
    public static Configuration getInstance()
    {
        if (config == null)
        {
            Yaml yaml = new Yaml();
            config = (Configuration)yaml.loadAs(Configuration.class.getResourceAsStream("/config/config.yml"),Configuration.class);
            
        }
        return config;
    }
    
    private String favoriteFruit;
    private String key;

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    
  private Configuration()
  {
      
  }  
}

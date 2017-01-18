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

    public static Configuration getInstance() {

        return config;
    }

    public static Configuration getInstance(InputStream file) {
        if (config == null) {
            Yaml yaml = new Yaml();
            config = (Configuration) yaml.loadAs(file, Configuration.class);

        }
        return config;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Configuration() {

    }

}

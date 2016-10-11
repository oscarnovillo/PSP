/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class ConfigurationXML {

    private Properties pro = null;
    private static ConfigurationXML config;

    private ConfigurationXML() {

    }

    public static ConfigurationXML getInstance() {

        if (config == null) {
            config = new ConfigurationXML();
            config.pro = new Properties();
            try {
                config.pro.loadFromXML(ConfigurationXML.class.getResourceAsStream("/config/config.xml"));
            } catch (IOException ex) {
                Logger.getLogger(ConfigurationXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return config;
    }

    public Properties getPro() {
        return pro;
    }

}

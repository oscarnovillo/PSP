
import config.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author oscar
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration.getInstance(sce.getServletContext().getResourceAsStream("/WEB-INF/config.yml"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

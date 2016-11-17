/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author oscar
 */
public class SendEmail {
    public static void main(String[] args) {
        try {
            Email email = new SimpleEmail();
            //email.setHostName("smtp01.educa.madrid.org");
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthentication("alumno1@iesquevedo.es", "alumnodam2");
            email.setSSLOnConnect(true);
            email.setFrom("alumno1@iesquevedo.es");
            email.setSubject("TestMail");
            email.setMsg("http://elservidor?confirmacion="+utils.Utils.randomAlphaNumeric(80));
            email.addTo("oscar.novillo@gmail.com");
           
            email.send();
            System.out.println("OK");
        } catch (EmailException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.asimetrico;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

/**
 *
 * @author oscar
 */
public class Cert {

    public static void main(String[] args) {

        try {

            CertAndKeyGen certGen = new CertAndKeyGen("RSA", "SHA256WithRSA", null);
            // generate it with 2048 bits
            certGen.generate(2048);
            // prepare the validity of the certificate
            long validSecs = (long) 365 * 24 * 60 * 60; // valid for one year
            // add the certificate information, currently only valid for one year.
            X509Certificate cert = certGen.getSelfCertificate(
                    // enter your details according to your application
                    new X500Name("CN=Pedro Salazar,O=My Organisation,L=My City,C=DE"), validSecs);
            
       
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(Cert.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

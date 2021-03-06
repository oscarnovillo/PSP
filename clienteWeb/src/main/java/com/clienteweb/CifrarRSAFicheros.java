/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clienteweb;

import static com.clienteweb.EjemploRSA.mostrarBytes;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author oscar
 */
public class CifrarRSAFicheros {
 
    public static void main(String[] args) throws FileNotFoundException {
        
        String nombre = "server1024";
        try {
            // Anadir provider JCE (provider por defecto no soporta RSA)
      Security.addProvider(new BouncyCastleProvider());  // Cargar el provider BC
       Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            // PASO 2: Crear cifrador RSA
          //  Cipher cifrador =Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC"); // Hace uso del provider BC
            /************************************************************************
             * IMPORTANTE: En BouncyCastle el algoritmo RSA no funciona realmente en modo ECB
             *		  * No divide el mensaje de entrada en bloques
             *                  * Solo cifra los primeros 512 bits (tam. clave)
             *		  * Para cifrar mensajes mayores, habría que hacer la
             *                    división en bloques "a mano"
             ************************************************************************/
            
            /*** Crear KeyFactory (depende del provider) usado para las transformaciones de claves*/
		KeyFactory keyFactoryRSA = KeyFactory.getInstance("RSA", "BC"); // Hace uso del provider BC
            /*** 4 Recuperar clave PUBLICA del fichero */
		// 4.1 Leer datos binarios x809
		byte[] bufferPub = new byte[162];
		FileInputStream in = new FileInputStream(nombre+".publica");
                DataInputStream d =  new DataInputStream(in);
                d.readFully(bufferPub,0,162);
		//in.read(bufferPub, 0, 5000);
		in.close();

		// 4.2 Recuperar clave publica desde datos codificados en formato X509
		X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
		PublicKey clavePublica2 = keyFactoryRSA.generatePublic(clavePublicaSpec);

            // PASO 3a: Poner cifrador en modo CIFRADO
            cifrador.init(Cipher.ENCRYPT_MODE, clavePublica2);  // Cifra con la clave publica
            
            System.out.println("3a. Cifrar con clave publica");
            
            String sinCifrar = "12345678901234567890123456789012345678901234567890123456789esto no puede ser";
            sinCifrar += "kokokokok";//sinCifrar = "12asdad  ";
            System.out.println(sinCifrar.getBytes("UTF-8").length);
            byte[] partes = new byte[100];
            
            
            byte[] bufferCifrado = new byte[5000];
            byte[] buffer = sinCifrar.getBytes("UTF-8");
            
                cifrador.doFinal(buffer);

                System.out.println("TEXTO CIFRADO"+bufferCifrado.length);
            mostrarBytes(bufferCifrado);
            bufferCifrado= Base64.encodeBase64(bufferCifrado);
            System.out.println("\n-------------------------------");
            
            
            
            // PASO 3b: Poner cifrador en modo DESCIFRADO
            
            /*** 2 Recuperar clave Privada del fichero */
		// 2.1 Leer datos binarios PKCS8
		byte[] bufferPriv = new byte[5000];
		in = new FileInputStream(nombre + ".privada");
		          int chars = in.read(bufferPriv, 0, 5000);
		in.close();
                
                byte[] bufferPriv2 = new byte[chars];
                System.arraycopy(bufferPriv, 0, bufferPriv2, 0, chars);

		// 2.2 Recuperar clave privada desde datos codificados en formato PKCS8
		PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv2);
                
		PrivateKey clavePrivada2 = keyFactoryRSA.generatePrivate(clavePrivadaSpec);
                
            cifrador.init(Cipher.DECRYPT_MODE, clavePrivada2); // Descrifra con la clave privada
            
            System.out.println("3b. Descifrar con clave privada");
            byte[] bufferPlano2 = cifrador.doFinal(bufferCifrado);
            
            System.out.println("TEXTO DESCIFRADO");
            mostrarBytes(bufferPlano2);
            mostrarBytes(cifrador.doFinal(Base64.decodeBase64(bufferCifrado)));
            
            System.out.println("\n-------------------------------");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (NoSuchPaddingException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(CifrarRSAFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

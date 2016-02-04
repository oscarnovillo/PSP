/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clienteweb;

import com.datoshttp.Juego;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author oscar
 */
public class ClienteWebCifrado {

    public static void main(String[] args) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/login");
            HttpClientContext context = HttpClientContext.create();

            CloseableHttpResponse response1 = httpclient.execute(httpGet, context);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.

            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            String content = EntityUtils.toString(entity1);
       System.out.println(content);
            //descodifico
            byte[] clave = Base64.decodeBase64(content);
            //descifro
            byte[] bufferPub = new byte[5000];
            FileInputStream in = new FileInputStream(new File("server1024.publica"));
            int chars = in.read(bufferPub, 0, 5000);
            in.close();

            byte[] bufferPub2 = new byte[chars];
            System.arraycopy(bufferPub, 0, bufferPub2, 0, chars);

            Security.addProvider(new BouncyCastleProvider());  // Cargar el provider BC
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");

            KeyFactory keyFactoryRSA = KeyFactory.getInstance("RSA", "BC"); // Hace uso del provider BC
            // 4.2 Recuperar clave publica desde datos codificados en formato X509
            X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub2);
            PublicKey clavePublica2 = keyFactoryRSA.generatePublic(clavePublicaSpec);

            cifrador.init(Cipher.DECRYPT_MODE, clavePublica2); // Descrifra con la clave privada
            
            byte[] claveAES = cifrador.doFinal(clave);
            
            SecretKey originalKey = new SecretKeySpec(claveAES, 0, claveAES.length, "AES"); 
            
            //descodifico la key del AES
            // la convierto a key
     

            System.out.println("----Segunda llamada");
            httpGet = new HttpGet("http://localhost:8080/juegos?command=get");

            httpclient = HttpClients.createDefault();
            response1 = httpclient.execute(httpGet, context);

            entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            content = EntityUtils.toString(entity1);

            System.out.println(content);
            ObjectMapper mapper = new ObjectMapper();
            
            System.out.println(descifra(Base64.decodeBase64(content),originalKey));
            
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList<Juego> j = mapper.readValue(descifra(Base64.decodeBase64(content),originalKey),
                    new TypeReference<ArrayList<Juego>>() {
            });
            System.out.println(j.get(0).getNombre());
            response1.close();

        } catch (IOException ex) {
            Logger.getLogger(ClienteWebCifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteWebCifrado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public static byte[] cifra(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true,null);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    public static String descifra(byte[] cifrado,SecretKey key) throws Exception {
        final Cipher aes = obtieneCipher(false,key);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    private static Cipher obtieneCipher(boolean paraCifrar,SecretKey key) throws Exception {
//        final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
//        final MessageDigest digest = MessageDigest.getInstance("SHA-1");
//        digest.update("clave".getBytes("UTF-8"));
//        //final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }

}

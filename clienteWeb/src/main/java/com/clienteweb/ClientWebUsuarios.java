/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clienteweb;

import com.datoshttp.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ClientWebUsuarios {

    //private static String url = "http://www.apache.org/";
    public static void main(String[] args) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            Usuario usu = new Usuario("pepe", "pepe");

            HttpPost httpPost = new HttpPost("http://localhost:8080/usuarios");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            ObjectMapper mapper = new ObjectMapper();
            String usuarioJson = mapper.writeValueAsString(usu);
            
            byte [] bytes = PasswordHash.cifra(usuarioJson);
            String mandar = new String(Base64.encodeBase64(bytes));
    
            
            nvps.add(new BasicNameValuePair("user",mandar));

            
            
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                System.out.println(EntityUtils.toString(entity2,"UTF-8"));
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientWebUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientWebUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientWebUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

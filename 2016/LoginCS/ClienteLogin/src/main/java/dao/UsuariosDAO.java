/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dam.model.Usuario;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.Configuration;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author oscar
 */
public class UsuariosDAO {

    public List<Usuario> getUsers() {
        List<Usuario> lista = null;

        try {

            String msg = "";
            try {
                CloseableHttpClient httpclient;

                httpclient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(
                        "http://localhost:8080/servidorLogin/Usuarios?op=GET");
                //Ejecutamos y se mete dentro del response la respuesta
                CloseableHttpResponse response = httpclient.execute(httpGet);
                // Cogemos la entidad getContent y se parsea el objeto con el mapper
                HttpEntity entity1 = response.getEntity();
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                lista = mapper.readValue(entity1.getContent(), new TypeReference<List<Usuario>>() {
                });

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}

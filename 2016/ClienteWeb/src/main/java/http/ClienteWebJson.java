/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Configuration;
import dao.PesoPersona;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 *
 * @author oscar
 */
public class ClienteWebJson {

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet(
                    Configuration.getInstance().getUrlBase()
                    +"/JsonExample");
            HttpClientContext context = HttpClientContext.create();
            
            CloseableHttpResponse response1 = 
                    httpclient.execute(httpGet, context);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {

                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
               ObjectMapper mapper = new ObjectMapper();
               mapper.configure(
                       DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, 
                       false);
                ArrayList<PesoPersona> j = 
                        mapper.readValue(
                                entity1.getContent(),
                    new TypeReference<ArrayList<PesoPersona>>() {
            });
                System.out.println("peso"+j.get(0).getPeso()); 

                // Get all the cookies
            }
                catch(Exception e)
                        {
                        System.out.println(e.getMessage());
                        
            } finally {
                response1.close();
            }
            
            
        } catch (IOException ex) {
          
        } finally {
            try {
                httpclient.close();
            } catch (IOException ex) {
                
            }
        }
    }
}

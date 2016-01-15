/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clienteweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

/**
 *
 * @author oscar
 */
public class ClienteWebCookies {

    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet httpGet = new HttpGet("http://localhost:8080/ServletSession");
            HttpClientContext context = HttpClientContext.create();
            
            CloseableHttpResponse response1 = httpclient.execute(httpGet, context);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                
                
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed

                Header[] hs = response1.getAllHeaders();
                for (Header h : hs) {
                    System.out.println(h.getName() + "**** " + h.getValue());
                }
                System.out.println(EntityUtils.toString(entity1,"UTF-8"));
                for (Cookie c : context.getCookieStore().getCookies())
                {
                    System.out.println(c.getName()+ " "+c.getValue());
                }
                

                // Get all the cookies
            } finally {
                response1.close();
            }

            response1 = httpclient.execute(httpGet, context);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                
                
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed

                Header[] hs = response1.getAllHeaders();
                for (Header h : hs) {
                    System.out.println(h.getName() + "**** " + h.getValue());
                }
                System.out.println(EntityUtils.toString(entity1,"UTF-8"));
                for (Cookie c : context.getCookieStore().getCookies())
                {
                    System.out.println(c.getName()+ " "+c.getValue());
                }
                
                // Get all the cookies
            } finally {
                response1.close();
            }

            /*
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }*/
        } catch (IOException ex) {
            Logger.getLogger(ClientWeb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                httpclient.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

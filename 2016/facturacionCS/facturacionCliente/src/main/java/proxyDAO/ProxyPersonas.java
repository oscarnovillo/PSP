/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyDAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Configuration;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MensajeServidor;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author oscar
 */
public class ProxyPersonas {

    CloseableHttpClient httpclient;
    FiltroProxyDAO filtro = null;

    public ProxyPersonas() {
        httpclient = HttpClients.createDefault();
        filtro = new FiltroProxyDAO();
    }

    public MensajeServidor callUserServlet(Object usuario, String url, String op) {
        MensajeServidor mensaje = null;
        try {
            HttpPost httpPost = new HttpPost(Configuration.getInstance().getUrlBase() + "/" + url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("user", filtro.prepararParametro(usuario)));
            nvps.add(new BasicNameValuePair("op", op));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            HttpEntity entity = response2.getEntity();

            mensaje = filtro.prepararRespuesta(EntityUtils.toString(entity, "UTF-8"));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProxyPersonas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProxyPersonas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProxyPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

}

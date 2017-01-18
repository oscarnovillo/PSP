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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MensajeServidor;
import org.apache.http.HttpEntity;
import utils.Encriptacion;

/**
 *
 * @author oscar
 */
public class FiltroProxyDAO {

    ObjectMapper mapper = null;

    public FiltroProxyDAO() {
        mapper = new ObjectMapper();

    }

    public String prepararParametro(Object parametro) {
        String parametroPreparado = null;
        try {

            parametroPreparado = mapper.writeValueAsString(parametro);
            Encriptacion enc = new Encriptacion();
            
            enc.cifrar();

        } catch (JsonProcessingException ex) {
            Logger.getLogger(FiltroProxyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parametroPreparado;
    }

    public MensajeServidor prepararRespuesta(String entity) {
        MensajeServidor mensaje = null;

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            //descodificar base 64
             Encriptacion enc = new Encriptacion();
            
            String respuesta = enc.descifrar(entity);
            
            mensaje = mapper.readValue(respuesta, new TypeReference<MensajeServidor>() {
            });
        } catch (IOException ex) {
            Logger.getLogger(FiltroProxyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

}

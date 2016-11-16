/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    public BorderPane panelPrincipal;
    
    @FXML
    public Menu FXMLMenuLogin;

    @FXML
    public Menu FXMLMenuUsuario;

    @FXML
    private void handleMenuItemLogin(ActionEvent event) {

        try {
            FXMLMenuUsuario.setVisible(true);
            AnchorPane root = (AnchorPane)FXMLLoader.load(
                    getClass().getResource("/fxml/FXMLPantalla2.fxml"));
            panelPrincipal.setCenter( root);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleMenuItemRegistro(ActionEvent event) {

        FXMLMenuUsuario.setVisible(false);
         try {
            FXMLMenuUsuario.setVisible(true);
            AnchorPane root = (AnchorPane)FXMLLoader.load(
                    getClass().getResource("/fxml/FXMLPantalla1.fxml"));
            panelPrincipal.setCenter( root);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

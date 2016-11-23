/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlControllers;

import dam.model.Usuario;
import dao.ProxyUsuariosDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ObservableList<Usuario> ob =null;
    List<Usuario> lista = null;
    
    @FXML
    private void handleMenuItemLogin(ActionEvent event) {

        try {
            ProxyUsuariosDAO user = new ProxyUsuariosDAO();
            lista = user.getUsers();
            ob = FXCollections.observableArrayList(lista);
            FXMLMenuUsuario.setVisible(true);
            AnchorPane root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLPantalla2.fxml"));
            root = loader.load();
            FXMLPantalla2Controller controller = loader.getController();
            controller.setOb(ob);
            panelPrincipal.setCenter( root);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleMenuItemRegistro(ActionEvent event) {

        FXMLMenuUsuario.setVisible(false);
        lista.get(0).setUser("OTRO TIO");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

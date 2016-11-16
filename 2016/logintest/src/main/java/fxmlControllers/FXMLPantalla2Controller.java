package fxmlControllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.UsuariosDAO;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class FXMLPantalla2Controller implements Initializable {

    @FXML
    public TableView<Usuario> FXMLTabla;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //   TODO
      UsuariosDAO user = new UsuariosDAO();
      List<Usuario> lista = user.getUsers();  
      ObservableList<Usuario> ob = FXCollections.observableArrayList(lista); 
    
      //Cargar Tabla
      FXMLTabla.setItems(ob);
     
    }    
    
}

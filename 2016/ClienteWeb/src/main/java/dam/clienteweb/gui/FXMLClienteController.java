/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.clienteweb.gui;


import config.Configuration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import negocio.CalculoPeso;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class FXMLClienteController implements Initializable {

    @FXML
    public TextField txtNombre;
    
    @FXML
    public TextField txtPeso;
    
    @FXML
    public Button btRegistro;
    
    @FXML
    public Button btWorkout;  
    
    CalculoPeso cp = new CalculoPeso();
    
    @FXML
    private void handleButtonRegistro(ActionEvent event)
    {
        
        
        String nombre = txtNombre.getText();
        String peso = txtPeso.getText();

        cp.registro(nombre, peso);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(txtNombre.getText());
        alert.show();
        
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

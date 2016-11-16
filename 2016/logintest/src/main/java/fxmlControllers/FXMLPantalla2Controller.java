package fxmlControllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UsuariosDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Person;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class FXMLPantalla2Controller implements Initializable {

    @FXML
    public TableView<Usuario> FXMLTabla;
    @FXML
    private TableColumn<Usuario, String> firstNameColumn;
    @FXML
    private TableColumn<Usuario, String> lastNameColumn;
    @FXML
    private TableColumn<Usuario, Date> dateColumn;
    @FXML
    private TextArea txtArea;
    
    
    ObservableList<Usuario> ob =null;

    public void setOb(ObservableList<Usuario> ob) {
        try {
            this.ob = ob;
            FXMLTabla.setItems(ob);
            ObjectMapper mapper = new ObjectMapper();
            txtArea.setText(mapper.writeValueAsString(ob.get(0)));
            Usuario user = mapper.readValue(txtArea.getText(), new TypeReference<Usuario>(){});
            user.setUser("CONTRASEÑA");
            ob.add(user);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FXMLPantalla2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantalla2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   TODO
        
        //Cargar Tabla
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().userProperty());
        ((TableColumn<Usuario, String>)FXMLTabla.getColumns().get(0)).setCellValueFactory(cellData -> cellData.getValue().userProperty());
        ((TableColumn<Usuario, String>)FXMLTabla.getColumns().get(2)).setCellValueFactory(cellData -> cellData.getValue().userProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

    }

}

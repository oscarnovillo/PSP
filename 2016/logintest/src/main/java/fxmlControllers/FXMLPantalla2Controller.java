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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    ObservableList<Usuario> ob =null;

    public void setOb(ObservableList<Usuario> ob) {
        this.ob = ob;
        FXMLTabla.setItems(ob);
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
        

    }

}

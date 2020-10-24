/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.AvisAnnonce;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import services.AvisAnnoncesServices;

public class RatingAnnonceController implements Initializable {
    @FXML
    private Button EvalBtn;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button AnnulerBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList ob =FXCollections.observableArrayList(
       "Mauvais","Passable","Bien","Assez Bien","TrésBien"
        );
        combo.setItems(ob);
    }   
    @FXML
    private void Evaluer(ActionEvent event) throws IOException {
        AvisAnnonce A =new AvisAnnonce();
        A.setAvis(combo.getValue());
        if(A.getAvis()!=null){
        AvisAnnoncesServices AAS= new AvisAnnoncesServices();
        AAS.AjouterAvisAnnonce(A);
        }
        
    }

    @FXML
    private void Annuler(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    
}

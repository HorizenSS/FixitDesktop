/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.CommentAnn;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CommentAnnoncesServices;
import com.sun.speech.freetts.*;
public class AffichComentAnnonceController implements Initializable {
    @FXML
    private TableView<CommentAnn> listAnnonce;
    @FXML
    private TableColumn<CommentAnn, String> Commentaire;
    @FXML
    private TableColumn<CommentAnn, Date> dateC;
    @FXML
    private Button suppBtn;
    @FXML
    private Button AnnulerBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommentAnnoncesServices Ann=new CommentAnnoncesServices();
        ArrayList A= (ArrayList) Ann.AfficherAllComment();
        ObservableList ob=FXCollections.observableArrayList(A);
        listAnnonce.setItems(ob);
        Commentaire.setCellValueFactory(new PropertyValueFactory<>("commentAnn"));
        dateC.setCellValueFactory(new PropertyValueFactory<>("d"));
        listAnnonce.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    

    @FXML
    private void delete(ActionEvent event) {
        ObservableList<CommentAnn> r,fo;
        CommentAnnoncesServices Ann=new CommentAnnoncesServices();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r!=null){
        r.stream().map((A) -> {
            Ann.SupprimerCommentA(A);
            return A;
        }).forEach((A) -> {
            fo.remove(A);
        });
        }
    }
private void read(){
 VoiceManager vm = VoiceManager.getInstance();
    Voice voice = vm.getVoice("kevin16");
    voice.allocate();
 System.out.println("Description: " + voice.getDescription());
}

    @FXML
    private void Annuler(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    
}

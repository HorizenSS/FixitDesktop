/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.CommentAnn;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CommentAnnoncesServices;
import com.sun.speech.freetts.*;
public class CommentAnnonceController implements Initializable {
    @FXML
    private Button CommentBtn;
    @FXML
    private Button AnnulerBtn;
    @FXML
    private TextArea text;
    @FXML
    private TableView<CommentAnn> tableCom;
    @FXML
    private TableColumn<CommentAnn, String> tCom;
    @FXML
    private TableColumn<CommentAnn, String> tDate;

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
        tableCom.setItems(ob);
        tCom.setCellValueFactory(new PropertyValueFactory<>("commentAnn"));
        tDate.setCellValueFactory(new PropertyValueFactory<>("d"));
        tableCom.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    

    @FXML
    private void Commenter(ActionEvent event) throws InstantiationException, IllegalAccessException, IOException {
        CommentAnnoncesServices CAC=new CommentAnnoncesServices();
        CommentAnn com=new CommentAnn();
        com.setCommentAnn(text.getText());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        com.setD(format.format(date));
        if(com.getD()!=null &&com.getCommentAnn()!=null){
        CAC.AjouterCommentAnnonce2(com);
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("CommentAnnonce.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Les Services!");
        primary.setScene(scene2);
        primary.show();
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    
}

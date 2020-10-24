/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import services.AnnonceServices;
import utils.mailU;
public class AnnoncesConfirmController implements Initializable {
    @FXML
    private TableView<Annonce> listAnnonce;
    @FXML
    private TableColumn<Annonce, String> NomAnnonce;
    @FXML
    private TableColumn<Annonce, String> type;
    @FXML
    private TableColumn<Annonce, Float> Prix;
    @FXML
    private TableColumn<Annonce, String> Description;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imagev;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button delete;
    @FXML
    private CheckBox mail;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceServices Ann=new AnnonceServices();
        ArrayList A= (ArrayList) Ann.AfficherAllAnnonceC();
        ObservableList ob=FXCollections.observableArrayList(A);
        listAnnonce.setItems(ob);
        NomAnnonce.setCellValueFactory(new PropertyValueFactory<>("NomAnnonce"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        listAnnonce.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<Annonce> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super Annonce>) Annonce->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    if(Annonce.getNomAnnonce().toLowerCase().contains(lower)){return true;}
                    else if(Annonce.getType().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<Annonce> k = new SortedList<>(fil);
            k.comparatorProperty().bind(listAnnonce.comparatorProperty());
            listAnnonce.setItems(k);
        });
        listAnnonce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       ObservableList<Annonce> anno;
         File f=new File("L:\\studir$\\3eme\\3 eme\\3A\\PIDEV\\PI_Java_3A\\src\\Image\\"+newSelection.getImage());
          Image img=new Image(f.toURI().toString());
          imagev.setImage(img);
    }});  
    }    

    @FXML
    private void confirm(ActionEvent event) {
        ObservableList<Annonce> r,f;
        f=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        AnnonceServices Ev=new AnnonceServices();
        if(r!=null){
        r.stream().map((A) -> {
            Ev.ModifierEtat(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
        
    }
  
    
    @FXML
    private void delete(ActionEvent event) {
         ObservableList<Annonce> r,f;
        f=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        AnnonceServices Ev=new AnnonceServices();
        if(r!=null){
        r.stream().map((A) -> {
            Ev.SupprimerAnnonceA(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
    }
    
  
     
}

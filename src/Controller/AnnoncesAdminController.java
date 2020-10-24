/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import services.Csv;
import Entities.Annonce;
import java.io.File;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.AnnonceServices;

public class AnnoncesAdminController implements Initializable {
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
    private Button suppBtn;
    @FXML
    private Button StatBtn;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imagev;
    @FXML
    private Button StatAvisBtn;
    @FXML
    private Button commentBtn;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button Home;
    @FXML
    private Button Produits;
    @FXML
    private Button Annonces;
    @FXML
    private Button Evénements;
    @FXML
    private Button Restaurants;
    @FXML
    private Button SAV;
 @FXML
    private Button csv;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnnonceServices Ann=new AnnonceServices();
        ArrayList A= (ArrayList) Ann.AfficherAllAnnonce();
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
    private void delete(ActionEvent event) {
        ObservableList<Annonce> r,fo;
        AnnonceServices Ann=new AnnonceServices();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r!=null){
        r.stream().map((A) -> {
            Ann.SupprimerAnnonceA(A);
            return A;
        }).forEach((A) -> {
            fo.remove(A);
        });
        }
    }

    @FXML
    private void StatType(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesAdmin.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StatAnnonce.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void StatAvis(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesAdmin.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StatAvisAnnonce.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Comment(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage Stage=new Stage();
        Parent root2=FXMLLoader.load(getClass().getResource("AffichComentAnnonce.fxml"));
        Scene scene2 = new Scene(root2);
        Stage.setTitle("Avis!");
        Stage.setScene(scene2);
        Stage.show();
    }

    @FXML
    private void confirm(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesAdmin.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesConfirm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomeAd.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void Produits(ActionEvent event) {
    }

    @FXML
    private void Annonces(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
      Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("AffichEventAd.fxml"));
      Scene scene = new Scene(root);    
      primaryStage.setTitle("Evenement");
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    @FXML
    private void Resto(ActionEvent event) {
    }

    @FXML
    private void Sav(ActionEvent event) {
    }
    @FXML
    private void generercsv(ActionEvent event) {
       
        
    }
}

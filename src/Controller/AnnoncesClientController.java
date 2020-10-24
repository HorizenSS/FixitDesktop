/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import Entities.rechercheAnnonce;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
import services.rechercheAnnonceServices;
import utils.InputValidation;

public class AnnoncesClientController implements Initializable {
    
    public static Annonce d ;
    public static TableView<Annonce> Ak;
    
    @FXML
    private TableView<Annonce> tabAnn;
    @FXML
    private TableColumn<Annonce, String> NomAnnonce;
    @FXML
    private ImageView imageAnn;
    @FXML
    private Label desc;
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private TextField seach;
    @FXML
    private Hyperlink AvisBtn;
    @FXML
    private Hyperlink CommentBtn;
    @FXML
    private Hyperlink GalleryBtn;
    @FXML
    private Hyperlink ListeAr;
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
    private Hyperlink détailBtn;
    @FXML
    private Label nbrText;
    @FXML
    private Button RechercheBtn;

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
        tabAnn.setItems(ob);
        NomAnnonce.setCellValueFactory(new PropertyValueFactory<>("NomAnnonce"));
        
        tabAnn.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
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
            k.comparatorProperty().bind(tabAnn.comparatorProperty());
            tabAnn.setItems(k);
            Ak=tabAnn;
            System.out.println(Ak.getItems().size());
        if(Ak.getItems().size()==0){
            rechercheAnnonce r=new rechercheAnnonce();
            r.setRecherche(seach.getText());
            rechercheAnnonceServices rs=new rechercheAnnonceServices();
            rs.AjouterRAnnonce(r);
        }
        });
        tabAnn.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       ObservableList<Annonce> anno;
          File f=new File("L:\\studir$\\3eme\\3 eme\\3A\\PIDEV\\PI_Java_3A\\src\\Image"+newSelection.getImage());
          Image img=new Image(f.toURI().toString());
          imageAnn.setImage(img);
          prix.setVisible(false);
          nom.setVisible(true);
          nom.setText(newSelection.getNomAnnonce());
          desc.setVisible(true);
          desc.setText(newSelection.getDescription());
          type.setVisible(true);
          type.setText(newSelection.getType());
          if(newSelection.getPrix()!=0)
          {
          prix.setVisible(true);
          prix.setText(Float.toString((float) newSelection.getPrix()));
          }
          d=newSelection;
    }});  
        rechercheAnnonceServices rAnn=new rechercheAnnonceServices();
        int i=rAnn.Count();
        if(i>0){
        nbrText.setVisible(true);
        nbrText.setText(Integer.toString(i));
        }
    }

    @FXML
    private void Avis(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage Stage=new Stage();
        Parent root2=FXMLLoader.load(getClass().getResource("RatingAnnonce.fxml"));
        Scene scene2 = new Scene(root2);
        Stage.setTitle("Avis!");
        Stage.setScene(scene2);
        Stage.show();
    }

    @FXML
    private void comment(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage Stage=new Stage();
        Parent root2=FXMLLoader.load(getClass().getResource("CommentAnnonce.fxml"));
        Scene scene2 = new Scene(root2);
        Stage.setTitle("Avis!");
        Stage.setScene(scene2);
        Stage.show();
    }

    @FXML
    private void Gallery(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GalleryPicAnnonce.fxml"));
        Scene scene = new Scene(root);        
        primaryStage.setTitle("Evenement!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ListeAr(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MesAnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Artisan!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomeC.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AffichEventClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Evenement!");
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
    private void détail(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        ObservableList<Annonce> r=tabAnn.getSelectionModel().getSelectedItems();
        if(r.size()>0){
        Parent root = FXMLLoader.load(getClass().getResource("AnnonceDétail.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Détail Annonce!!");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }
    
    @FXML
    private void rech(ActionEvent event) {
        rechercheAnnonceServices rAnn=new rechercheAnnonceServices();
        ArrayList A= (ArrayList) rAnn.AfficherAllRAnnonceC();
        int i=0;
        i=rAnn.Count();
        if(i>0){
        Alert alert = new InputValidation().getAlert("Recherche", "Veuillez verifier la liste des annonces! \n Il y a de nouvelles annonces liées à vos anciens recherches");
        alert.showAndWait();
          for (Iterator it = A.iterator(); it.hasNext();) {
            rechercheAnnonce r = (rechercheAnnonce) it.next(); 
            rAnn.SupprimerAnnonceA(r);
          }
        nbrText.setText(Integer.toString(0));
        nbrText.setVisible(false);
        
        }
        else {
        Alert alert = new InputValidation().getAlert("Recherche", "Les annonces concernant les produits que vous chercher n'existe pas!");
        alert.showAndWait();
        }
        System.out.println(i);
    }
    
    
}

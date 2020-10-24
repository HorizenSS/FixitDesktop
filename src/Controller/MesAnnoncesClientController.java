/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MesAnnoncesClientController.da;
import Entities.Annonce;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
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
import services.AvisAnnoncesServices;
import services.CommentAnnoncesServices;
import utils.InputValidation;

/**
 * FXML Controller class
 *
 * @author Sweet
 */
public class MesAnnoncesClientController implements Initializable {

 
        public static Annonce da ;

    
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
    private Button Ajout;
    @FXML
    private Button suppBtn;
    @FXML
    private Button ModifBtn;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imagev;
File f;
@FXML
    private Button partageFBBTn;
    /**
     * Initializes the controller class.
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
                    else if(Annonce.getDescription().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<Annonce> k = new SortedList<>(fil);
            k.comparatorProperty().bind(listAnnonce.comparatorProperty());
            listAnnonce.setItems(k);
        });
        listAnnonce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
          f=new File("L:\\studir$\\3eme\\3 eme\\3A\\PIDEV\\PI_Java_3A\\src\\Image\\"+newSelection.getImage());
          Image img=new Image(f.toURI().toString());
          imagev.setImage(img);
          da=newSelection;
    }});  
     
 
    } 
    @FXML
    public void delete(ActionEvent event) throws IOException{
        ObservableList<Annonce> r,fo;
        AnnonceServices Ann=new AnnonceServices();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r!=null){
            r.stream().forEach((A) -> {
                Ann.SupprimerAnnonceA2(A);
            });
        }
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("MesAnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce service!");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void AjouterAnn(ActionEvent event) throws IOException{
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("MesAnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce service!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutAnnonce.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonce service!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    @FXML
    private void Modif(ActionEvent event) throws IOException {
        ObservableList<Annonce> r,fo;
        AnnonceServices Ann=new AnnonceServices();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r.size()>0){
            System.out.println(da.getIdUser());
        if(da.getIdUser()==1){
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("MesAnnoncesClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierAnnonce.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        r.stream().forEach((A) -> {
            ModifierAnnonceController controllerModifA = loader.getController();
            controllerModifA.DATA(A);
        });
        primaryStage.setTitle("Modifier Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        }
    }

   
    @FXML
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }


    @FXML
    private void Annonces(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MesAnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }



    @FXML
    private void partageFB(ActionEvent event) {
        Annonce selectedItem = listAnnonce.getSelectionModel().getSelectedItem();
        String accessToken = "EAAgRwfggYr4BAEywYdnfJZBVkEd6ufzR0tnjqHxeEuGHvTAoR4tkQDVbaZCjYX2qG1kvjpwUHWhkShc2Q82tzhZBbAxVurpXwqm7m8q5tlXBWokEYAQmiZBPG8IWDV8ZAY7ZAGuOEg2PJ2677152utHQhxvoofQhW6Y41Pl8DbNgFhxZC2Lzwt6rem6BBBEw52ooFF1XbEhdQZDZD";
        Scanner s = new Scanner(System.in);
        FacebookClient fbClient=new DefaultFacebookClient(accessToken, Version.UNVERSIONED);
         
FacebookType publishMessageResponse =
fbClient.publish("me/feed", FacebookType.class,
Parameter.with("message", "hello"));
        FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                Parameter.with("message", "Annonce" + selectedItem.getNomAnnonce()+ " at" + selectedItem.getDescription()),
                Parameter.with("link", "http://127.168.0.1/"));
        System.out.println("fb.com/" + response.getId());
        Alert alert = new InputValidation().getAlert("Success", "Votre Annonce à été publié sur facebook!");
        alert.showAndWait();
    }
    
}


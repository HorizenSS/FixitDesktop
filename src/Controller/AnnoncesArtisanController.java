/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import Entities.CommentAnn;
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
//import static services.UserService.conn;
import utils.InputValidation;

public class AnnoncesArtisanController implements Initializable {
    
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
    private Button StatBtn;
    @FXML
    private Button ModifBtn;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imagev;
    @FXML
    private Button StatAvisBtn;
    @FXML
    private Button commentBtn;
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
    
    File f;
    @FXML
    private Button partageFBBTn;
    @FXML
    private PieChart pie;
    @FXML
    private TableView<CommentAnn> tableCom;
    @FXML
    private TableColumn<CommentAnn, String> commentaire;
    @FXML
    private TableColumn<CommentAnn, String> date;
    
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
        listAnnonce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
       if (newSelection != null) {
            int i,j,k,f2,r;
       AvisAnnoncesServices A2 = new AvisAnnoncesServices();
       i=A2.CountAvis2("Mauvais",newSelection.getId());
       k=A2.CountAvis2("Passable",newSelection.getId());
       f2=A2.CountAvis2("Bien",newSelection.getId());
       r=A2.CountAvis2("Assez Bien",newSelection.getId());
       j=A2.CountAvis2("TrésBien",newSelection.getId());
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Mauvais",i),
                       new PieChart.Data("Passable",k),
                       new PieChart.Data("Bien",f2),
                       new PieChart.Data("Assez Bien",r),
                       new PieChart.Data("TrésBien",j)
               );
        pie.setData(pieChartData);
       }
         });
        listAnnonce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
       if (newSelection != null) {
        tableCom.setVisible(true);
        CommentAnnoncesServices v =  new  CommentAnnoncesServices();    
        ArrayList arrayList = (ArrayList) v.AfficherAllComment2(newSelection.getId());
        ObservableList observablelist = FXCollections.observableArrayList(arrayList);
        tableCom.setItems(observablelist);
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentAnn"));
        date.setCellValueFactory(new PropertyValueFactory<>("d"));
                }
        });
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
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce service!");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void AjouterAnn(ActionEvent event) throws IOException{
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
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
    private void StatType(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce service!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StatAnnonce.fxml"));
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
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
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
    private void StatAvis(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
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
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Evenements!");
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
    private void partageFB(ActionEvent event) {
        Annonce selectedItem = listAnnonce.getSelectionModel().getSelectedItem();
        String accessToken = "EAAgRwfggYr4BAPZCkzmwUZAD0luWxWNeCFwpbRFNj2DY7cKsbotThKrKzEKZA1lZA1ZACkkWORA2WknmNQvUO94xPKkM9MmRdW0qZAOr8ldAL89eiaQSZCrccM4CGCZCSAoyIcemscqTK2BuCyCcDGb9GDwdyjJTUcXJx37xAjnOyZAm0ccBD8J0T";
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

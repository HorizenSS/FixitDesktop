/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import  tray.notification.TrayNotification;

import tray.notification.NotificationType;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;


import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import Entities.Annonce;
import utils.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.AnnonceServices;
import utils.InputValidation;


public class AjoutAnnonceController implements Initializable {
    @FXML
    private TextField NomAnnonce;
    @FXML
    private TextField Prix;
    @FXML
    private TextArea Description;
    @FXML
    private Button AjoutBtn;
    @FXML
    private RadioButton Demande;
    @FXML
    private RadioButton Offre;
    @FXML
    private ToggleGroup type;
    @FXML
    private Label prixLabel;
    @FXML
    private Button fileBtn;
    @FXML
    private ImageView imageAnn;
    
    private FileChooser fc;
    private File f;
    private Image image;
    private String uuid;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=f.getName();
            
            image=new Image(f.toURI().toString(),100,150,true,true);
            imageAnn.setImage(image); 
            Utils u =new Utils();
            String Emp ="C:\\wamp64\\www\\SoukI\\web\\imagesAnnonce\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        }    
    }
        
    @FXML
    private void AjoutAnnonce(ActionEvent event) throws IOException
    {String title = "Congratulations sir";
        String message = "You've successfully created your first Tray Notification";
   
        NotificationType notification = NotificationType.INFORMATION;
        
        Annonce A=new Annonce();
        A.setNomAnnonce(NomAnnonce.getText());
        if(Demande.isSelected()){
            A.setType(Demande.getText());
            A.setPrix(Float.parseFloat(Prix.getText()));
            Prix.setEditable(true);
        }
        else if(Offre.isSelected()) { 
            A.setType(Offre.getText());
            Prix.setEditable(false);
            Prix.setMouseTransparent(true);
              TrayNotification tray = new TrayNotification("confirmation annonce", "votre annonce ne sera pas visible qu'apres confirmation!",notification);
        tray.showAndWait();
            
        }
        A.setDescription(Description.getText());
        A.setImage(uuid);
        AnnonceServices Ann=new AnnonceServices();
         if (InputValidation.validTextField(NomAnnonce.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez un nom ");
            alertNom.showAndWait();
        } 
         else if (InputValidation.validTextField(Description.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez une description");
                alertPrenom.showAndWait();
            } 
        else
        {
        Alert alertPrenom = new InputValidation().getAlert("Ajout", "Ajout Réussi!");
        alertPrenom.showAndWait();
        Ann.AjouterAnnonce(A);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        }
    }
    
}
